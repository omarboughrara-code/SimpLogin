package com.example.LoginSimple.Service;


import com.example.LoginSimple.Entity.PasswordResetToken;
import com.example.LoginSimple.Entity.User;
import com.example.LoginSimple.Repository.PasswordResetTokenRepository;
import com.example.LoginSimple.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PasswordResetService {
    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

     public  String createPasswordResetToken(String email){
         Optional<User> userOpt= Optional.ofNullable(userRepository.findByUseremail(email));
         if (userOpt.isEmpty()){
             throw new RuntimeException("user not found");
         }
         String token= UUID.randomUUID().toString();
         PasswordResetToken resetToken=new PasswordResetToken(token,email, LocalDateTime.now().plusMinutes(15)
         );
         tokenRepository.save(resetToken);
         return "http://localhost:8080/auth/reset-password?token=" + token;
     }
    public boolean resetPassword(String token, String newPassword) {
        Optional<PasswordResetToken> tokenOpt = tokenRepository.findByToken(token);
        if (tokenOpt.isEmpty()) return false;

        PasswordResetToken resetToken = tokenOpt.get();

        if (resetToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            return false; // expired
        }

        Optional<User> userOpt = Optional.ofNullable(userRepository.findByUseremail(resetToken.getUseremail()));
        if (userOpt.isEmpty()) return false;

        User user = userOpt.get();
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        tokenRepository.delete(resetToken); // delete token after use
        return true;
    }

}
