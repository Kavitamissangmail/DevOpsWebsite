package com.soprasteria.devopsassesmenttool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soprasteria.devopsassesmenttool.model.AppUser;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {

	AppUser findByUserName(String userName);
}
