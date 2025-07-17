package com.dockerapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockerapp.app.entity.AppUser;


public interface AppUserRepository extends JpaRepository<AppUser, Long> {
	
	 boolean existsByUsername(String username);
	    boolean existsByEmail(String email);


}
