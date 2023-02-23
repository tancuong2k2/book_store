package com.datn.service;


import com.datn.dto.CartDto;
import com.datn.entity.Users;


public interface CartService {
	
	CartDto updateCart(CartDto cart, Long productId, Integer quantity, boolean isReplace);
	Integer getTotalQuantity(CartDto cart);
	Double getTotalPrice(CartDto cart);
	void insert(CartDto cart, Users user, String address, String phone) throws Exception;
}
