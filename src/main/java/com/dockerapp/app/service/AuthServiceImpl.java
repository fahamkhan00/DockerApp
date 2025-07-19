package com.dockerapp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dockerapp.app.config.JwtTokenProvider;
import com.dockerapp.app.dto.LoginRequest;
import com.dockerapp.app.dto.RegisterRequest;
import com.dockerapp.app.dto.ServerResponse;
import com.dockerapp.app.entity.AppUser;
import com.dockerapp.app.repository.AppUserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	AppUserRepository containerRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;


	
	@Override
	public ServerResponse register(RegisterRequest request) {
		
		if (containerRepo.existsByUsername(request.getUsername())) {
			return ServerResponse.builder()
					.responseCode("301")
					.responseMessage("Username already Exist")
					.dockerInfo(null)
					.build();
           // throw new RuntimeException("Username already taken");
        }
        if (containerRepo.existsByEmail(request.getEmail())) {
        	return ServerResponse.builder()
					.responseCode("301")
					.responseMessage("Email already Exist")
					.dockerInfo(null)
					.build();
            //throw new RuntimeException("Email already registered");
        }
        
        AppUser user=AppUser.builder()
        		.username(request.getUsername())
        		.email(request.getEmail())
        		.password(passwordEncoder.encode(request.getPassword()))
        		.build();
        containerRepo.save(user);
        
        
		return ServerResponse.builder()
				.responseCode("203")
				.responseMessage("Register Successful")
				.dockerInfo(null)
				.build();
	}

	@Override
	public ServerResponse login(LoginRequest request) {
		Boolean isUsernameExist=containerRepo.existsByUsername(request.getUsername());
		if(!isUsernameExist) {
			return ServerResponse.builder()
					.responseCode("303")
					.responseMessage("Username Not Exist")
					.dockerInfo(null)
					.build();
		}
		
		Authentication authentication=null;
		authentication=authenticationManager.authenticate(
		new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		
		
		return ServerResponse.builder()
				.responseCode("203")
				.responseMessage(jwtTokenProvider.generateToken(authentication)+" \n Login Successful: "+request.getUsername())
				.dockerInfo(null)
				.build();
				
		
	}

}
