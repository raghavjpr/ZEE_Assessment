package com.learning.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.learning.entity.FoodItems;

@Repository
public interface FoodIteamsRepo extends JpaRepository<FoodItems, Integer> {

}
