package com.datn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.entity.Categories;
import com.datn.repository.CategoriesRepo;
import com.datn.service.CategoriesService;

@Service
public class CategoriesServiceImpl implements CategoriesService {
	
	@Autowired
	private CategoriesRepo repo;
	
	@Override
	public List<Categories> findAll() {
		return repo.findByIsDeleted(Boolean.FALSE);
	}

	@Override
	public Categories findBySlug(String slug) {
		return repo.findBySlugAndIsDeleted(slug, Boolean.FALSE);
	}
}
