package com.dockerapp.app.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
	
	private Long id;
	
	
	private String containerId;
	
	private int containerPort;
	
	private String imageName;
	
	private String anyUsername;
	
	private String anyPassword;
	
	private String userIp;

	
	
	
	
	

}
