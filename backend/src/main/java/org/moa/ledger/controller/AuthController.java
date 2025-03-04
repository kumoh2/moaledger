package org.moa.ledger.controller;

import jakarta.servlet.http.HttpSession;
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

    @GetMapping("/secure-data")
    public String secureData(HttpSession session) {
        String user = authService.getLoggedInUser(session);
        return (user != null) ? "Hello, " + user + "!" : "Unauthorized! Please log in.";
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        authService.logout(session);
        return "Logged out successfully!";
    }
}
