package com.pfe.backend.services.model;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class UserDTO {
    private long id;
    private String name;
    private String email;
    private String role;
    private String number;
}
