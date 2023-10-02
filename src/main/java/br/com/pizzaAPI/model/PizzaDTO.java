package br.com.pizzaAPI.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PizzaDTO {

    @ApiModelProperty(value = "id, required field")
    @NotBlank(message = "The id field is mandatory")
    private int id;
    @ApiModelProperty(value = "flavor, required field", position = 1)
    @NotBlank(message = "The flavor field is mandatory")
    private String flavor;
    @ApiModelProperty(value = "border, required field", position = 2)
    @NotBlank(message = "The flavor border is mandatory")
    private String border;
    @ApiModelProperty(value = "price, required field", position = 3)
    @NotBlank(message = "The flavor price is mandatory")
    private double price;
    @ApiModelProperty(value = "img, required field", position = 4)
    @NotBlank(message = "The flavor img is mandatory")
    private String img;
}
