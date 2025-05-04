package br.com.pizzaAPI.domain.useCase;

import br.com.pizzaAPI.adapters.input.mapper.PizzaMapper;
import br.com.pizzaAPI.adapters.input.models.request.PizzaRequest;
import br.com.pizzaAPI.adapters.input.models.response.PizzaResponse;
import br.com.pizzaAPI.adapters.output.entity.PizzaEntity;
import br.com.pizzaAPI.ports.output.repository.PizzaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaUseCaseImpl implements PizzaUseCase {

    private final PizzaRepository pizzaRepository;

    public PizzaUseCaseImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }

    @Override
    public PizzaResponse getPizzas(int id) throws Exception {
        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setPizzasList(new ArrayList<>());

        if (id == 0) {
            List<PizzaEntity> pizzaEntities = pizzaRepository.findAll();
            List<PizzaRequest> pizzaRequests = PizzaMapper.INSTANCE.pizzaEntitiesToPizzaRequests(pizzaEntities);
            pizzaResponse.getPizzasList().addAll(pizzaRequests);
        } else {
            Optional<PizzaEntity> pizzaEntity = pizzaRepository.findById(id);
            if (pizzaEntity.isPresent()) {
                PizzaRequest pizzaRequest = PizzaMapper.INSTANCE.pizzaEntityToPizzaRequest(pizzaEntity.get());
                pizzaResponse.getPizzasList().add(pizzaRequest);
            } else {
                pizzaResponse.setMessageCode("Pizza ID does not exist!");
                pizzaResponse.setReturnCode(404);
            }
        }

        return pizzaResponse;
    }

    @Override
    public PizzaResponse createPizza(PizzaRequest pizzaRequest) throws Exception {
        PizzaEntity pizzaEntity = PizzaMapper.INSTANCE.pizzaRequestToPizzaEntity(pizzaRequest);
        pizzaRepository.save(pizzaEntity);

        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setMessageCode("Pizza created successfully!");
        pizzaResponse.setReturnCode(201);
        return pizzaResponse;
    }

    @Override
    public PizzaResponse updatePizza(PizzaRequest pizzaRequest) throws Exception {
        Optional<PizzaEntity> existingPizza = pizzaRepository.findById(pizzaRequest.getId());
        if (existingPizza.isPresent()) {
            PizzaEntity pizzaEntity = PizzaMapper.INSTANCE.pizzaRequestToPizzaEntity(pizzaRequest);
            pizzaRepository.save(pizzaEntity);

            PizzaResponse pizzaResponse = new PizzaResponse();
            pizzaResponse.setMessageCode("Pizza updated successfully!");
            pizzaResponse.setReturnCode(200);
            return pizzaResponse;
        } else {
            PizzaResponse pizzaResponse = new PizzaResponse();
            pizzaResponse.setMessageCode("Pizza ID does not exist!");
            pizzaResponse.setReturnCode(404);
            return pizzaResponse;
        }
    }

    @Override
    public PizzaResponse deletePizza(int id) throws Exception {
        Optional<PizzaEntity> pizzaEntity = pizzaRepository.findById(id);
        if (pizzaEntity.isPresent()) {
            pizzaRepository.deleteById(id);

            PizzaResponse pizzaResponse = new PizzaResponse();
            pizzaResponse.setMessageCode("Pizza deleted successfully!");
            pizzaResponse.setReturnCode(200);
            return pizzaResponse;
        } else {
            PizzaResponse pizzaResponse = new PizzaResponse();
            pizzaResponse.setMessageCode("Pizza ID does not exist!");
            pizzaResponse.setReturnCode(404);
            return pizzaResponse;
        }
    }
}
