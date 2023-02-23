package com.datn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datn.entity.ProductImages;

@Repository
public interface ProductImagesRepo extends JpaRepository<ProductImages, Long> {

	List<ProductImages> findByProductId(Long id);

}
