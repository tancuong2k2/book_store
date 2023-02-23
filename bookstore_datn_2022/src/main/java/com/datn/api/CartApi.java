package com.datn.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.datn.constant.SessionConstant;
import com.datn.dto.CartDto;
import com.datn.entity.Users;
import com.datn.service.CartService;
import com.datn.util.QuantityException;
import com.datn.util.SessionUtil;


@RestController
@RequestMapping("/api/cart")
public class CartApi {

	@Autowired
	private CartService cartService;

	// /api/cart/update?productId=...&quantity=...&isReplace=...
	@GetMapping("/update")
	public ResponseEntity<?> doGetUpdate(@RequestParam("productId") Long productId,
			@RequestParam("quantity") Integer quantity, @RequestParam("isReplace") Boolean isReplace,
			HttpSession session) {
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		cartService.updateCart(currentCart, productId, quantity, isReplace);
		return ResponseEntity.ok(currentCart);
	}

	@GetMapping("/refresh")
	public ResponseEntity<?> doGetRefresh(HttpSession session) {
		CartDto currentCart = SessionUtil.getCurrentCart(session);
		return ResponseEntity.ok(currentCart);
	}

	// /api/cart/checkout?address=...&phone=...
	@GetMapping("/checkout")
	public ResponseEntity<?> doGetCheckout(@RequestParam("address") String address, @RequestParam("phone") String phone,
			HttpSession session) {
		Users currentUser = (Users) session.getAttribute(SessionConstant.CURRENT_USER);

		if (!ObjectUtils.isEmpty(currentUser)) {
			CartDto currentCart = SessionUtil.getCurrentCart(session);
			try {
				cartService.insert(currentCart, currentUser, address, phone);
				session.setAttribute(SessionConstant.CURRENT_CART, new CartDto());
				return new ResponseEntity<>(HttpStatus.OK);
			} catch (QuantityException e) {
				return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}
}
