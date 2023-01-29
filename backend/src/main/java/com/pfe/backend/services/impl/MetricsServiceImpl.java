package com.pfe.backend.services.impl;

import com.pfe.backend.dao.MetricsDao;
import com.pfe.backend.dao.entity.Metrics;
import com.pfe.backend.services.mapper.MetricsMapper;

import com.pfe.backend.services.model.AddMetricsDTO;
import com.pfe.backend.services.model.MetricsDTO;
import com.pfe.backend.services.MetricsService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MetricsServiceImpl implements MetricsService {
    private final MetricsDao metricsDao;
    private final MetricsMapper metricsMapper;
    private final ModelMapper modelMapper;

    @Override
    public MetricsDTO add(AddMetricsDTO addMetricsDTO) {
        return metricsMapper.mapToDTO(metricsDao.add(modelMapper.map(addMetricsDTO, Metrics.class)));
    }

    @Override
    public List<MetricsDTO> findAll() {
        return metricsMapper.mapToDTOList(metricsDao.findAll());
    }

    @Override
    public List<MetricsDTO> findByDate(String date) {
        return metricsMapper.mapToDTOList(metricsDao.findByDate(date));
    }

    @Override
    public MetricsDTO findLatest() {
        return metricsMapper.mapToDTO(metricsDao.findLatest());
    }
}
