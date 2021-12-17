package com.example.projetoAPIs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.projetoAPIs.model.Pizza;

public interface PizzaRepository extends JpaRepository<Pizza, Long>{

	List<Pizza> findBySabor(String saborPizza);

}
