package br.com.pizzaAPI.exception;

import br.com.pizzaAPI.enums.MessageSystem;
import br.com.pizzaAPI.exception.model.ErroResponse;
import br.com.pizzaAPI.exception.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(DefaultErrorException.class)
    public ResponseEntity<ExceptionResponse> defaultErrorException(DefaultErrorException e, HttpServletRequest request){
        return ResponseEntity.status(e.getCode()).body(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .mensagem(e.getMessage())
                .statusCode(e.getCode())
                .erros(e.getErrors())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        List<ErroResponse> errors = new ArrayList<>();
        for(FieldError x : e.getBindingResult().getFieldErrors()) {
            errors.add(new ErroResponse(x.getField(), x.getDefaultMessage()));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(ExceptionResponse.builder()
                .timestamp(LocalDateTime.now().toString())
                .mensagem(MessageSystem.BAD_REQUEST.getErro())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .erros(errors)
                .build());
    }

}
