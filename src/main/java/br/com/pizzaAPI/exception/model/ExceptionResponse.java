package br.com.pizzaAPI.exception.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "ExceptionResponse", description = "Retorno de erro da API")
public class ExceptionResponse {

    @ApiModelProperty(name = "timestamp", notes = "Momento que aconteceu o erro", example = "2020-09-23T12:58:11.505", position = 0)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String timestamp;

    @ApiModelProperty(name = "statusCode", notes = "Código do erro do http", example = "400", position = 1)
    private Integer statusCode;

    @ApiModelProperty(name = "mensagem", notes = "Mensagem do erro", example = "Campo idade possui problemas de validação", position = 2)
    private String mensagem;

    @ApiModelProperty(name = "erros", notes = "Lista de erros", position = 3)
    private List<ErroResponse> erros;

}
