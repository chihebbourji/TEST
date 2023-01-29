package com.pfe.backend.dao.repository;


import com.pfe.backend.dao.entity.Metrics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface MetricsRepository extends JpaRepository<Metrics,Long> {

   Metrics findFirstByOrderByIdDesc();

   List<Metrics> findByDateStartingWith(@NonNull String date);

   List<Metrics> findByDateBetween(Date dateStart, Date dateEnd);

}
