package com.example.LoginSimple.Controller;


import com.example.LoginSimple.Service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class PasswordResetController {

    @Autowired
    private PasswordResetService passwordResetService;


    @PostMapping("/forgot-password")
    public String forgotPassword(@RequestParam String email){
        String resetLink=passwordResetService.createPasswordResetToken(email);
        return "Rest  link  snet  to  email  : " + resetLink;
    }
    @PostMapping("/reset-password")
    public String resetPassword(@RequestParam String token, @RequestParam String newPassword) {
        boolean success = passwordResetService.resetPassword(token, newPassword);
        return success ? "Password reset successful!" : "Invalid or expired token!";
    }
}
