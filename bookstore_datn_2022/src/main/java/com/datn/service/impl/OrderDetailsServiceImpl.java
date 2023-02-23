package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.dto.CartDetailDto;
import com.datn.entity.OrderDetails;
import com.datn.repository.OrderDetailsRepo;
import com.datn.service.OrderDetailsService;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

	@Autowired
	private OrderDetailsRepo repo;

	@Override
	public List<OrderDetails> findByOrderId(Long id) {
		return repo.findByOrderId(id);
	}
	
	@Override
	public void insert(CartDetailDto cartDetailDto) {
		Double percentDiscount = 1.0;
		if (cartDetailDto.getDiscount() != null) {
			Double discount = (double) cartDetailDto.getDiscount();
			if (discount > 0) {
				percentDiscount = 1.0 - discount / 100;
			}
		}
		Double price = cartDetailDto.getPrice() * percentDiscount;
		cartDetailDto.setPrice(price);
		repo.insert(cartDetailDto);
	}

}
