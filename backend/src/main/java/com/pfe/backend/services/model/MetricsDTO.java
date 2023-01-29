package com.pfe.backend.services.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetricsDTO {

    private long id;

    private String date;

    private String temperature;

    private String humidity;

    private String gaz;

    private String sound;
}
