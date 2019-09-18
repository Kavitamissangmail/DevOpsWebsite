package com.soprasteria.devopsassesmenttool.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.devopsassesmenttool.model.DBFile;

public interface DBFileRepository extends JpaRepository<DBFile, Long> {

	Set<DBFile> findByUserIdOrderByQIdAsc(Long userId);

	Set<DBFile> findByQId(Long qId);

	Set<DBFile> findByQIdAndUserId(Long qId, Long userId);

	Set<DBFile> findByQIdAndUserIdAndAssessmentType(Long qId, Long userId, String assessment_type);
	
	Set<DBFile> findByUserIdAndAssessmentTypeOrderByQIdAsc(Long userId,String type);

}
