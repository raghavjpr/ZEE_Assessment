package com.learning.service;

import java.util.List;
import java.util.Optional;

import com.learning.entity.Food;
import com.learning.entity.User;

public interface CartService {

	public List<Food> addFoodToCart(User user, int id);
	public Optional<List<Food>> getCart(User user);
	public void deleteFoodFromCart(User user, int id);
	public void deleteCart(User user);

}
