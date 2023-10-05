package br.com.pizzaAPI.controller;

import br.com.pizzaAPI.config.SecurityConfig;
import br.com.pizzaAPI.util.JWTObject;
import br.com.pizzaAPI.model.LoginDTO;
import br.com.pizzaAPI.model.response.SessionResponse;
import br.com.pizzaAPI.entity.UserEntity;
import br.com.pizzaAPI.repository.UserRepository;
import br.com.pizzaAPI.util.JWTCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/api")
public class LoginController {
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private UserRepository repository;

    @PostMapping("/login")
    public SessionResponse login(@RequestBody LoginDTO login) {
        UserEntity user = repository.findByUsername(login.getUsername());
        if (user != null) {
            boolean passwordOk = encoder.matches(login.getPassword(), user.getPassword());
            if (!passwordOk) {
                throw new RuntimeException("Invalid password for login: " + login.getUsername());
            }
            //We are sending a Session object to return more user information
            SessionResponse session = new SessionResponse();
            session.setLogin(user.getUsername());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            session.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return session;
        } else {
            throw new RuntimeException("Error when trying to log in");
        }
    }
}