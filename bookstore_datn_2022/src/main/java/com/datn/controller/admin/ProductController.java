package com.datn.controller.admin;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.datn.entity.Categories;
import com.datn.entity.ProductTypes;
import com.datn.entity.Products;
import com.datn.service.CategoriesService;
import com.datn.service.ProductTypesService;
import com.datn.service.ProductsService;

@Controller(value = "ProductControllerOfAdmin")
@RequestMapping("/admin/product")
public class ProductController {

	@Autowired
	private ProductsService productsService;

	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private ProductTypesService productTypesService;

	@GetMapping("")
	public String doGetIndex(Model model) {
		model.addAttribute("isAdminProductPage", true);
		model.addAttribute("isAdminProductPage1", true);

		List<Products> products = productsService.findAll();
		model.addAttribute("products", products);

		List<Categories> categories = categoriesService.findAll();
		model.addAttribute("categories", categories);

		List<ProductTypes> productTypes = productTypesService.findAll();
		model.addAttribute("productTypes", productTypes);

		model.addAttribute("productRequest", new Products());
		return "admin/product";
	}
	
	// Admin product - recovery
	@GetMapping("/recovery")
	public String doGetRecovery(Model model) {
		model.addAttribute("isAdminProductPage", true);
		model.addAttribute("isAdminProductPage3", true);
		
		List<Products> products = productsService.findByIsDeleted();
		model.addAttribute("products", products);
		return "admin/product-recovery";
	}
	
	@GetMapping("/recovery/recovery")
	public String doGetRecoveryTrue(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			productsService.recoveryLogical(id);
			redirectAttributes.addFlashAttribute("succeedMessage", "Product " + id + " was recovered");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Cannot recovery product " + id);
		}
		return "redirect:/admin/product/recovery";
	}

	@GetMapping("/edit")
	public String doGetEditProduct(@RequestParam("id") Long id, Model model) {
		Products productRequest = productsService.findById(id);
		model.addAttribute("productRequest", productRequest);

		List<Categories> categories = categoriesService.findAll();
		model.addAttribute("categories", categories);

		List<ProductTypes> productTypes = productTypesService.findAll();
		model.addAttribute("productTypes", productTypes);

		Long categorySelected = productRequest.getCategory().getId();
		Long productTypeSelected = productRequest.getProductType().getId();
		model.addAttribute("categorySelected", categorySelected);
		model.addAttribute("productTypeSelected", productTypeSelected);
		return "admin/product::#form";
	}

	// /admin/product/delete?id={...}
	@GetMapping("/delete")
	public String doGetDelete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			productsService.deleteLogical(id);
			redirectAttributes.addFlashAttribute("succeedMessage", "Product " + id + " has been deleted");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete product " + id);
		}
		return "redirect:/admin/product";
	}

	@PostMapping("/create")
	public String doPostCreateProduct(@Valid @ModelAttribute("productRequest") Products productRequest,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@RequestParam("categories") Long categoryId, @RequestParam("productTypes") Long productTypeId,
			@RequestParam("attach") MultipartFile attach) {
		if (bindingResult.hasErrors()) {
			redirectAttributes.addFlashAttribute("errorMessage", "Product is not valid");
		} else {
			try {
				String imageHashName = "";
				Boolean isImageAdded = false;
				if (!attach.isEmpty()) {
					Path path = Paths.get("src/main/resources/static/user/img/product");

					if (!Files.exists(path)) {
						Files.createDirectories(path);
					}
					InputStream inputStream = attach.getInputStream();
					String s = System.currentTimeMillis() + attach.getOriginalFilename();
					imageHashName = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
					Files.copy(inputStream, path.resolve(imageHashName), StandardCopyOption.REPLACE_EXISTING);
					productRequest.setImgName(imageHashName);
					isImageAdded = true;
				}
				productsService.save(productRequest, categoryId, productTypeId);
				Long productId = productsService.findBySlug(productRequest.getSlug()).getId();
				if (isImageAdded) {
					// Tao thu muc anh cua product
					Path pathAfter = Paths.get("src/main/resources/static/user/img/product", productId.toString());
					if (!Files.exists(pathAfter)) {
						Files.createDirectories(pathAfter);
					}
					Files.move(Paths.get("src/main/resources/static/user/img/product", imageHashName), Paths
							.get("src/main/resources/static/user/img/product", productId.toString(), imageHashName),
							StandardCopyOption.REPLACE_EXISTING);
				}
				redirectAttributes.addFlashAttribute("succeedMessage",
						"Product " + productRequest.getName() + " has been created successfully");
			} catch (Exception e) {
				e.printStackTrace();
				redirectAttributes.addFlashAttribute("succeedMessage",
						"Cannot create product: " + productRequest.getName() + ". " + e.getMessage());
			}
		}
		return "redirect:/admin/product";
	}

	@PostMapping("/edit/{id}")
	public String doPostEditProduct(@Valid @ModelAttribute("productRequest") Products productRequest,
			BindingResult bindingResult, RedirectAttributes redirectAttributes,
			@RequestParam("categories") Long categoryId, @RequestParam("productTypes") Long productTypeId,
			@RequestParam("attach") MultipartFile attach, @PathVariable("id") Long id) {
		Products checkProduct = productsService.findById(id);
		if (checkProduct != null) {
			if (bindingResult.hasErrors()) {
				redirectAttributes.addFlashAttribute("errorMessage", "Product is not valid");
			} else {
				try {
					String imageHashName = "";
					Boolean isImageAdded = false;
					if (!attach.isEmpty()) {
						Path path = Paths.get("src/main/resources/static/user/img/product");

						if (!Files.exists(path)) {
							Files.createDirectories(path);
						}
						InputStream inputStream = attach.getInputStream();
						String s = System.currentTimeMillis() + attach.getOriginalFilename();
						imageHashName = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
						Files.copy(inputStream, path.resolve(imageHashName), StandardCopyOption.REPLACE_EXISTING);
						productRequest.setImgName(imageHashName);
						isImageAdded = true;
					} else {
						productRequest.setImgName(checkProduct.getImgName());
					}
					productsService.update(productRequest, categoryId, productTypeId);
					if (isImageAdded) {
						// Tao thu muc anh cua product
						Path pathAfter = Paths.get("src/main/resources/static/user/img/product", id.toString());
						if (!Files.exists(pathAfter)) {
							Files.createDirectories(pathAfter);
						}
						Files.move(Paths.get("src/main/resources/static/user/img/product", imageHashName),
								Paths.get("src/main/resources/static/user/img/product", id.toString(), imageHashName),
								StandardCopyOption.REPLACE_EXISTING);
					}
					redirectAttributes.addFlashAttribute("succeedMessage",
							"Product " + productRequest.getName() + " has been edited successfully");
				} catch (Exception e) {
					e.printStackTrace();
					redirectAttributes.addFlashAttribute("succeedMessage",
							"Cannot edit product: " + productRequest.getName());
				}
			}
		} else {
			redirectAttributes.addFlashAttribute("errorMessage", "Cannot found product has id: " + id);
		}
		return "redirect:/admin/product";
	}
}
