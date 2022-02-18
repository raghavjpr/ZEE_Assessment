package com.learning.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.learning.entity.enums.EROLE;

import lombok.Data;

@Data
@Entity
public class Role {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int roleId;

	@Enumerated(EnumType.STRING)
	@Column(length = 20)
	private EROLE roleName;

}
