package br.com.pizzaAPI.controller;

import java.net.URI;

import br.com.pizzaAPI.model.PizzaDTO;
import br.com.pizzaAPI.model.response.PizzaResponse;
import br.com.pizzaAPI.service.PizzaService;
import br.com.pizzaAPI.utils.Logs;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
@Api(description = "Endpoints for creating, retrieving, updating and deleting Pizzas.", tags = {"pizzas"})
public class PizzaController {

	@Autowired
	private PizzaService service;

	@ApiOperation(value = "Returns one pizza from the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@GetMapping(value = "/pizzas/", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> getPizzas() throws Exception {
		Logs.logEndpoint("GET", "/api/pizzas/", PizzaController.class.getName());
		return ResponseEntity.ok().body(service.getPizzas(0));
	}

	@ApiOperation(value = "Returns all pizzas from the database", nickname = "/pizzas/{id}", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@GetMapping(value = "/pizzas/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> getPizzaById(@PathVariable int id) throws Exception {
		Logs.logEndpoint("GET","/api/pizzas/{" + id + "}", PizzaController.class.getName());
		return ResponseEntity.ok().body(service.getPizzas(id));
	}

	@ApiOperation(value = "Creates a pizza in the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@PostMapping(value = "/pizzas/", produces = "application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PizzaResponse> createPizza(
			@ApiParam(value = "JSON entry to create a pizza in the database")
			@RequestBody @Validated PizzaDTO pizzaDto) throws Exception {
		Logs.logEndpoint("POST", "/api/pizzas/", PizzaController.class.getName());
		return ResponseEntity.created(URI.create("/pizzas/")).body(service.createPizza(pizzaDto));
	}

	@ApiOperation(value = "Updates a pizza in the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@PutMapping(value = "/pizzas/", produces = "application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> updatePizza(@RequestBody PizzaDTO pizzaDto) throws Exception {
		Logs.logEndpoint("PUT", "/api/pizzas/", PizzaController.class.getName());
		return ResponseEntity.ok().body(service.updatePizza(pizzaDto));
	}

	@ApiOperation(value = "Delete a pizza in the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@DeleteMapping(value = "/pizzas/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> deletePizza(@PathVariable int id) throws JsonProcessingException {
		Logs.logEndpoint("DELETE", "/api/pizzas/", PizzaController.class.getName());
		return ResponseEntity.ok().body(service.deletePizza(id));
	}

}
