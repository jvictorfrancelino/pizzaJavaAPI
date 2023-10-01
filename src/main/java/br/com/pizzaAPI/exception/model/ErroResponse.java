package br.com.pizzaAPI.exception.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ErroResponse", description = "Retorno de erro da API")
public class ErroResponse {

    @ApiModelProperty(name = "Código do erro", notes = "Código interno do erro", example = "400 BAD_REQUEST", position = 0)
    private String codigo;

    @ApiModelProperty(name = "Explicação do erro", notes = "Breve explicação do erro", example = "Erro na validação do campo", position = 1)
    private String detalhe;

}