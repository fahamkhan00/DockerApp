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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name="Authentication Management API's")

public class AuthController {
	
	@Autowired
	AuthServiceImpl authServiceImpl;
	
	
	@Operation(
			summary="User Registration",
			description="Registration of User"
		)	
	@PostMapping("/register")
	public ServerResponse register(@RequestBody RegisterRequest request){
		return authServiceImpl.register(request);
		
	}
			@Operation(
					summary="User Login",
					description="Login Into Your Account"
					)
	@PostMapping("/login")
	public ServerResponse register(@RequestBody LoginRequest request){
		return authServiceImpl.login(request);
		
	
	}
	
	

}
