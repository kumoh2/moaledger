// main.js
import { db, checkLogin, setupLogout,loadHTML } from '../../js/utils/helpers.js';
import { Calendar } from 'https://cdn.skypack.dev/@fullcalendar/core';
import dayGridPlugin from 'https://cdn.skypack.dev/@fullcalendar/daygrid';

document.addEventListener('DOMContentLoaded', async () => {
  await loadHTML();
  const currentUser = checkLogin();
  setupLogout();
  setupSelectGroup(currentUser);
  setupnickName(currentUser);
  setupLedgerForm(currentUser);
  initializeCalendar(currentUser);
  loadPrices();
});

async function setupSelectGroup(currentUser) {

  // ✅ 1. usergroup 테이블에서 user_id로 group_id 목록 조회
  const { data: userGroups, error: userGroupError } = await db
    .from('usergroup')
    .select('group_id')
    .eq('user_id', currentUser);

  if (!userGroups.length) return; // 그룹이 없으면 종료

  const groupIds = userGroups.map(group => group.group_id);

  console.log('그룹 아이디 목록:', groupIds);

  // ✅ 2. group 테이블에서 group_id로 name 조회
  const { data: groups, error: groupError } = await db
    .from('group')
    .select('group_id, name')
    .in('group_id', groupIds); // 여러 조건에 대해 .in() 사용

  if (groupError) throw groupError;

  // ✅ 3. 조회된 그룹들을 select 옵션으로 추가
  groups.forEach(group => {
    const option = document.createElement('option');
    option.value = group.group_id;
    option.textContent = group.name;
    groupSelect.appendChild(option);
  });

  // ✅ 4. userledger 테이블에서 main_ledger_group_id 가져와 선택 설정
  const { data: userLedger, error: userLedgerError } = await db
    .from('userledger')
    .select('main_ledger_group_id')
    .eq('user_id', currentUser)
    .single(); // 단일 레코드 조회

  if (userLedgerError) throw userLedgerError;

  if (userLedger?.main_ledger_group_id) {
    groupSelect.value = userLedger.main_ledger_group_id;
  }

  loadLedger(currentUser);
}

async function setupnickName(currentUser) {
  const { data: user, error } = await db
    .from('user')
    .select('nickname')
    .eq('user_id', currentUser)
    .single();

  if (error) {
    console.error('사용자 정보 조회 오류:', error);
    return;
  }

  const nicknameEl = document.getElementById('nicknameSpan');
  if (nicknameEl) {
    nicknameEl.textContent = user.nickname + ' 님';
  }
}

function setupLedgerForm(currentUser) {
  const ledgerForm = document.getElementById('ledgerForm');
  if (ledgerForm) {
    ledgerForm.addEventListener('submit', async (e) => {
      e.preventDefault();
      const amount = parseInt(document.getElementById('amount').value);
      const type = document.getElementById('type').value;
      const date = new Date().toISOString();
      const groupSelect = document.getElementById('groupSelect');

      console.log(groupSelect.value, currentUser, amount, type, date);

      const { data, error } = await db
          .from('ledger')
          .insert([{ 
            group_id: groupSelect.value, 
            user_id: currentUser, 
            transaction_type: type, 
            transaction_date: date,
            category: '', 
            amount: amount }]);

      if (error) {
        alert('가계부 등록 중 오류: ' + error.message);
        return;
      }

      alert("내역이 등록되었습니다.");
      ledgerForm.reset();
      loadLedger(currentUser);
    });
  }
}

function initializeCalendar(currentUser) {
  const calendarEl = document.getElementById('calendar');
  if (calendarEl) {
    const calendar = new Calendar(calendarEl, {
      plugins: [dayGridPlugin],
      initialView: 'dayGridMonth',
      locale: 'ko',
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
      },
      selectable: true,
      events: async (fetchInfo, successCallback, failureCallback) => {
        try {
          const groupSelect = document.getElementById('groupSelect');
          const { data: entries, error } = await db
              .from('ledger')
              .select('*')
              .eq('user_id', currentUser)
              .eq('group_id', groupSelect.value);

          if (error) {
            console.error('이벤트 불러오기 오류:', error);
            failureCallback(error);
            return;
          }

          const events = entries.map(entry => ({
            title: `${entry.type === 'income' ? '수입' : '지출'}: ${entry.amount}원`,
            start: entry.date,
            allDay: true
          }));

          successCallback(events);
        } catch (err) {
          console.error('예기치 못한 오류:', err);
          failureCallback(err);
        }
      }
    });
    calendar.render();
  }
}

async function loadLedger(userId) {
  try {
    const { data: entries, error } = await db
        .from('ledger')
        .select('*')
        .eq('user_id', userId)
        .eq('group_id', groupSelect.value)
        .order('transaction_type', { ascending: false });

    if (error) {
      console.error('가계부 내역 조회 오류:', error);
      alert('가계부 내역이 조회되지 않습니다.');
      return [];
    }
    console.log('가계부 내역:', entries);
    return entries;
  } catch (err) {
    console.error('예기치 못한 오류:', err);
  }
}

function loadPrices() {
  const agriElem = document.getElementById('agriPrices');
  const fuelElem = document.getElementById('fuelPrices');
  if (agriElem) agriElem.textContent = "배추: 3,000원, 감자: 2,500원";
  if (fuelElem) fuelElem.textContent = "휘발유: 1,700원/L, 경유: 1,600원/L";
}
