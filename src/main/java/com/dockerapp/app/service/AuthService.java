package com.dockerapp.app.service;

import com.dockerapp.app.dto.LoginRequest;
import com.dockerapp.app.dto.RegisterRequest;
import com.dockerapp.app.dto.ServerResponse;

public interface AuthService{
	ServerResponse register(RegisterRequest request);
	
	ServerResponse login(LoginRequest request);

	
	
	
	

}
