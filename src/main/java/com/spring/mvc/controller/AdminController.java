package com.spring.mvc.controller;

import com.spring.mvc.entity.Client;
import com.spring.mvc.service.ClientService;
import com.spring.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//admin - oleg, pass - 123
@Controller
public class AdminController {
    @Autowired
    private UserService userService;

    @Autowired
    private ClientService clientService;

    //admin может просматривать всех пользователей
    @GetMapping("/admin")
    public String userList(Model model) {
        model.addAttribute("users", userService.allUsers());
        return "admin";
    }

    //admin может просматривать все заявки на услуги
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String getAllNotes(Model model) {
        List<Client> clients = clientService.listAll();
        model.addAttribute("clients", clients);
        return "all_notes";
    }

    //ниже операции, которые может выполнить user в url - исправить
    //admin может искать заявки по ключевым словам
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String searchNote(Model model, @RequestParam String keyword) {
        List<Client> clients = clientService.search(keyword);
        model.addAttribute("clients", clients);
        return "search";
    }

    //admin может редактировать записи
    @RequestMapping(value = "/editNote", method = RequestMethod.GET)
    public String editNote(Model model, @RequestParam int id) {
        Client client = clientService.get(id);
        model.addAttribute("client", client);
        return "edit_note";
    }

    //admin может удалять записи
    @RequestMapping(value = "/deleteNote", method = RequestMethod.GET)
    public String deleteNote(@RequestParam int id) {
        clientService.delete(id);
        return "redirect:/clients";
    }

    //admin может удалять пользователей
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String deleteUser(@RequestParam int id) {
        userService.deleteById(id);
        return "redirect:/admin";
    }

}
