package com.datn.service;

import java.util.List;

import com.datn.dto.CartDetailDto;
import com.datn.entity.OrderDetails;

public interface OrderDetailsService {

	List<OrderDetails> findByOrderId(Long id);

	void insert(CartDetailDto cartDetailDto);
}
