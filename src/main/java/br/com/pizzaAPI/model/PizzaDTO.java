package br.com.pizzaAPI.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDTO {

    private int id;
    private String flavor;
    private String border;
    private double price;
    private String img;
}
