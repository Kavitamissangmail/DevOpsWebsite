package com.soprasteria.devopsassesmenttool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.Measure;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Integer> {

}