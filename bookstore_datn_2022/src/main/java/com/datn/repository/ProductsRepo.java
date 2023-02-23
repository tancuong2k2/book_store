package com.datn.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.datn.entity.ProductTypes;
import com.datn.entity.Products;

@Repository
public interface ProductsRepo extends JpaRepository<Products, Long> {

	Products findBySlug(String slug);

	List<Products> findByIsDeleted(Boolean isDeleted);

	Products findBySlugAndIdNot(String slug, Long id);

	List<Products> findByDiscountGreaterThan(Integer discount);


	List<Products> findByCategoryIdAndDiscountGreaterThan(Long categoryId, Integer discount);

	//index
	List<Products> findByIsDeletedAndQuantityGreaterThan(Boolean isDeleted, Integer quantity);

	// Phân trang
	// select * from products where isDeleted = ? and quantity > ?
	Page<Products> findByIsDeletedAndQuantityGreaterThan(Boolean isDeleted, Integer quantity, Pageable pageable);

	// select * from products where productTypes = ? and slug <> ? and isDeleted = ?
	// and quantity > ?
	List<Products> findByProductTypeAndSlugNotAndIsDeletedAndQuantityGreaterThan(ProductTypes productType, String slug,
			Boolean isDeleted, Integer quantity);

	// select * from products where categotyId = ? and isDeleted = ? and quantity >
	// ?
	List<Products> findByCategoryIdAndIsDeletedAndQuantityGreaterThan(Long categoryId, Boolean isDeleted,
			Integer quantity);

	// Phân trang
	// select * from products where categoryId = ? and isDeleted = ? and quantity >
	// ?
	Page<Products> findByCategoryIdAndIsDeletedAndQuantityGreaterThan(Long categoryId, Boolean isDeleted,
			Integer quantity, Pageable pageable);

	@Query(value = "SELECT * FROM products WHERE name LIKE %:key%", nativeQuery = true)
	List<Products> findByKeywords(String key);

	@Query(value = "SELECT * FROM products WHERE name LIKE %:key%", nativeQuery = true)
	Page<Products> findByKeywords(String key, PageRequest of);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products SET isDeleted = 1 WHERE id = ?", nativeQuery = true)
	void deleteLogical(Long id);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products SET isDeleted = 0 WHERE id = ?", nativeQuery = true)
	void recoveryLogical(Long id);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products SET quantity = ? WHERE id = ?", nativeQuery = true)
	void updateQuantity(Integer newQuantity, Long productId);

	@Modifying(clearAutomatically = true)
	@Query(value = "INSERT INTO products "
			+ "([name], quantity, price, discount, imgName, [description], slug, categoryId, productTypeId) VALUES "
			+ "(?, ?, ?, ?, ?, ?, ?, ?, ?)", nativeQuery = true)
	void save(String name, Integer quantity, Double price, Integer discount, String imgName, String description,
			String slug, Long categoryId, Long productTypeId);

	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE products SET "
			+ "[name] = ?, quantity = ?, price = ?, discount = ?, imgName = ?, [description] = ?, slug = ?, categoryId = ?, productTypeId = ? "
			+ "WHERE id = ?", nativeQuery = true)
	void update(String name, Integer quantity, Double price, Integer discount, String imgName, String description,
			String slug, Long categoryId, Long productTypeId, Long id);
}
