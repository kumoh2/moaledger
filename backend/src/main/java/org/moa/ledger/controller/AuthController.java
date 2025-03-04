package org.moa.ledger.controller;

import jakarta.servlet.http.HttpSession;
import org.moa.ledger.model.User;
import org.moa.ledger.service.AuthService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String userId, @RequestParam String password, HttpSession session) {
        boolean success = authService.login(userId, password, session);
        return success ? "Login successful" : "Invalid username or password";
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "Logged out successfully!";
    }

    @PostMapping("/update")
    public String update(@RequestBody User user, HttpSession session) {
        String currentUser = authService.getLoggedInUser(session);
        if (currentUser == null || !currentUser.equals(user.getUserId())) {
            return "Unauthorized";
        }
        boolean result = authService.updateProfile(user);
        return result ? "Profile updated" : "Update failed";
    }

    @PostMapping("/withdraw")
    public String withdraw(HttpSession session) {
        String userId = authService.getLoggedInUser(session);
        if (userId == null) {
            return "Not logged in";
        }
        boolean result = authService.withdraw(userId);
        if (result) {
            session.invalidate();
            return "Account deleted";
        }
        return "Deletion failed";
    }

    @GetMapping("/userinfo")
    public User getUserInfo(@RequestParam String userId) {
        // 단순 조회
        User user = authService.getLoggedInUser(new HttpSession() {
            @Override public String getId() { return userId; }
            // 여기서는 간단히 userId를 반환하는 dummy session을 사용합니다.
            // 실제 구현에서는 session에서 사용자 ID를 가져와 UserMapper를 통해 조회해야 합니다.
            // 이 메서드는 예제이므로 단순 반환.
        });
        // 실제로는 userMapper.findByUserId(userId) 호출해야 함
        user = authService.getLoggedInUser(null) != null ? null : null; // Placeholder
        // 실제 구현에서는 백엔드에서 사용자 정보를 조회하여 반환합니다.
        // 여기서는 간단히 null 반환.
        return user;
    }

    @GetMapping("/secure-data")
    public String secureData(HttpSession session) {
        String user = authService.getLoggedInUser(session);
        return user != null ? "Hello, " + user + "!" : "Unauthorized!";
    }
}
