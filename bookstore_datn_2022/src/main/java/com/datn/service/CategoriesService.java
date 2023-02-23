package com.datn.service;

import java.util.List;

import com.datn.entity.Categories;

public interface CategoriesService {

	List<Categories> findAll();

	Categories findBySlug(String slug);
}
