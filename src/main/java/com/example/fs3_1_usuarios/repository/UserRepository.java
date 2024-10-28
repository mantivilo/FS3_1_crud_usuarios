package com.example.fs3_1_usuarios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.fs3_1_usuarios.model.User;

public interface UserRepository extends JpaRepository<User, Long>  {
    
}
