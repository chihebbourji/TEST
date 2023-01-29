package com.pfe.backend.services.impl;

import com.pfe.backend.properties.SecurityProperties;
import com.pfe.backend.services.AuthenticationService;
import com.pfe.backend.services.UserService;
import com.pfe.backend.services.model.LoginRequest;
import com.pfe.backend.services.model.LoginResponse;
import com.pfe.backend.services.model.SignupRequest;
import com.pfe.backend.services.model.UserDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SecurityProperties securityProperties;

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(),
                loginRequest.getPassword()));
        return LoginResponse.builder().accessToken(securityProperties.getTokenPrefix().concat(generateToken(loginRequest.getEmail())))
                .userDTO(userService.findByEmail(loginRequest.getEmail()))
                .build();
    }

    @Override
    public UserDTO signUp(SignupRequest signupRequest) {
        signupRequest.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        return userService.add(signupRequest);
    }
    private String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + securityProperties.getValidity() * 1000))
                .signWith(SignatureAlgorithm.HS512, securityProperties.getSecret()).compact();
    }
}
