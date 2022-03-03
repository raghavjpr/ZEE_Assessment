package com.learning.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learning.entity.Food;
import com.learning.entity.User;
import com.learning.payload.request.IdRequest;
import com.learning.payload.response.CartResponse;
import com.learning.payload.response.MessageResponse;
import com.learning.repo.UserRepo;
import com.learning.security.services.UserDetailsImpl;
import com.learning.service.CartService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	@Autowired
	private UserRepo userRepo;
	
	@PostMapping("/")
	public ResponseEntity<?> addToCart(@RequestBody IdRequest idRequest) {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Long userId = userDetailsImpl.getId();
		Optional<User> optional = userRepo.findById(userId);
		List<Food> cart = cartService.addFoodToCart(optional.get(), idRequest.getId());
		return ResponseEntity.status(201).body(cart);
	}
	
	@GetMapping("/")
	public ResponseEntity<?> getCart() {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Long userId = userDetailsImpl.getId();
		Optional<User> optional = userRepo.findById(userId);
		List<Food> cart = cartService.getCart(optional.get()).get();
		if (optional.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new MessageResponse("No record found"));
		}
		return ResponseEntity.ok(new CartResponse(cart));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteFoodFromCart(@PathVariable("id") int id) {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Long userId = userDetailsImpl.getId();
		Optional<User> optional = userRepo.findById(userId);
		cartService.deleteFoodFromCart(optional.get(), id);
		return ResponseEntity.status(200).body(new MessageResponse("Food item removed from cart"));
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> deleteCart() {
		UserDetailsImpl userDetailsImpl = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		Long userId = userDetailsImpl.getId();
		Optional<User> optional = userRepo.findById(userId);
		cartService.deleteCart(optional.get());
		return ResponseEntity.status(200).body(new MessageResponse("Cart is empty now"));
	}

}
