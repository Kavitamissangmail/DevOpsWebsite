package com.soprasteria.devopsassesmenttool.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.devopsassesmenttool.model.DBFile;

public interface DBFileRepository extends JpaRepository<DBFile, Long> {

	Set<DBFile> findByUserId(Long userId);

	Set<DBFile> findByQId(Long qId);

	Set<DBFile> findByAnswerId(Long answerId);
}
