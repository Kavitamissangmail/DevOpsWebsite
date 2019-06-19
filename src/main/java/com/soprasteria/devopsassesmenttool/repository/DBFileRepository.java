package com.soprasteria.devopsassesmenttool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.devopsassesmenttool.model.DBFile;

public interface DBFileRepository extends JpaRepository<DBFile, Long> {
}
