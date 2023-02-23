package com.datn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datn.entity.Categories;

@Repository
public interface CategoriesRepo extends JpaRepository<Categories, Long> {

    List<Categories> findByIsDeleted(Boolean isDeleted);

	Categories findBySlugAndIsDeleted(String slug, Boolean isDeleted);
}
