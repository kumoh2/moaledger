import { login } from '../../js/utils/helpers.js';

document.addEventListener('DOMContentLoaded', () => {
  const loginForm = document.getElementById('loginForm');
  if (loginForm) {
    loginForm.addEventListener('submit', async (e) => {
      e.preventDefault();

      const username = document.getElementById('login-username').value.trim();
      const password = document.getElementById('login-password').value.trim();

      try {
        const result = await login(username, password); // ✅ 백엔드 API 호출

        if (result.includes("Login successful")) {
          alert("로그인 성공!");
          window.location.href = '../main/main.html'; // ✅ 로그인 성공 시 메인 페이지 이동
        } else {
          alert("아이디 또는 비밀번호가 올바르지 않습니다.");
        }
      } catch (error) {
        console.error("로그인 중 오류 발생:", error);
        alert("로그인 실패! 다시 시도해주세요.");
      }
    });
  }
});
