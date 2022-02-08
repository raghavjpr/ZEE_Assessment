package com.learning.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // to auto-generate getter and setters
@AllArgsConstructor
@NoArgsConstructor

@Entity // table creation
public class Registration {

	@Id // PK
	@GeneratedValue(strategy = GenerationType.AUTO) // automatically generate
	private int registrationId;

	@Size(max = 50) // maximum length
	@NotBlank
	private String name;

	@Size(max = 50)
	@Email
	private String email;

	@Size(max = 100)
	@NotBlank
	private String password;

	@Size(max = 100)
	@NotBlank
	private String address;

	// one user one login
	@OneToOne(mappedBy = "registration", cascade = CascadeType.ALL)
	private Login login;

}