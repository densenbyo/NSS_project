package cz.cvut.fel.ear.lingo.user.rest;

import exception.LingoException;
import cz.cvut.fel.ear.lingo.user.security.jwt.JwtAuthenticationResponse;
import cz.cvut.fel.ear.lingo.user.security.model.LoginRequest;
import cz.cvut.fel.ear.lingo.user.service.LoginService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> login( @RequestBody LoginRequest loginRequest) throws LingoException {
        return ResponseEntity.ok(new JwtAuthenticationResponse(loginService.login(loginRequest.getUsername(), loginRequest.getPassword())));
    }
}