package br.com.pizzaAPI.adapters.input.mapper;

import br.com.pizzaAPI.adapters.input.models.request.PizzaRequest;
import br.com.pizzaAPI.adapters.input.models.response.PizzaResponse;
import br.com.pizzaAPI.adapters.output.entity.PizzaEntity;
import br.com.pizzaAPI.domain.entity.Pizza;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PizzaMapper {
    // Instância do mapeador
    PizzaMapper INSTANCE = Mappers.getMapper(PizzaMapper.class);

    // Mapeia PizzaEntity para PizzaRequest
    PizzaRequest pizzaEntityToPizzaRequest(PizzaEntity pizzaEntity);

    // Mapeia PizzaRequest para PizzaEntity
    PizzaEntity pizzaRequestToPizzaEntity(PizzaRequest pizzaRequest);

    // Mapeia PizzaEntity para Pizza
    Pizza pizzaEntityToPizza(PizzaEntity pizzaEntity);

    // Mapeia Pizza para PizzaEntity
    PizzaEntity pizzaToPizzaEntity(Pizza pizza);

    // Mapeia lista de PizzaEntity para lista de PizzaRequest
    List<PizzaRequest> pizzaEntitiesToPizzaRequests(List<PizzaEntity> pizzaEntities);

    // Mapeia lista de PizzaEntity para lista de Pizza (entidade de domínio)
    List<Pizza> pizzaEntitiesToPizzas(List<PizzaEntity> pizzaEntities);

    // Mapeia PizzaResponse a partir de Pizza
    PizzaResponse pizzaToPizzaResponse(Pizza pizza);

}
