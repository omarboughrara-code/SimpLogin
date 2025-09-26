package com.example.LoginSimple.Controller;


import com.example.LoginSimple.Entity.User;
import com.example.LoginSimple.Service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class UserController {

    private final UserService userService;
     public UserController(UserService userService){
         this.userService=userService;
     }
     @PostMapping("/register")
    public User registre(@RequestBody User user){
         return userService.registre(user);

     }

     @PostMapping("/login")
    public String login(@RequestBody User user){
         User validUser=userService.login(user.getUseremail(), user.getPassword());
         if(validUser !=null){
             return "login successful !! Welcome" + validUser.getUsername();
         }
         return "invalid username or password !!!" ;
     }

     @PutMapping("/update/{id}")
    public  User updateUser(@PathVariable Long id  ,@RequestBody User updateUser){
         return userService.updateUser(id,updateUser);

     }
    @GetMapping("/loginSuccess")
    public String loginSuccess(@AuthenticationPrincipal OAuth2User oauth2User) {
        // Get user info from Google
        String name = oauth2User.getAttribute("name");
        String email = oauth2User.getAttribute("email");
        return "Hello " + name + ", your email is " + email;
    }

}
