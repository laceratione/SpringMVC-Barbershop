package com.spring.mvc.controller;

import com.spring.mvc.entity.Client;
import com.spring.mvc.service.ClientService;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ClientController {
    @Autowired
    private ClientService clientService;

    @Autowired
    private UserService userService;

    //сохранение заявки
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNote(@ModelAttribute("client") Client client) {
        clientService.save(client);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))){
            return "redirect:/clients";
        }
        else {
            return "redirect:/";
        }
    }

}
