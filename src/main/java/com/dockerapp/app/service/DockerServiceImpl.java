package com.dockerapp.app.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dockerapp.app.dto.ContainerIdDto;
import com.dockerapp.app.dto.DockerInfo;
import com.dockerapp.app.dto.ServerResponse;
import com.dockerapp.app.dto.UserRequest;
import com.dockerapp.app.entity.AppUser;
import com.dockerapp.app.entity.User;
import com.dockerapp.app.repository.AppUserRepository;
import com.dockerapp.app.repository.UserRepository;
import com.dockerapp.app.utils.Utils;

@Service
public class DockerServiceImpl implements DockerService {
	
	@Autowired
    UserRepository containerRepo;
	
	@Autowired
	AppUserRepository appRepo;
	
	@Autowired
	PasswordEncoder passwordEncoder;


//    public DockerServiceImpl(UserRepository containerRepo) {
//        this.containerRepo = containerRepo;
//    }

	
    @Override
    public ServerResponse createContainerOpenJDk(UserRequest userRequest) {
    	
        return createContainer(userRequest, "openjdk", List.of());
    }

    @Override
    public ServerResponse createContainerMySql(UserRequest userRequest) {
        List<String> envVars = new ArrayList<>();
        if (userRequest.getAnyPassword() != null) {
            envVars.add("-e");
            envVars.add("MYSQL_ROOT_PASSWORD=" + userRequest.getAnyPassword());

            if (userRequest.getAnyUsername() != null) {
                envVars.add("-e");
                envVars.add("MYSQL_ROOT_USER=" + userRequest.getAnyUsername());
                envVars.add("-e");
                envVars.add("MYSQL_ROOT_PASSWORD=" + userRequest.getAnyPassword());
            }
        }
        return createContainer(userRequest, "mysql", envVars);
    }

    private ServerResponse createContainer(UserRequest userRequest, String imageName, List<String> envVars) {
        try {
            int hostPort = Utils.getAvailablePort();

            List<String> cmd = new ArrayList<>();
            cmd.add("docker");
            cmd.add("run");
            cmd.add("-it");
            cmd.add("-d");
            cmd.add("-p");
            cmd.add(hostPort + ":" + hostPort);
            cmd.addAll(envVars);
            cmd.add(imageName);

            ProcessBuilder pb = new ProcessBuilder(cmd);
            Process process = pb.start();
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String containerId = reader.readLine().trim();
            
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username;

            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername(); // here this is usually the `username`
            } else {
                username = principal.toString();
            }
            
            AppUser user = appRepo.findByUsername(username)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            //AppUser userEmail = appRepo.findByEmail(containerId);
            User record = User.builder()
                    .containerId(containerId.substring(0,12))
                    .imageName(imageName)
                    .email(user.getEmail())
                    .appUser(user)
                    .anyUsername(userRequest.getAnyUsername())
                    .anyPassword(userRequest.getAnyPassword())
                    .containerPort(hostPort)
                    .userIp(Utils.getClientIp())
                    .build();

            User userContainer=containerRepo.save(record);

            return ServerResponse.builder()
            		.responseCode("200")
            		.responseMessage(imageName + " container started at http://localhost"+hostPort)
            		.dockerInfo(DockerInfo.builder()
            				.containerName(userContainer.getImageName())
            				.containerId(userContainer.getContainerId())
            				.containerPort(userContainer.getContainerPort())
            				.build())
                    .build();

        } catch (Exception e) {
            return ServerResponse.builder()
                    .responseCode(imageName)
                    .responseMessage(imageName)
                    .dockerInfo(null)
                    .build();
        }
    }

	@Override
	public ServerResponse stopContainer(ContainerIdDto request) {
		try {
			
			User containerToStop = containerRepo.findByContainerId(request.getContainerId());
			
			
			
			List<String> cmd = new ArrayList<>();
			cmd.add("docker");
			cmd.add("stop");
			cmd.add(request.getContainerId());
			
			 ProcessBuilder pb = new ProcessBuilder(cmd);
	         Process process = pb.start();
	         process.waitFor();
	         
	         return ServerResponse.builder()
	        		 .responseCode("201")
	        		 .responseMessage("Container Stop")
	        		 .dockerInfo(DockerInfo.builder()
	        				 .containerName(containerToStop.getImageName())
	        				 .containerId(containerToStop.getContainerId())
	        				 .build())
	        		 .build();

					
			
		}catch(Exception e) {
			return null;
			
		}
		
	}

  
}
