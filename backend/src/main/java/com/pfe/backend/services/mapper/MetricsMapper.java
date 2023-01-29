package com.pfe.backend.services.mapper;

import com.pfe.backend.dao.entity.Metrics;

import com.pfe.backend.services.model.MetricsDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class MetricsMapper implements GenericMapper<Metrics,MetricsDTO> {
    private final ModelMapper modelMapper;

    @Override
    public Metrics mapToEntity(MetricsDTO metricsDTO) {
        return modelMapper.map(metricsDTO,Metrics.class);
    }


    @Override
    public MetricsDTO mapToDTO(Metrics metrics) {
        return modelMapper.map(metrics,MetricsDTO.class);
    }
}
