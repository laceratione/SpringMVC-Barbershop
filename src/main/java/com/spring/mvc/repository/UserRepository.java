package com.spring.mvc.repository;

import com.spring.mvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    //поиск пользователя по имени
    public User findByUsername(String username);
}
