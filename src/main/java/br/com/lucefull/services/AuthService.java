package br.com.lucefull.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.lucefull.data.vo.v1.security.AccountCredentialsVO;
import br.com.lucefull.data.vo.v1.security.TokenVO;
import br.com.lucefull.model.User;
import br.com.lucefull.repositories.UserRepository;
import br.com.lucefull.security.jwt.JwtTokenProvider;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> signin(AccountCredentialsVO data) {
        try {

            if (data == null || data.getUserName() == null || data.getUserName().isBlank() || data.getPassword() == null
                    || data.getPassword().isBlank())
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");
            String userName = data.getUserName();
            String password = data.getPassword();

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));

            User user = userRepository.findByUserName(userName);
            TokenVO tokenResponse = new TokenVO();

            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(userName, user.getRoles());

                if (tokenResponse == null)
                    return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid client request!");

            } else {
                throw new UsernameNotFoundException("Username " + userName + " not found!");
            }

            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

}
