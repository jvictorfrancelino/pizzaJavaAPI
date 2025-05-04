package br.com.pizzaAPI.domain.useCase;

import br.com.pizzaAPI.adapters.input.models.request.PizzaRequest;
import br.com.pizzaAPI.adapters.input.models.response.PizzaResponse;

public interface PizzaUseCase {
    PizzaResponse getPizzas(int id) throws Exception;
    PizzaResponse createPizza(PizzaRequest pizzaRequest) throws Exception;
    PizzaResponse updatePizza(PizzaRequest pizzaRequest) throws Exception;
    PizzaResponse deletePizza(int id) throws Exception;
}
