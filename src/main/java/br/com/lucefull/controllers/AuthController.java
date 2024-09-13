package br.com.lucefull.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.lucefull.data.vo.v1.security.AccountCredentialsVO;
import br.com.lucefull.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/auth")
@Tag(name = "auth", description = "Authetication Endpoint")
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/signin")
    @Operation(summary = "Autheticates a user and return a token")
    public ResponseEntity<?> signin(@RequestBody AccountCredentialsVO data) {

        return authService.signin(data);

    }

    @PutMapping("/resfresh/{username}")
    @Operation(summary = "Refresh token for authemticated user and return a token")
    public ResponseEntity<?> refresh(@PathVariable("username") String userName,
            @RequestHeader("Authorization") String refreshToken) {

        return authService.refreshToken(userName, refreshToken);

    }
}
