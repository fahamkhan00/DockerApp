package com.dockerapp.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServerResponse {
	
	private String responseCode;
	private String responseMessage;
	private DockerInfo dockerInfo;

}
