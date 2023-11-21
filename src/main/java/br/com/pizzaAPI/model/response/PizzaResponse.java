package br.com.pizzaAPI.model.response;

import br.com.pizzaAPI.model.PizzaDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaResponse {

    @ApiModelProperty(name = "message", notes = "Return code", example = "0", position = 1)
    private int returnCode;
    @ApiModelProperty(name = "message", notes = "Message code", example = "0", position = 2)
    private String messageCode;
    @ApiModelProperty(name = "pizzasList", notes = "List of pizzas", position = 3)
    private List<PizzaDTO> pizzasList;
}
