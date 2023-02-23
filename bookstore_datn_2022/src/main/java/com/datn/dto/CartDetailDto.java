package com.datn.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDetailDto implements Serializable {
	
	private static final long serialVersionUID = -4477941925255540968L;

	private Long orderId;
	private Long productId;
	private Double price;
	private Integer discount;
	private Integer quantity;
	private String name;
	private String slug;
	private String imgName;
	
}
