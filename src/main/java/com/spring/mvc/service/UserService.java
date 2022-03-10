package com.spring.mvc.service;

import com.spring.mvc.entity.Role;
import com.spring.mvc.entity.User;
import com.spring.mvc.repository.RoleRepository;
import com.spring.mvc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
//@Transactional из-за него Spring не мог создать bean userService в AdminController
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findById(int id) {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(new User());
    }

    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }

    public boolean deleteById(int id) {
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
        //void userRepository.deleteById(id);
    }

    public boolean save(User user) {
        User userFromRepo = userRepository.findByUsername(user.getUsername());

        if (userFromRepo != null) {
            return false;
        }

        //по умолчанию пользватель имеет роль ROLE_USER
        user.setRoles(Collections.singleton(new Role(1, "ROLE_USER")));
        //хэширование пароля
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

}
