package br.com.pizzaAPI.exception;

import br.com.pizzaAPI.exception.model.ErroResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
public class DefaultErrorException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private int code;
    private String message;
    private List<ErroResponse> errors = new ArrayList<>();

    public DefaultErrorException(String error) {
        this.code = HttpStatus.BAD_REQUEST.value();
        this.message = HttpStatus.BAD_REQUEST.toString();
        this.errors.add(new ErroResponse(HttpStatus.BAD_REQUEST.toString(), error));
    }

    public DefaultErrorException(String message, String error) {
        this.code = HttpStatus.BAD_REQUEST.value();
        this.message = message;
        this.errors.add(new ErroResponse(HttpStatus.BAD_REQUEST.toString(), error));
    }

    public DefaultErrorException(int code, String message,String codeDetail, String errorDetail) {
        this.code = code;
        this.message = message;
        this.errors.add(new ErroResponse(codeDetail, errorDetail));
    }
}


