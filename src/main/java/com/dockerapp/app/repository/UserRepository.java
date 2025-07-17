package com.dockerapp.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dockerapp.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	User findByContainerId(String containerId);
	
   

}
