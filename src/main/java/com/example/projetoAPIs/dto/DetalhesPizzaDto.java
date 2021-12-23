package com.example.projetoAPIs.dto;

import java.time.LocalDateTime;

import com.example.projetoAPIs.model.Pizza;
import com.example.projetoAPIs.model.Sabor;

public class DetalhesPizzaDto {

	private Long id;
	private Sabor sabor;
	private LocalDateTime dataCriacao;
	private Long valor;
	private String peso;

	public DetalhesPizzaDto(Pizza pizza) {
		this.id = pizza.getId();
		this.sabor = pizza.getSabor();
		this.dataCriacao = pizza.getDataCriacao();
		this.valor = pizza.getValor();
		this.peso = pizza.getPeso();
	}

	public Long getId() {
		return id;
	}

	public Sabor getSabor() {
		return sabor;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	public Long getValor() {
		return valor;
	}

	public String getPeso() {
		return peso;
	}

}
