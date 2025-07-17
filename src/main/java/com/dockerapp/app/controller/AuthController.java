package com.dockerapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dockerapp.app.dto.LoginRequest;
import com.dockerapp.app.dto.RegisterRequest;
import com.dockerapp.app.dto.ServerResponse;
import com.dockerapp.app.service.AuthServiceImpl;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	AuthServiceImpl authServiceImpl;
	
	@PostMapping("/register")
	public ServerResponse register(@RequestBody RegisterRequest request){
		return authServiceImpl.register(request);
		
	}
	
	@PostMapping("/login")
	public ServerResponse register(@RequestBody LoginRequest request){
		return authServiceImpl.login(request);
		
	
	}
	
	

}
