package com.datn.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.datn.entity.ProductTypes;
import com.datn.entity.Products;

public interface ProductsService {

	Products findById(Long id);

	Products findBySlug(String slug);

	List<Products> findByIsDeleted();

	List<Products> findByDiscount();

	List<Products> findByCategoryIdAndDiscount(Long categoryId);

	List<Products> findAll();
	
	Page<Products> findAll(int pageSize, int pageNumber) throws Exception;

	List<Products> findByRelatedProducts(ProductTypes productType, String slug);

	List<Products> findAllByCategoryId(Long categoryId);
	
	Page<Products> findAllByCategoryId(Long categoryId, int maxSizePage, int pageNumber) throws Exception;

	Page<Products> findByKeywords(String key, int pageSize, int pageNumber) throws Exception;

	List<Products> findByKeywords(String key);

	void updateQuantity(Integer newQuantity, Long productId);

	void deleteLogical(Long id);

	void recoveryLogical(Long id);

	void save(Products productRequest, Long categoryId, Long productTypeId) throws Exception;

	void update(Products productRequest, Long categoryId, Long productTypeId) throws Exception;
}
