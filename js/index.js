import { getCurrentUser } from './utils/helpers.js';

document.addEventListener('DOMContentLoaded', () => {
    const currentUser = getCurrentUser();
    if (currentUser) {
        // 로그인 상태라면 메인 페이지로 이동
        window.location.href = './views/main/main.html';
    } else {
        // 로그인 안 되어 있으면 로그인 페이지로 이동
        window.location.href = './views/user/login.html';
    }
});
