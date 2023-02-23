package com.datn.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.entity.ProductTypes;
import com.datn.repository.ProductTypesRepo;
import com.datn.service.ProductTypesService;

@Service
public class ProductTypesServiceImpl implements ProductTypesService{

	@Autowired
	private ProductTypesRepo repo;

	@Override
	public List<ProductTypes> findAll() {
		// TODO Auto-generated method stub
		return repo.findByIsDeleted(Boolean.FALSE);
	}

	@Override
	public Optional<ProductTypes> findById(Long id) {
		return repo.findById(id);
	}
	
}
