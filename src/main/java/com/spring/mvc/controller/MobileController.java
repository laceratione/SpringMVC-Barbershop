package com.spring.mvc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.mvc.entity.Client;
import com.spring.mvc.entity.Role;
import com.spring.mvc.entity.User;
import com.spring.mvc.entity.UserTmp;
import com.spring.mvc.service.ClientService;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

//обработка запросов с мобильного клиента
@Controller
public class MobileController {
    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    //вход в приложение с моб. клиента
    @PostMapping("/loginMobile")
    public ResponseEntity<String> loginMobile(@RequestBody String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            UserTmp userTmp = objectMapper.readValue(json, UserTmp.class);

            User user = userService.findByUsername(userTmp.getUsername());
            if (user == null){
                System.out.println("Пользователь не найден");
                return new ResponseEntity<String>("User not found", HttpStatus.OK);
            }

            boolean check = passwordEncoder.matches(userTmp.getPassword(), user.getPassword());
            if (check == false) {
                System.out.println("Пароль не верный");
                return new ResponseEntity<String>("Password invalid", HttpStatus.OK);
            }

            boolean checkAdmin = isAdmin(user);
            if (checkAdmin == true) {
                System.out.println("ИМЕЕТ РОЛЬ АДМИНИСИТРАТОРА");
                return new ResponseEntity<String>("ROLE_ADMIN", HttpStatus.OK);
            } else {
                System.out.println("ИМЕЕТ РОЛЬ ПОЛЬЗОВАТЕЛЯ");
                return new ResponseEntity<String>("ROLE_USER", HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error", HttpStatus.OK);
        }
    }

    //получение списка пользователей
    @GetMapping("/getUsersJson")
    public ResponseEntity<String> getUsersJson() {
        try {
            List<User> users = userService.allUsers();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(users);
            return new ResponseEntity<String>(json, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error", HttpStatus.FORBIDDEN);
        }
    }

    //получение списка заявок
    @GetMapping("/getNotesJson")
    public ResponseEntity<String> getNotesJson() {
        try {
            List<Client> clients = clientService.listAll();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(clients);
            return new ResponseEntity<String>(json, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error", HttpStatus.FORBIDDEN);
        }
    }

    //история заявок для конкретного пользователя
    @GetMapping("/getHistoryNotesJson")
    public ResponseEntity<String> getHistoryNotesJson(@RequestParam String username) {
        try {
            List<Client> clients = getNotesByUsername(username);
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(clients);
            return new ResponseEntity<String>(json, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error", HttpStatus.FORBIDDEN);
        }
    }

    //сохранение заявки
    @PostMapping("/saveMobile")
    public ResponseEntity<String> saveMobile(@RequestBody String jsonClient) {
        try {
            System.out.println(jsonClient);
            ObjectMapper objectMapper = new ObjectMapper();
            Client client = objectMapper.readValue(jsonClient, Client.class);
            clientService.save(client);
            return new ResponseEntity<String>("Note saved", HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error", HttpStatus.FORBIDDEN);
        }
    }

    //удаление пользователя
    @GetMapping("/deleteUserMob")
    public ResponseEntity<String> deleteUser(@RequestParam int id) {
        try {
            userService.deleteById(id);
            return new ResponseEntity<String>("User deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error", HttpStatus.FORBIDDEN);
        }
    }

    //удаление заявки
    @GetMapping("/deleteNoteMob")
    public ResponseEntity<String> deleteNote(@RequestParam int id) {
        try {
            clientService.delete(id);
            return new ResponseEntity<String>("Note deleted", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error", HttpStatus.FORBIDDEN);
        }
    }

    //регистрация пользователя
    @PostMapping("/registrationMob")
    public ResponseEntity<String> addUser(@RequestBody String userTmp) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(userTmp, User.class);

            if (userService.save(user))
                return new ResponseEntity<String>("User saved", HttpStatus.OK);
            else
                return new ResponseEntity<String>("User already exist", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<String>("Error", HttpStatus.FORBIDDEN);
        }
    }

    //поиск заявок по имени пользователя
    private List<Client> getNotesByUsername(String username) {
        List<Client> filter = new ArrayList<>();
        for (Client client : clientService.listAll()) {
            if (client.getName().equals(username)) {
                filter.add(client);
            }
        }
        return filter;
    }

    //проверить на роль админа/менеджера
    private boolean isAdmin(User user){
        boolean isAdmin = false;
        Set<Role> roles = user.getRoles();
        for (Iterator<Role> iter = roles.iterator(); iter.hasNext(); ) {
            Role r = iter.next();
            String name = r.getName();
            if (r.getName().equals("ROLE_ADMIN")) {
                isAdmin = true;
                break;
            }
        }
        return  isAdmin;
    }

}