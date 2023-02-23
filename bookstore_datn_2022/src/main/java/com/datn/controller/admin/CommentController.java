package com.datn.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.datn.entity.ProductReviews;
import com.datn.service.ProductReviewsService;

@Controller(value = "CommentControllerOfAdmin")
@RequestMapping("/admin/comment")
public class CommentController {
	
	@Autowired
	private ProductReviewsService productReviewsService;
	

	@GetMapping("")
	public String doGetIndex(Model model) {
		model.addAttribute("isAdminCommentPage", true);

		List<ProductReviews> productReviews = productReviewsService.findAll();
		model.addAttribute("comments", productReviews);

		return "admin/comment";
	}
	
	@GetMapping("/delete")
	public String doGetDelete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
		try {
			productReviewsService.deleteById(id);
			redirectAttributes.addFlashAttribute("succeedMessage", "Comment " + id + " has been deleted");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "Cannot delete product " + id);
		}
		return "redirect:/admin/comment";
	}
}
