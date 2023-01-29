package com.pfe.backend.dao;

import com.pfe.backend.dao.entity.Metrics;

import java.util.List;

public interface MetricsDao {

    Metrics add(Metrics metrics);

    List<Metrics> findAll();

    List<Metrics> findByDate(String date);

    Metrics findLatest();
}
