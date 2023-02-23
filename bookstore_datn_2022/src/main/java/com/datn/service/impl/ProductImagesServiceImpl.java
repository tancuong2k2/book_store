package com.datn.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.entity.ProductImages;
import com.datn.repository.ProductImagesRepo;
import com.datn.service.ProductImagesService;

@Service
public class ProductImagesServiceImpl implements ProductImagesService {
	
	@Autowired
	ProductImagesRepo imagesRepo;

	@Override
	public Optional<ProductImages> findById(Long id) {
		return imagesRepo.findById(id);
	}

	@Override
	public List<ProductImages> findByProductId(Long id) {
		return imagesRepo.findByProductId(id);
	}

}
