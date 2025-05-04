package br.com.pizzaAPI.adapters.input.models.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "PizzaDTO", description = "Input object for pizza's endpoints")
public class PizzaRequest {

    @ApiModelProperty(value = "id, not required field", position = 1)
    private int id;
    @ApiModelProperty(value = "flavor, required field", required = true, example = "Mussarela", position = 2)
    @NotBlank(message = "The flavor field is mandatory")
    private String flavor;
    @ApiModelProperty(value = "border, required field", required = true, example = "Cheese", position = 3)
    @NotBlank(message = "The border is mandatory")
    private String border;
    @ApiModelProperty(value = "price, required field", required = true, example = "50.0", position = 4)
    @Min(value = 1, message = "price should not be less than 1")
    private double price;
    @ApiModelProperty(value = "img, not required field", required = false, example = "img", position = 5)
    private String img;
}
