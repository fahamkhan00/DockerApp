package com.dockerapp.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dockerapp.app.dto.LoginRequest;
import com.dockerapp.app.dto.RegisterRequest;
import com.dockerapp.app.dto.ServerResponse;
import com.dockerapp.app.entity.AppUser;
import com.dockerapp.app.repository.AppUserRepository;

@Service
public class AuthServiceImpl implements AuthService {
	
	@Autowired
	AppUserRepository containerRepo;
	
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
        		.password(request.getPassword())
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
		
		return ServerResponse.builder()
				.responseCode("203")
				.responseMessage("Register Successful"+request.getUsername())
				.dockerInfo(null)
				.build();
				
		
	}

}
