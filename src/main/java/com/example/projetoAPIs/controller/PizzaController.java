package com.example.projetoAPIs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.projetoAPIs.dto.DetalhesPizzaDto;
import com.example.projetoAPIs.dto.PizzaDto;
import com.example.projetoAPIs.model.Pizza;
import com.example.projetoAPIs.repository.PizzaRepository;

@RestController
@RequestMapping(value="/pizzas")
public class PizzaController {
	
	@Autowired
	private PizzaRepository pizzaRepository;

	@GetMapping
	public List<PizzaDto> listar(String saborPizza){
		if(saborPizza == null) {
			List<Pizza> pizzas = pizzaRepository.findAll();
			return PizzaDto.converter(pizzas);
		}else {
			List<Pizza> pizzas = pizzaRepository.findBySabor(saborPizza);
			return PizzaDto.converter(pizzas);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<DetalhesPizzaDto> detalhar(@PathVariable Long id) {
		Optional<Pizza> pizza = pizzaRepository.findById(id);
		if(pizza.isPresent()) {
			return ResponseEntity.ok(new DetalhesPizzaDto(pizza.get()));
		}
		return ResponseEntity.notFound().build();
	}
	
}
