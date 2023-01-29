package com.pfe.backend.web;

import com.pfe.backend.services.MetricsService;
import com.pfe.backend.services.model.AddMetricsDTO;
import com.pfe.backend.services.model.MetricsDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/system-resources")
@Tag(name = "system Resource")
public class SystemController {
    private final MetricsService metricsService;
    @PostMapping("/add")
    public ResponseEntity<MetricsDTO> add(@RequestBody final AddMetricsDTO addMetricsDTO) {
        return ResponseEntity.ok(metricsService.add(addMetricsDTO));
    }
}
