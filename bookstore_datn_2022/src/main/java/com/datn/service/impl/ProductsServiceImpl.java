package com.datn.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.datn.entity.ProductTypes;
import com.datn.entity.Products;
import com.datn.repository.ProductsRepo;
import com.datn.service.ProductsService;

@Service
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsRepo repo;

	@Override
	public Products findById(Long id) {
		Optional<Products> optional = repo.findById(id);
		return optional.isPresent() ? optional.get() : null;
	}

	@Override
	public Products findBySlug(String slug) {
		return repo.findBySlug(slug);
	}

	@Override
	public List<Products> findByIsDeleted() {
		return repo.findByIsDeleted(Boolean.TRUE);
	}

	@Override
	public List<Products> findByDiscount() {
		return repo.findByDiscountGreaterThan(0);
	}

	@Override
	public List<Products> findByCategoryIdAndDiscount(Long categoryId) {
		return repo.findByCategoryIdAndDiscountGreaterThan(categoryId, 0);
	}

	@Override
	public List<Products> findAll() {
		// TODO Auto-generated method stub
		return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0);
	}

	@Override
	public Page<Products> findAll(int pageSize, int pageNumber) throws Exception {
		if (pageNumber >= 1) {
			return repo.findByIsDeletedAndQuantityGreaterThan(Boolean.FALSE, 0,
					PageRequest.of(pageNumber - 1, pageSize));
		} else {
			throw new Exception("Page number must be greater than 0");
		}
	}

	@Override
	public List<Products> findByRelatedProducts(ProductTypes productType, String slug) {
		return repo.findByProductTypeAndSlugNotAndIsDeletedAndQuantityGreaterThan(productType, slug, Boolean.FALSE, 0);
	}

	@Override
	public List<Products> findAllByCategoryId(Long categoryId) {
		return repo.findByCategoryIdAndIsDeletedAndQuantityGreaterThan(categoryId, Boolean.FALSE, 0);
	}

	@Override
	public Page<Products> findAllByCategoryId(Long categoryId, int pageSize, int pageNumber) throws Exception {
		if (pageNumber >= 1) {
			return repo.findByCategoryIdAndIsDeletedAndQuantityGreaterThan(categoryId, Boolean.FALSE, 0,
					PageRequest.of(pageNumber - 1, pageSize));
		} else {
			throw new Exception("Page number must be greater than 0");
		}
	}

	@Override
	public List<Products> findByKeywords(String key) {
		return repo.findByKeywords(key);
	}

	@Override
	public Page<Products> findByKeywords(String key, int pageSize, int pageNumber) throws Exception {
		if (pageNumber >= 1) {
			return repo.findByKeywords(key, PageRequest.of(pageNumber - 1, pageSize));
		} else {
			throw new Exception("Page number must be greater than 0");
		}
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void deleteLogical(Long id) {
		repo.deleteLogical(id);
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void recoveryLogical(Long id) {
		repo.recoveryLogical(id);
	}

	@Override
	public void updateQuantity(Integer newQuantity, Long productId) {
		repo.updateQuantity(newQuantity, productId);
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void save(Products productRequest, Long categoryId, Long productTypeId) throws Exception {
		if (existsProductSlug(productRequest.getSlug())) {
			throw new Exception("Slug already exists!");
		}
		String name = productRequest.getName();
		Integer quantity = productRequest.getQuantity();
		Double price = productRequest.getPrice();
		Integer discount = 0;
		if (productRequest.getDiscount() != null) {
			discount = productRequest.getDiscount();
		}
		String imgName = productRequest.getImgName();
		String description = "";
		if (productRequest.getDescription() != null) {
			description = productRequest.getDescription();
		}
		String slug = productRequest.getSlug();
		// categoryId
		// productTypeId
		repo.save(name, quantity, price, discount, imgName, description, slug, categoryId, productTypeId);
	}

	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public void update(Products productRequest, Long categoryId, Long productTypeId) throws Exception {
		if (existsProductSlugAndIdNot(productRequest.getSlug(), productRequest.getId())) {
			throw new Exception("Slug already exists!");
		}
		String name = productRequest.getName();
		Integer quantity = productRequest.getQuantity();
		Double price = productRequest.getPrice();
		Integer discount = 0;
		if (productRequest.getDiscount() != null) {
			discount = productRequest.getDiscount();
		}
		String imgName = productRequest.getImgName();
		String description = "";
		if (productRequest.getDescription() != null) {
			description = productRequest.getDescription();
		}
		String slug = productRequest.getSlug();
		// categoryId
		// productTypeId
		Long id = productRequest.getId();
		repo.update(name, quantity, price, discount, imgName, description, slug, categoryId, productTypeId, id);
	}

	private Boolean existsProductSlug(String slug) {
		return repo.findBySlug(slug) != null ? true : false;
	}
	
	private Boolean existsProductSlugAndIdNot(String slug, Long id) {
		return repo.findBySlugAndIdNot(slug, id) != null ? true : false;
	}

}
