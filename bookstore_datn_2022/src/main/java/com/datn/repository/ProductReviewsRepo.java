package com.datn.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.datn.entity.ProductReviews;

@Repository
public interface ProductReviewsRepo extends JpaRepository<ProductReviews, Long> {

	List<ProductReviews> findAllById(Long id);

	// select * from product_reviews where productId = ? order by createdDate DESC
	List<ProductReviews> findByProductIdOrderByCreatedDateDesc(Long id);

	@Query(value = "INSERT INTO product_reviews (productId, userId, [description], createdDate) "
			+ "VALUES (?, ?, ?, GETDATE())", nativeQuery = true)
	@Modifying
    @Transactional
	void createReview(Long productId, Long userId, String message);

	ProductReviews findTopByUserIdOrderByCreatedDateDesc(Long userId);

}
