package com.pfe.backend.services;

import com.pfe.backend.services.model.LoginRequest;
import com.pfe.backend.services.model.LoginResponse;
import com.pfe.backend.services.model.SignupRequest;
import com.pfe.backend.services.model.UserDTO;

public interface AuthenticationService {

    LoginResponse login(LoginRequest loginRequest);
    UserDTO signUp(SignupRequest signupRequest);
}
