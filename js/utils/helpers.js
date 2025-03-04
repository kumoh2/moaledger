// js/utils/helpers.js

const BACKEND_URL = "https://moa-ledger.onrender.com";

export async function login(userId, password) {
  const response = await fetch(`${BACKEND_URL}/auth/login`, {
    method: "POST",
    credentials: "include",
    headers: { "Content-Type": "application/x-www-form-urlencoded" },
    body: new URLSearchParams({ userId, password })
  });
  return await response.text();
}

export async function logout() {
  await fetch(`${BACKEND_URL}/auth/logout`, {
    method: "POST",
    credentials: "include"
  });
}

export async function checkSession() {
  const response = await fetch(`${BACKEND_URL}/auth/secure-data`, {
    method: "GET",
    credentials: "include"
  });
  return await response.text();
}

export async function loadHTML() {
  const response = await fetch('../common/common.html');
  const html = await response.text();
  document.getElementById('top-section').innerHTML = html;
}

export async function setupSelectGroup(currentUser) {
  // 그룹 목록 조회: GET /group/user?userId=xxx
  const response = await fetch(`${BACKEND_URL}/group/user?userId=${currentUser}`, {
    method: "GET",
    credentials: "include"
  });
  const groups = await response.json(); // 예: [{ groupId, name }, ...]
  const groupSelect = document.getElementById('groupSelect');
  if (!groupSelect) {
    console.error('groupSelect 요소가 없습니다.');
    return;
  }
  groupSelect.innerHTML = '';
  groups.forEach(group => {
    const option = document.createElement('option');
    option.value = group.groupId;
    option.textContent = group.name;
    groupSelect.appendChild(option);
  });
}

export async function setupnickName(currentUser) {
  const response = await fetch(`${BACKEND_URL}/auth/userinfo?userId=${currentUser}`, {
    method: "GET",
    credentials: "include"
  });
  const data = await response.json();
  const nicknameEl = document.getElementById('nicknameSpan');
  if (nicknameEl && data.nickname) {
    nicknameEl.textContent = data.nickname + ' 님';
  }
}
