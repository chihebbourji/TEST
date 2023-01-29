package com.pfe.backend.services.model;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

   private String accessToken;

   private UserDTO userDTO;
}
