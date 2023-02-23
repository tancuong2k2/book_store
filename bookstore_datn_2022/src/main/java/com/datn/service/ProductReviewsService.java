package com.datn.service;

import java.util.List;
import java.util.Optional;

import com.datn.entity.ProductReviews;

public interface ProductReviewsService {

	List<ProductReviews> findAll();

	Optional<ProductReviews> findById(Long id);

	List<ProductReviews> findAllById(Long id);

	List<ProductReviews> findAllByProductId(Long id);

	void createReview(Long productId, Long userId, String message);

	ProductReviews findTopByUserIdOrderByCreatedDateDesc(Long userId);

	void deleteById(Long id);

}
