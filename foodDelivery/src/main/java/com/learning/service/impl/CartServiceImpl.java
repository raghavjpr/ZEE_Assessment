package com.learning.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learning.entity.Food;
import com.learning.entity.User;
import com.learning.repo.FoodRepo;
import com.learning.repo.UserRepo;
import com.learning.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	UserRepo userRepo;
	@Autowired
	FoodRepo foodRepo;

	@Override
	public List<Food> addFoodToCart(User user, int id) {
		// TODO Auto-generated method stub
		List<Food> cart = user.getCart();
		Food food = foodRepo.getById(id);
		cart.add(food);
		user.setCart(cart);
		userRepo.save(user);
		return user.getCart();
	}

	@Override
	public Optional<List<Food>> getCart(User user) {
		// TODO Auto-generated method stub
		List<Food> cart = user.getCart();
		return Optional.ofNullable(cart);
	}

	@Override
	public void deleteFoodFromCart(User user, int id) {
		// TODO Auto-generated method stub
		List<Food> cart = user.getCart();
		Food food = foodRepo.getById(id);
		cart.remove(food);
		user.setCart(cart);
		userRepo.save(user);
	}

	@Override
	public void deleteCart(User user) {
		// TODO Auto-generated method stub
		user.setCart(new ArrayList<Food>());
		userRepo.save(user);
	}

}
