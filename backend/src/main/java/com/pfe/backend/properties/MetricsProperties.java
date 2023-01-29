package com.pfe.backend.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class MetricsProperties {
    @Value("${application.metrics.refresh}")
    private long refreshRate;
}
