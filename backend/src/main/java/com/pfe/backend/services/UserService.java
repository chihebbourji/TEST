package com.pfe.backend.services;


import com.pfe.backend.services.model.MakeUserAdminDto;
import com.pfe.backend.services.model.SignupRequest;
import com.pfe.backend.services.model.UserDTO;

import java.util.List;


public interface UserService {

    UserDTO add(SignupRequest signupRequest);

    List<UserDTO> findAll();

    UserDTO findByEmail(String email);

    void deleteById(long id);

    void makeUserAdmin(MakeUserAdminDto makeUserAdminDto);
}
