package br.com.pizzaAPI.controller;

import br.com.pizzaAPI.model.User;
import br.com.pizzaAPI.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(value = "/user")
    public void postUser(@RequestBody User user) {
        service.createUser(user);
    }
}
