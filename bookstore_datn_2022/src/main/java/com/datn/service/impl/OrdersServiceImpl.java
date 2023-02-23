package com.datn.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.entity.OrderStatuses;
import com.datn.entity.Orders;
import com.datn.repository.OrdersRepo;
import com.datn.service.OrdersService;

@Service
public class OrdersServiceImpl implements OrdersService {

	@Autowired
	private OrdersRepo repo;

	@Override
	public List<Orders> findAll() {
		return repo.findAll();
	}

	@Override
	public Orders findById(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public List<Orders> findByOrderStatus() {
		return repo.findByOrderStatusCustom();
	}

	@Override
	public List<Orders> findByOrderStatus(OrderStatuses orderStatus) {
		return repo.findByOrderStatus(orderStatus);
	}

	@Override
	public List<Orders> findByOrderByCreatedDateDesc() {
		return repo.findByOrderByCreatedDateDesc();
	}

	@Override
	public Orders insert(Orders order) {
		return repo.saveAndFlush(order);
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void updateOrderStatus(Long id, String orderStatus) {
		repo.updateOrderStatus(id, orderStatus);
	}
}
