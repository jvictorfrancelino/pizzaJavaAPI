package br.com.pizzaAPI.controller;

import br.com.pizzaAPI.model.EmployeeDTO;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class EmployeesController {

    @CrossOrigin(origins = "http://localhost:5173")
    @GetMapping(value = "/employees")
    public List<EmployeeDTO> listaFuncionarios() {
        List<EmployeeDTO> employeeDTOList = new ArrayList<>();
        return employeeDTOList;
    }
}