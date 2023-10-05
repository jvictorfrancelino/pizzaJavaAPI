package br.com.pizzaAPI.service;

import br.com.pizzaAPI.entity.PizzaEntity;
import br.com.pizzaAPI.exception.DefaultErrorException;
import br.com.pizzaAPI.model.PizzaDTO;
import br.com.pizzaAPI.model.response.PizzaResponse;
import br.com.pizzaAPI.repository.PizzaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    @Autowired
    private PizzaRepository repository;

    public PizzaResponse getPizzas(int id) throws Exception {
        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setPizzas(new ArrayList<>());

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
                    pizzaResponse.getPizzas().add(pizza);
                    pizzaResponse.setMensagem("Pizzas returned successfully!");
                    pizzaResponse.setCodRetorno(200);
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
                    pizzaResponse.getPizzas().add(pizza);
                    pizzaResponse.setMensagem("Pizza returned successfully!");
                    pizzaResponse.setCodRetorno(200);
                } else {
                    pizzaResponse.setMensagem("Pizza ID does not exist in the database!");
                    pizzaResponse.setCodRetorno(404);
                    return pizzaResponse;
                }
            } catch (Exception ex) {
                throw new Exception(ex.getCause());
            }
        }
        return pizzaResponse;
    }

    public PizzaResponse createPizza(PizzaDTO pizzaDto) throws Exception {
        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setPizzas(new ArrayList<>());

        try {
            PizzaEntity pizzaEntity = new PizzaEntity();


            pizzaEntity.setFlavor(trimIfNotNull(pizzaDto.getFlavor()));
            pizzaEntity.setBorder(trimIfNotNull(pizzaDto.getBorder()));
            pizzaEntity.setPrice(pizzaDto.getPrice());
            pizzaEntity.setImg(trimIfNotNull(pizzaDto.getImg()));
            repository.save(pizzaEntity);
            PizzaDTO pizza = new PizzaDTO(pizzaEntity.getId(), pizzaEntity.getFlavor(), pizzaEntity.getBorder(), pizzaEntity.getPrice(), pizzaEntity.getImg());
            pizzaResponse.getPizzas().add(pizza);
        } catch (Exception ex) {
            throw new Exception(ex.getCause());
        }
        pizzaResponse.setMensagem("Pizza created successfully!");
        pizzaResponse.setCodRetorno(201);
        return pizzaResponse;
    }

    public PizzaResponse updatePizza(PizzaDTO pizzaDto) throws Exception {
        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setPizzas(new ArrayList<>());
        String teste = "teste";
        try {
            Optional<PizzaEntity> pizzaEntityCheck = repository.findById( pizzaDto.getId());
            if (!pizzaEntityCheck.isPresent()) {
                pizzaResponse.setMensagem("Pizza ID does not exist in the database!");
                pizzaResponse.setCodRetorno(404);
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
        pizzaResponse.setMensagem("Pizza updated successfully!");
        pizzaResponse.setCodRetorno(201);
        pizzaResponse.getPizzas().add(pizzaDto);
        return pizzaResponse;
    }

    public PizzaResponse deletePizza(int id) {
        PizzaResponse pizzaResponse = new PizzaResponse();
        pizzaResponse.setPizzas(new ArrayList<>());
        try {
            Optional<PizzaEntity> pizzaEntity = repository.findById(id);
            if (!pizzaEntity.isPresent()) {
                pizzaResponse.setMensagem("Pizza ID does not exist in the database!");
                pizzaResponse.setCodRetorno(404);
                return pizzaResponse;
            }
            repository.deleteById(id);
        } catch (Exception ex) {
            throw new DefaultErrorException("Error to delete a pizza", HttpStatus.I_AM_A_TEAPOT.toString());
        }
        pizzaResponse.setMensagem("Pizza deleted successfully!");
        pizzaResponse.setCodRetorno(200);
        return pizzaResponse;
    }

    public static String trimIfNotNull(String input) {
        return input != null ? input.trim() : null;
    }
}
