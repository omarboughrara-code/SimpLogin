package com.example.LoginSimple.Service;


import com.example.LoginSimple.Entity.User;
import com.example.LoginSimple.Repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }
     public User login(String useremail,String password){
        User user =userRepository.findByUseremail(useremail);
        if(user !=null && user.getPassword().equals(password)){
            return user;
        }
        return  null;
     }

     public  User registre(User user){
        return userRepository.save(user);
     }
      public  User updateUser(Long id , User updateUser){
        return userRepository.findById(id).map(user -> {
            user.setUsername(updateUser.getUsername());
            user.setUseremail((updateUser.getUseremail()));
            user.setPassword(updateUser.getPassword());
            return userRepository.save(user);
        }).orElseThrow(()-> new RuntimeException("User not found with  id  "  + id));
      }


}
