package com.example.LoginSimple.Repository;

import com.example.LoginSimple.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUseremail(String useremail);
}
