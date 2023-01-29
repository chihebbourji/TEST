package com.pfe.backend.dao.impl;

import com.pfe.backend.dao.entity.Metrics;
import com.pfe.backend.dao.MetricsDao;
import com.pfe.backend.dao.repository.MetricsRepository;
import com.pfe.backend.properties.MetricsProperties;
import com.pfe.backend.utils.BackendUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MetricsDaoImpl implements MetricsDao {
    private final MetricsRepository metricsRepository;
    private final MetricsProperties metricsProperties;

    @Override
    public Metrics add(Metrics metrics) {
        Metrics oldMetrics = findLatest();
        if(BackendUtils.isEmptyOrNull(metrics.getGaz())||BackendUtils.isEmptyOrNull(metrics.getTemperature())||
                BackendUtils.isEmptyOrNull(metrics.getHumidity())||BackendUtils.isEmptyOrNull(metrics.getSound())){
            return oldMetrics;
        }
        if(oldMetrics==null || oldMetrics.getDate().getTime()+metricsProperties.getRefreshRate()<=System.currentTimeMillis()){
            metrics.setDate(new Date());
            return metricsRepository.save(metrics);
        }
        return oldMetrics;
    }

    @Override
    public List<Metrics> findAll() {
        return metricsRepository.findAll();
    }

    @Override
    public List<Metrics> findByDate(String date){
        Date startDate= null;
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            startDate=new Date();
        }
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(startDate);
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        startDate=calendar.getTime();
        calendar.set(Calendar.HOUR,23);
        calendar.set(Calendar.MINUTE,59);
        calendar.set(Calendar.SECOND,59);
        Date endSate=calendar.getTime();
        return metricsRepository.findByDateBetween(startDate,endSate);
    }

    @Override
    public Metrics findLatest() {
        return metricsRepository.findFirstByOrderByIdDesc();
    }
}
