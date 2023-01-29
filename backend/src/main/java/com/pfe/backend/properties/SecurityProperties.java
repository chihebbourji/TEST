package com.pfe.backend.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "security.config")
@Getter
@Setter
public class SecurityProperties {
    private String secret;
    private String tokenPrefix;
    private long validity;
}
