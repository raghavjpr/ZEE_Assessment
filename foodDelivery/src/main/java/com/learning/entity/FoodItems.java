package com.learning.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FoodItems {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer foodId;

	@NotNull
	@Size(max = 20)
	private String foodName;

	private Integer foodCost;

	@NotNull
	private FOODTYPE foodType;

	@NotNull
	@Size(max = 50)
	private String descripotion;

	@NotBlank
	private String foodPic;

}
