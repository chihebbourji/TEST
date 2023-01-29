package com.pfe.backend.web;


import com.pfe.backend.services.AuthenticationService;
import com.pfe.backend.services.model.LoginRequest;
import com.pfe.backend.services.model.LoginResponse;
import com.pfe.backend.services.model.SignupRequest;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication")
public class AuthenticationController {

    private  final AuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody final LoginRequest loginRequest){
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }
    @PostMapping("/signUp")
    public ResponseEntity<Void> signUp(@RequestBody final SignupRequest signupRequest) {
        authenticationService.signUp(signupRequest);
        return ResponseEntity.noContent().build();
    }
}
