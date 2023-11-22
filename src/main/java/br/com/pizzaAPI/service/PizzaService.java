package br.com.pizzaAPI.service;

import br.com.pizzaAPI.controller.PizzaController;
import br.com.pizzaAPI.entity.PizzaEntity;
import br.com.pizzaAPI.exception.DefaultErrorException;
import br.com.pizzaAPI.model.PizzaDTO;
import br.com.pizzaAPI.model.response.PizzaResponse;
import br.com.pizzaAPI.repository.PizzaRepository;
import br.com.pizzaAPI.utils.Logs;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PizzaService.class);

    @Autowired
    private PizzaRepository repository;

    public PizzaResponse getPizzas(int id) throws Exception {
        LOGGER.info("Executing method: getPizzas with id: {}", id);
        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setPizzasList(new ArrayList<>());

        if (id == 0) {
            try {
                List<PizzaEntity> listaPizzasEntity = repository.findAll();
                for (PizzaEntity pizzaEntity : listaPizzasEntity) {
                    PizzaDTO pizza = new PizzaDTO();
                    pizza.setId(pizzaEntity.getId());
                    pizza.setFlavor(pizzaEntity.getFlavor());
                    pizza.setFlavor(pizzaEntity.getFlavor());
                    pizza.setBorder(pizzaEntity.getBorder());
                    pizza.setPrice(pizzaEntity.getPrice());
                    if (pizzaEntity.getImg() != null) {
                        pizza.setImg(pizzaEntity.getImg());
                    } else {
                        pizza.setImg("");
                    }
                    pizza.setImg(trimIfNotNull(pizzaEntity.getImg()));
                    pizzaResponse.getPizzasList().add(pizza);
                    pizzaResponse.setMessageCode("Pizzas returned successfully!");
                    pizzaResponse.setReturnCode(200);
                }
            } catch (Exception ex) {
                throw new Exception(ex.getCause());
            }
        } else {
            try {
                Optional<PizzaEntity> pizzaEntity = repository.findById(id);
                PizzaDTO pizza = new PizzaDTO();
                if (pizzaEntity.isPresent()) {
                    pizza.setId(pizzaEntity.get().getId());
                    pizza.setFlavor(pizzaEntity.get().getFlavor());
                    pizza.setBorder(pizzaEntity.get().getBorder());
                    pizza.setPrice(pizzaEntity.get().getPrice());
                    pizza.setImg(pizzaEntity.get().getImg());
                    pizzaResponse.getPizzasList().add(pizza);
                    pizzaResponse.setMessageCode("Pizza returned successfully!");
                    pizzaResponse.setReturnCode(200);
                } else {
                    pizzaResponse.setMessageCode("Pizza ID does not exist in the database!");
                    pizzaResponse.setReturnCode(404);
                    return pizzaResponse;
                }
            } catch (Exception ex) {
                throw new Exception(ex.getCause());
            }
        }
        return pizzaResponse;
    }

    public PizzaResponse createPizza(PizzaDTO pizzaDto) throws Exception {
        Logs.logDtoService("createPizza", new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(pizzaDto), PizzaService.class.getName());

        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setPizzasList(new ArrayList<>());

        try {
            PizzaEntity pizzaEntity = new PizzaEntity();
            pizzaEntity.setFlavor(trimIfNotNull(pizzaDto.getFlavor()));
            pizzaEntity.setBorder(trimIfNotNull(pizzaDto.getBorder()));
            pizzaEntity.setPrice(pizzaDto.getPrice());
            pizzaEntity.setImg(trimIfNotNull(pizzaDto.getImg()));
            repository.save(pizzaEntity);
            PizzaDTO pizza = new PizzaDTO(pizzaEntity.getId(), pizzaEntity.getFlavor(), pizzaEntity.getBorder(), pizzaEntity.getPrice(), pizzaEntity.getImg());
            pizzaResponse.getPizzasList().add(pizza);
        } catch (Exception ex) {
            throw new Exception(ex.getCause());
        }
        pizzaResponse.setMessageCode("Pizza created successfully!");
        pizzaResponse.setReturnCode(201);
        return pizzaResponse;
    }

    public PizzaResponse updatePizza(PizzaDTO pizzaDto) throws Exception {
        Logs.logDtoService("updatePizza", new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(pizzaDto), PizzaController.class.getName());

        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setPizzasList(new ArrayList<>());
        String teste = "teste";
        try {
            Optional<PizzaEntity> pizzaEntityCheck = repository.findById( pizzaDto.getId());
            if (!pizzaEntityCheck.isPresent()) {
                pizzaResponse.setMessageCode("Pizza ID does not exist in the database!");
                pizzaResponse.setReturnCode(404);
                return pizzaResponse;
            }
            PizzaEntity pizzaEntity = new PizzaEntity();
            pizzaEntity.setId(pizzaDto.getId());
            pizzaEntity.setFlavor(trimIfNotNull(pizzaDto.getFlavor()));
            pizzaEntity.setBorder(trimIfNotNull(pizzaDto.getBorder()));
            pizzaEntity.setPrice(pizzaDto.getPrice());
            pizzaEntity.setImg(trimIfNotNull(pizzaDto.getImg()));
            repository.save(pizzaEntity);
        } catch (Exception ex) {
            throw new Exception(ex.getCause());
        }
        pizzaResponse.setMessageCode("Pizza updated successfully!");
        pizzaResponse.setReturnCode(201);
        pizzaResponse.getPizzasList().add(pizzaDto);
        return pizzaResponse;
    }

    public PizzaResponse deletePizza(int id) throws JsonProcessingException {
        Logs.logDtoService("deletePizza", new ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(id), PizzaController.class.getName());

        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setPizzasList(new ArrayList<>());
        try {
            Optional<PizzaEntity> pizzaEntity = repository.findById(id);
            if (!pizzaEntity.isPresent()) {
                pizzaResponse.setMessageCode("Pizza ID does not exist in the database!");
                pizzaResponse.setReturnCode(404);
                return pizzaResponse;
            }
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new DefaultErrorException("Error to delete a pizza", HttpStatus.I_AM_A_TEAPOT.toString());
        }
        pizzaResponse.setMessageCode("Pizza deleted successfully!");
        pizzaResponse.setReturnCode(200);
        return pizzaResponse;
    }

    public static String trimIfNotNull(String input) {
        return input != null ? input.trim() : null;
    }
}
