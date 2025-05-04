package br.com.pizzaAPI.adapters.input.controller;

import java.net.URI;

import br.com.pizzaAPI.adapters.input.models.request.PizzaRequest;
import br.com.pizzaAPI.adapters.input.models.response.PizzaResponse;
import br.com.pizzaAPI.domain.useCase.PizzaUseCaseImpl;
import br.com.pizzaAPI.util.Logs;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/api/v1")
@Api(description = "Endpoints for creating, retrieving, updating and deleting Pizzas.", tags = {"pizzas"})
public class PizzaController {

	private final PizzaUseCaseImpl pizzaUseCase;

	private static final String REQUEST_MAPPING = "/api/v1";

	private static final String APPLICATION_JSON = "/application/json";

	@Autowired
	public PizzaController(PizzaUseCaseImpl pizzaUseCase) {
		this.pizzaUseCase = pizzaUseCase;
	}

	@ApiOperation(value = "Returns one pizza from the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@GetMapping(value = "/pizzas/", produces = APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> getPizzas() throws Exception {
		Logs.logEndpoint("GET", REQUEST_MAPPING, PizzaController.class.getName());
		return ResponseEntity.ok().body(pizzaUseCase.getPizzas(0));
	}

	@ApiOperation(value = "Returns all pizzas from the database", nickname = "/pizzas/{id}", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@GetMapping(value = "/pizzas/{id}", produces = APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> getPizzaById(@PathVariable int id) throws Exception {
		Logs.logEndpoint("GET", REQUEST_MAPPING + "{" + id + "}", PizzaController.class.getName());
		return ResponseEntity.ok().body(pizzaUseCase.getPizzas(id));
	}

	@ApiOperation(value = "Creates a pizza in the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@PostMapping(value = "/pizzas/", produces = APPLICATION_JSON, consumes=APPLICATION_JSON)
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<PizzaResponse> createPizza(
			@ApiParam(value = "JSON entry to create a pizza in the database")
			@RequestBody @Validated PizzaRequest pizzaRequest) throws Exception {
		Logs.logEndpoint("POST", REQUEST_MAPPING, PizzaController.class.getName());
		return ResponseEntity.created(URI.create("/pizzas/")).body(pizzaUseCase.createPizza(pizzaRequest));
	}

	@ApiOperation(value = "Updates a pizza in the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@PutMapping(value = "/pizzas/", produces = APPLICATION_JSON, consumes=APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> updatePizza(@RequestBody PizzaRequest pizzaRequest) throws Exception {
		Logs.logEndpoint("PUT", REQUEST_MAPPING, PizzaController.class.getName());
		return ResponseEntity.ok().body(pizzaUseCase.updatePizza(pizzaRequest));
	}

	@ApiOperation(value = "Delete a pizza in the database", nickname = "/pizzas/", response = PizzaResponse.class)
	@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:4200"})
	@DeleteMapping(value = "/pizzas/{id}", produces = APPLICATION_JSON)
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<PizzaResponse> deletePizza(@PathVariable int id) throws Exception {
		Logs.logEndpoint("DELETE", REQUEST_MAPPING, PizzaController.class.getName());
		return ResponseEntity.ok().body(pizzaUseCase.deletePizza(id));
	}

}
