package br.com.pizzaAPI.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Pizzas")
public class PizzaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "flavor")
    private String flavor;
    @Column(name = "border")
    private String border;
    @Column(name = "price")
    private double price;
    @Column(name = "img")
    private String img;
}


