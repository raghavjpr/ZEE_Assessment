package com.learning.service;

import com.learning.entity.FoodItems;

public interface FoodIteamsService {

	public String addFoodIteam(FoodItems foodItems);

	public String getFoodItemById(Integer integer);

	public String updateFoodItemById(Integer integer, FoodItems foodItems);

	public String getAllFoodItems();

	public String getAllFoodIteamsByType(String foodtype);

	public String deleteFoodById();

}
