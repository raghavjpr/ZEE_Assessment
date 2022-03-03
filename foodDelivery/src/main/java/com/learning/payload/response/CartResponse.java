package com.learning.payload.response;

import java.util.List;

import com.learning.entity.Food;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
	
	List<Food> cart;

}
