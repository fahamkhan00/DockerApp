package com.dockerapp.app.service;


import com.dockerapp.app.dto.ContainerIdDto;
import com.dockerapp.app.dto.ServerResponse;
import com.dockerapp.app.dto.UserRequest;

public interface DockerService {
	
	//ServerResponse createContainer(UserRequest userRequest);
	
	ServerResponse createContainerOpenJDk(UserRequest userRequest);
	
	ServerResponse createContainerMySql(UserRequest userRequest);
	
	ServerResponse stopContainer(ContainerIdDto request);
	


}
