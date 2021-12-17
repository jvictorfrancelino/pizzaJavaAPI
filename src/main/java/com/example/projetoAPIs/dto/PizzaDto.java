package com.example.projetoAPIs.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.example.projetoAPIs.model.Pizza;
import com.example.projetoAPIs.model.Sabor;

public class PizzaDto {

	public PizzaDto(Pizza pizza) {
		this.id = pizza.getId();
		this.sabor = pizza.getSabor();
		this.valor = pizza.getValor();
	}

	private Long id;
	private String nome;
	private Sabor sabor;
	private Long valor;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public Long getValor() {
		return valor;
	}

	public static List<PizzaDto> converter(List<Pizza> pizzas){
		return pizzas.stream().map(PizzaDto::new).collect(Collectors.toList());
	}
	
}
