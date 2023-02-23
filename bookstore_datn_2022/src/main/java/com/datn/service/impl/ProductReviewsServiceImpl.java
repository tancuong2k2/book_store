package com.datn.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.entity.ProductReviews;
import com.datn.repository.ProductReviewsRepo;
import com.datn.service.ProductReviewsService;

@Service
public class ProductReviewsServiceImpl implements ProductReviewsService {

	@Autowired
	ProductReviewsRepo productReviewsRepo;

	@Override
	public List<ProductReviews> findAll() {
		return productReviewsRepo.findAll();
	}

	@Override
	public Optional<ProductReviews> findById(Long id) {
		return productReviewsRepo.findById(id);
	}

	@Override
	public List<ProductReviews> findAllById(Long id) {
		// TODO Auto-generated method stub
		return productReviewsRepo.findAllById(id);
	}

	@Override
	public List<ProductReviews> findAllByProductId(Long id) {
		// TODO Auto-generated method stub
		return productReviewsRepo.findByProductIdOrderByCreatedDateDesc(id);
	}

	@Override
	public void createReview(Long productId, Long userId, String message) {
		productReviewsRepo.createReview(productId, userId, message);
	}

	@Override
	public ProductReviews findTopByUserIdOrderByCreatedDateDesc(Long userId) {
		return productReviewsRepo.findTopByUserIdOrderByCreatedDateDesc(userId);
	}

	@Override
	public void deleteById(Long id) {
		productReviewsRepo.deleteById(id);
	}

}
