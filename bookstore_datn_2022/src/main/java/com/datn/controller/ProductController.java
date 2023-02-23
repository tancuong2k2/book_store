package com.datn.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.datn.constant.SessionConstant;
import com.datn.entity.Users;
import com.datn.entity.Categories;
import com.datn.entity.ProductImages;
import com.datn.entity.ProductReviews;
import com.datn.entity.Products;
import com.datn.service.CategoriesService;
import com.datn.service.ProductImagesService;
import com.datn.service.ProductReviewsService;
import com.datn.service.ProductTypesService;
import com.datn.service.ProductsService;
import com.datn.util.UserNotFoundExcepion;

@Controller
@RequestMapping("/")
public class ProductController {

	@Autowired
	private ProductReviewsService productReviewsService;

	@Autowired
	private ProductsService productsService;

	@Autowired
	private CategoriesService categoriesService;
	
	@Autowired
	private ProductTypesService productTypesService;
	
	@Autowired
	private ProductImagesService productImagesService;

	private static final int PAGE_SIZE = 9;

	@GetMapping("shop-grid")
	public String doGetShopGrid(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			Model model) {
		model.addAttribute("isShopPage", true);
		List<Products> products = new ArrayList<>();
		try {
			Page<Products> pageProducts = productsService.findAll(PAGE_SIZE, page);
			products = pageProducts.getContent();
			model.addAttribute("totalPages", pageProducts.getTotalPages());
			model.addAttribute("currentPage", page);
		} catch (Exception e) {
			products = productsService.findAll();
		}
		model.addAttribute("products", products);
		model.addAttribute("totalProducts", productsService.findAll().size());
		List<Products> discountProducts = productsService.findByDiscount();
		model.addAttribute("discountProducts", discountProducts);
		model.addAttribute("productTypes", productTypesService.findAll());
		return "shop-grid";
	}

	@GetMapping("shop-grid/{slug}")
	public String doGetShopGridByCategory(@RequestParam(value = "page", required = false, defaultValue = "1") int page,
			@PathVariable("slug") String categorySlug, Model model) {
		model.addAttribute("isShopPage", true);
		Categories category = categoriesService.findBySlug(categorySlug);
		if (categoriesService.findBySlug(categorySlug) == null) {
			return "not-found";
		}
		Long categoryId = category.getId();
		List<Products> products = new ArrayList<>();
		try {
			Page<Products> pageProducts = productsService.findAllByCategoryId(categoryId, PAGE_SIZE, page);
			products = pageProducts.getContent();
			model.addAttribute("totalPages", pageProducts.getTotalPages());
			model.addAttribute("currentPage", page);
		} catch (Exception e) {
			products = productsService.findAllByCategoryId(categoryId);
		}
		model.addAttribute("products", products);
		System.out.println(products);
		model.addAttribute("totalProducts", productsService.findAllByCategoryId(categoryId).size());
		List<Products> discountProducts = productsService.findByCategoryIdAndDiscount(categoryId);
		model.addAttribute("discountProducts", discountProducts);
		return "shop-grid";
	}

	@GetMapping("shop-details/{slug}")
	public String doGetShopDetail(@PathVariable("slug") String slug, Model model) {
		Products product = productsService.findBySlug(slug);
		if (product == null) {
			return "not-found";
		}
		System.out.println(product);
		List<ProductReviews> productReviews = productReviewsService.findAllByProductId(product.getId());
		List<Products> relatedProducts = productsService.findByRelatedProducts(product.getProductType(), slug);
		List<ProductImages> productImages = productImagesService.findByProductId(product.getId());
		model.addAttribute("product", product);
		model.addAttribute("productImages", productImages);
		model.addAttribute("productReviews", productReviews);
		model.addAttribute("relatedProducts", relatedProducts);
		return "shop-details";
	}

	@GetMapping("/search")
	public String search(@RequestParam("key") String key,
			@RequestParam(value = "page", required = false, defaultValue = "1") int page, Model model) {
		List<Products> products = new ArrayList<>();
		try {
			Page<Products> pageProducts = productsService.findByKeywords(key, PAGE_SIZE, page);
			products = pageProducts.getContent();
			model.addAttribute("totalProducts", productsService.findByKeywords(key).size());
			model.addAttribute("totalPages", pageProducts.getTotalPages());
			model.addAttribute("currentPage", page);
		} catch (Exception e) {
			products = productsService.findAll();
		}
		model.addAttribute("isSearched", Boolean.TRUE);
		model.addAttribute("products", products);
		return "shop-grid";
	}

	@PostMapping("/product/comment")
	public String comment(@RequestParam("productId") Long productId, @RequestParam("message") String message,
			RedirectAttributes ra, Model model, HttpSession session) throws UserNotFoundExcepion {
		Users user = (Users) session.getAttribute(SessionConstant.CURRENT_USER);
		if (user == null) {
			throw new UserNotFoundExcepion("Chưa đăng nhập");
		}
		ProductReviews productReviewNewest = productReviewsService.findTopByUserIdOrderByCreatedDateDesc(user.getId());
		Timestamp timestamp = productReviewNewest.getCreatedDate();
		long now = System.currentTimeMillis(); // See note below
		long then = timestamp.getTime();
		long minutes = TimeUnit.MILLISECONDS.toMinutes(now - then);
		if (minutes > 1) {
			productReviewsService.createReview(productId, user.getId(), message);
		} else {
			model.addAttribute("commentMessage", "Thí chủ xin dừng bước, bình luận đã quá nhanh. *1phuthon");
		}
		List<ProductReviews> productReviews = productReviewsService.findAllByProductId(productId);
		model.addAttribute("productReviews", productReviews);
		return "shop-details::#comment-list";
	}
}
