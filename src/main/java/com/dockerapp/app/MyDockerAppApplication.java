package com.dockerapp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@EnableAsync


@OpenAPIDefinition(
		info=@Info(
				title="DockerApp",
				description="Bckend REST API's for Running Docker Containers Virtually",
				version="v1.0",
				contact= @Contact(
						name="Faham Khan",
						email="fahamkhan11@gmail.com",
						url="https://www.linkedin.com/in/faham0/"
						),
				license=@License(
						name="DockerApp",
						url="https://github.com/fahamkhan00/DockerApp"				
				)
		
		),
externalDocs= @ExternalDocumentation(
		description="DockerApp Documentation",
		url="https://github.com/fahamkhan00/DockerApp"
		)
)



public class MyDockerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyDockerAppApplication.class, args);
	}

}
