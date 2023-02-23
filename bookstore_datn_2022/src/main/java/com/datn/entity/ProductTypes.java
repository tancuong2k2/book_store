package com.datn.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_types")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductTypes implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -6613646720452431900L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "slug")
	private String slug;
	
	@Column(name = "isDeleted")
	private Boolean isDeleted;
}
