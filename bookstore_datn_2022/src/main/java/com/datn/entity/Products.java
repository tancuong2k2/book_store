package com.datn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "products")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Products implements Serializable {

	private static final long serialVersionUID = 6997228053827235423L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "quantity")
	@Min(value = 0)
	@Max(value = 10000)
	private Integer quantity;
	
	@Column(name = "price")
	@Min(value = 1000)
	@Max(value = 100000000)
	private Double price;
	
	@Column(name = "imgName")
	private String imgName;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "discount")
	private Integer discount;
	
	@Column(name = "slug")
	private String slug;
	
	@Column(name = "isDeleted")
	private Boolean isDeleted;
	
	@ManyToOne
	@JoinColumn(name = "categoryId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private Categories category;
	
	@ManyToOne
	@JoinColumn(name = "productTypeId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
	private ProductTypes productType;
}
