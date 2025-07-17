package com.dockerapp.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dockerapp.app.dto.ContainerIdDto;
import com.dockerapp.app.dto.ServerResponse;
import com.dockerapp.app.dto.UserRequest;
import com.dockerapp.app.service.DockerServiceImpl;

@RestController
@RequestMapping("/api/docker")
public class DockerController {
	
	@Autowired
	private DockerServiceImpl dockerService;
	
	
	@PostMapping("/openjdk")
	public ServerResponse jdkContainer(@RequestBody UserRequest userRequest){
		return dockerService.createContainerOpenJDk(userRequest);
		
	}
	
	@PostMapping("/mysql")
	public ServerResponse sqlContainer(@RequestBody UserRequest userRequest){
		return dockerService.createContainerMySql(userRequest);
		
	}
	
	@PostMapping("/stop")
	public ServerResponse stopContainer(@RequestBody  ContainerIdDto containerId){
		return dockerService.stopContainer(containerId);
		
	}

}
