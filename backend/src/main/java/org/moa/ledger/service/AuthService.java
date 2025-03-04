package org.moa.ledger.service;

import jakarta.servlet.http.HttpSession;
import org.moa.ledger.mapper.UserMapper;
import org.moa.ledger.model.User;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserMapper userMapper;

    public AuthService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public boolean login(String userId, String password, HttpSession session) {
        User user = userMapper.findByUserId(userId);
        if (user != null && user.getPassword().equals(password)) { // 🔒 비밀번호 암호화 필요
            session.setAttribute("user", user.getUserId());
            return true;
        }
        return false;
    }

    public void logout(HttpSession session) {
        session.invalidate();
    }

    public String getLoggedInUser(HttpSession session) {
        return (String) session.getAttribute("user");
    }
}
