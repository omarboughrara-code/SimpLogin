package com.example.LoginSimple.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class PasswordResetToken {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
private  String token;
private  String useremail;
private LocalDateTime expiryDate;
public PasswordResetToken(){}
    public PasswordResetToken(String token,String useremail,LocalDateTime expiryDate){
    this.token=token;
    this.useremail=useremail;
    this.expiryDate=expiryDate;

    }
     public  long getId(){return  id;}

    public void setId(Long id) {
        this.id = id;
    }
    public void setToken(String token){this.token=token;}

    public String getUseremail() {
        return useremail;
    }

    public void setUseremail(String useremail) {
        this.useremail = useremail;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}
