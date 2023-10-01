package br.com.pizzaAPI.model.response;

import br.com.pizzaAPI.model.PizzaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaResponse {

    private int codRetorno;
    private String mensagem;
    private List<PizzaDTO> pizzas;
}
