package br.com.pizzaAPI.controller;

import java.net.URI;

import br.com.pizzaAPI.model.PizzaDTO;
import br.com.pizzaAPI.model.response.PizzaResponse;
import br.com.pizzaAPI.service.PizzaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api")
public class PizzaController {

	@Autowired
	private PizzaService service;

	@ApiOperation(value = "Returns one pizza from the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@GetMapping(value = "/pizzas/", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> getPizzas() throws Exception {
		return ResponseEntity.ok().body(service.getPizzas(0));
	}

	@ApiOperation(value = "Returns all pizzas from the database", nickname = "/pizzas/{id}", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@GetMapping(value = "/pizzas/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> getPizzaById(@PathVariable int id) throws Exception {
		return ResponseEntity.ok().body(service.getPizzas(id));
	}

	@ApiOperation(value = "Creates a pizza from the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@PostMapping(value = "/pizzas/", produces = "application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PizzaResponse> createPizza(@RequestBody PizzaDTO pizzaDto) throws Exception {
		return ResponseEntity.created(URI.create("/pizzas/create/" )).body(service.createPizza(pizzaDto));
	}

	@ApiOperation(value = "Updates a pizza from the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@PutMapping(value = "/pizzas/", produces = "application/json", consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> updatePizza(@RequestBody PizzaDTO pizzaDto) throws Exception {
		return ResponseEntity.ok().body(service.updatePizza(pizzaDto));
	}

	@ApiOperation(value = "Delete a pizza from the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@DeleteMapping(value = "/pizzas/{id}", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> deletePizza(@PathVariable Long id) {
		return ResponseEntity.ok().body(service.deletePizza(id));
	}

}
