package br.com.pizzaAPI.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ClientsController {

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "/clients")
    public void listaClientes() {
    }

}