package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Login {
	
	@Id
	@Email
	private String email;
	
	@NotNull
	private String password;
	
	@OneToOne
	@JoinColumn(name = "registrationId")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Registration registration;

}
