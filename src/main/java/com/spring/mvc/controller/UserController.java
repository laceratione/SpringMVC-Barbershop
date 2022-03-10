package com.spring.mvc.controller;

import com.spring.mvc.entity.Client;
import com.spring.mvc.service.ClientService;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    //user может создать заявку в салон
    @RequestMapping("/newNote")
    public String createNote(Model model) {
        model.addAttribute("client", new Client());
        return "new_note";
    }
}
