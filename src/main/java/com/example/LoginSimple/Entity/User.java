package com.example.LoginSimple.Entity;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
     private String username;
     private String useremail;
     private String password;
      public User(){}
    private String role="User";
    public User( String username ,String useremail,String password, String role){
          this.username=username;
          this.useremail=useremail;
          this.password=password;
          this.role=role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}



