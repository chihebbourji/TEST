package com.pfe.backend.services.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMetricsDTO {

    private String temperature;

    private String humidity;

    private String gaz;

    private String sound;
}
