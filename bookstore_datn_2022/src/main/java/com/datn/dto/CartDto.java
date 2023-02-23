package com.datn.dto;

import java.io.Serializable;
import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDto implements Serializable {

	private static final long serialVersionUID = 5194599303116963858L;

	private Long userId;
	private String address;
	private String phone;
	private Double totalPrice = 0D;
	private Integer totalQuantity = 0;
	private Double shippingFee;
	private HashMap<Long, CartDetailDto> details = new HashMap<>();
	
}
