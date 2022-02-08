package com.learning.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "email") })
public class Registration {

	@Id
	private String registrationId;

	@NotNull
	@Email
	private String email;

	@NotNull
	private String name;

	@NotNull
	private String password;

	@NotNull
	private String address;

	@OneToOne(mappedBy = "registration", cascade = CascadeType.ALL)
	private Login login;

}
