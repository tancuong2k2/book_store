package com.datn.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.datn.entity.OrderDetails;
import com.datn.entity.Orders;
import com.datn.service.OrderDetailsService;
import com.datn.service.OrdersService;

@Controller(value = "OrderControllerOfAdmin")
@RequestMapping("/admin/order")
public class OrderController {
	
	@Autowired
	private OrdersService ordersService;
	
	@Autowired
	private OrderDetailsService orderDetailsService;

	@GetMapping("")
	public String doGetIndex(Model model) {
		model.addAttribute("isAdminOrderPage", true);
		model.addAttribute("isAdminOrderPage1", true);

		List<Orders> orders = ordersService.findByOrderStatus();
		model.addAttribute("orders", orders);

		return "admin/order";
	}
	
	@GetMapping("/orderdetails")
	public String doGetOrderDetails(@RequestParam("id") Long id, Model model) {
		List<OrderDetails> orderDetails = orderDetailsService.findByOrderId(id);
		Orders order = ordersService.findById(id);
		model.addAttribute("order", order);
		model.addAttribute("orderStatusSelected", order.getOrderStatus().toString());
		model.addAttribute("orderDetails", orderDetails);
		return "admin/order::#table-order-details";
	}
	
	@GetMapping("/orderStatus")
	public String doPostOrderStatus(@RequestParam("id") Long id, @RequestParam("orderStatus") String orderStatus, Model model) {
		ordersService.updateOrderStatus(id, orderStatus);
		return "forward:/admin/order";
	}
}
