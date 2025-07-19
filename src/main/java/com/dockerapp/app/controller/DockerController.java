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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/docker")
@Tag(name="Container Management API's")

public class DockerController {
	
	@Autowired
	private DockerServiceImpl dockerService;
	
	@Operation(
			summary="Create OpenJdk Container",
			description="Create a OpenJdk Container For Running Java"
			)
	@PostMapping("/openjdk")
	public ServerResponse jdkContainer(@RequestBody UserRequest userRequest){
		return dockerService.createContainerOpenJDk(userRequest);
		
	}
	
	@Operation(
			summary="Create MySql Container",
			description="Create a MySql Container"
			)
	
	@PostMapping("/mysql")
	public ServerResponse sqlContainer(@RequestBody UserRequest userRequest){
		return dockerService.createContainerMySql(userRequest);
		
	}
	
	@Operation(
			summary="Stop the Container",
			description="Stop the Running Container With ContainerId"
			)
	
	@PostMapping("/stop")
	public ServerResponse stopContainer(@RequestBody  ContainerIdDto containerId){
		return dockerService.stopContainer(containerId);
		
	}

}
