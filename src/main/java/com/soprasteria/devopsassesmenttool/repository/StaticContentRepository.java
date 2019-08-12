/**
 * 
 */
package com.soprasteria.devopsassesmenttool.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soprasteria.devopsassesmenttool.model.Account;
import com.soprasteria.devopsassesmenttool.model.StaticContent;

/**
 * @author dbkumar
 *
 */
public interface StaticContentRepository extends JpaRepository<StaticContent, Long> {
	
	StaticContent findByScId(Long scId);

	boolean existsByScId(Long scId);



}
