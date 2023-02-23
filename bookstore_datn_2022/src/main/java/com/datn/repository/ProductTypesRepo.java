package com.datn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.datn.entity.ProductTypes;

public interface ProductTypesRepo extends JpaRepository<ProductTypes, Long> {
	
	List<ProductTypes> findByIsDeleted(Boolean isDeleted);

}
