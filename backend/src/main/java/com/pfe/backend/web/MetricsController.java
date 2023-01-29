package com.pfe.backend.web;

import com.pfe.backend.services.model.DateFilterDTO;
import com.pfe.backend.services.model.MetricsDTO;
import com.pfe.backend.services.MetricsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("/Metrics-resources")
@Tag(name = "Metrics Resource")
@PreAuthorize("hasAuthority('ADMIN_ROLE')||hasAuthority('USER_ROLE')")
public class MetricsController {
        private final MetricsService metricsService;

        @GetMapping("/all")
        public ResponseEntity<List<MetricsDTO>> findAll(){
            return ResponseEntity.ok(metricsService.findAll());
        }

        @PostMapping("/by-date")
        public ResponseEntity<List<MetricsDTO>> findByDate(@RequestBody DateFilterDTO date){
            return ResponseEntity.ok(metricsService.findByDate(date.getDate()));
        }
        @GetMapping("/findLatest")
        public ResponseEntity<MetricsDTO> findLatest() {
            return ResponseEntity.ok(metricsService.findLatest());
        }
}

