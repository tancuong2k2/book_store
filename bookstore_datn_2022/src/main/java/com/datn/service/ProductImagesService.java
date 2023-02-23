package com.datn.service;

import java.util.List;
import java.util.Optional;

import com.datn.entity.ProductImages;

public interface ProductImagesService {

	Optional<ProductImages> findById(Long id);

	List<ProductImages> findByProductId(Long id);

}
