package com.dockerapp.app.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table( name="users")
public class User {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String containerId;
	
	private int containerPort;
	
	private String imageName;
	
	private String anyUsername;
	
	private String anyPassword;
	
	private String userIp;
	
	@CreationTimestamp
	private LocalDateTime createdAt;
	
	@UpdateTimestamp
	private LocalDateTime modifiedAt;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;
	
	

}
