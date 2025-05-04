package br.com.pizzaAPI.adapters.input.models.request;

import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
}
