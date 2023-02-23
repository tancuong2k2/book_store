package com.datn.service;

import java.util.List;

import com.datn.entity.OrderStatuses;
import com.datn.entity.Orders;

public interface OrdersService {

	List<Orders> findAll();

	Orders findById(Long id);

	List<Orders> findByOrderStatus();

	List<Orders> findByOrderStatus(OrderStatuses orderStatus);
	
	List<Orders> findByOrderByCreatedDateDesc();

	Orders insert(Orders order);

	void updateOrderStatus(Long id, String orderStatus);
}
