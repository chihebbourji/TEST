package com.pfe.backend.services;


import com.pfe.backend.services.model.AddMetricsDTO;
import com.pfe.backend.services.model.MetricsDTO;
import java.util.List;

public interface MetricsService {

    MetricsDTO add(AddMetricsDTO addMetricsDTO);

    List<MetricsDTO> findAll();

    List<MetricsDTO> findByDate(String date);

    MetricsDTO findLatest();

}
