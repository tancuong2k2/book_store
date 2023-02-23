package com.datn.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.datn.service.StatsService;

@Controller(value = "HomeControllerOfAdmin")
@RequestMapping("/admin")
public class HomeController {

	@Autowired
	private StatsService statsService;
	
	@GetMapping("")
	public String doGetIndex(Model model) {
		model.addAttribute("isAdminHomePage", true);
		
		String[][] chartData = statsService.getTotalPriceAndTotalOrderAndTotalUserLast6Months();
		model.addAttribute("chartData", chartData);
		
		String totalPrice = chartData[1][5];
		model.addAttribute("totalPrice", totalPrice);

		String totalOrder = chartData[2][5];
		model.addAttribute("totalOrder", totalOrder);
		
		String totalUser = chartData[3][5];
		model.addAttribute("totalUser", totalUser);
		
		
		return "admin/index";
	}
}
