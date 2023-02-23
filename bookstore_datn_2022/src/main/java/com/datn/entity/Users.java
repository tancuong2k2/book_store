package com.datn.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class Users implements Serializable {

	private static final long serialVersionUID = -8379333422254368439L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/*
	 * @NotNull
	 */	@Column(name = "username")
	@Size(min = 5, max = 20, message = "Tên đăng nhập phải lớn hơn 5 và ít hơn 20 ký tự")
	private String username;

	@Column(name = "fullname")
	@Size(min = 5, max = 50, message = "Họ và tên phải lớn hơn 5 và ít hơn 50 ký tự")
	private String fullname;

	@Column(name = "hashPassword")
	private String hashPassword;

	@Column(name = "email")
	private String email;

	@Column(name = "isEnabled")
	private Boolean isEnabled;

	@Column(name = "authProvider")
	private String authProvider;

	@Column(name = "resetPasswordToken")
	private String resetPasswordToken;

	@Column(name = "verificationCode")
	private String verificationCode;

	@Column(name = "createdDate")
	@CreationTimestamp
	private Timestamp createdDate;

	@Column(name = "imgUrl")
	private String imgUrl;

	@Column(name = "isDeleted")
	private Boolean isDeleted;

	@ManyToOne
	@JoinColumn(name = "roleId", referencedColumnName = "id")
	@JsonIgnoreProperties(value = { "application", "hibernateLazyInitializer" })
	private Roles role;

	@Transient
	public String getAvatarImagePath() {
		if (imgUrl == null) {
			return "/user-avatar/default.png";
		}
		return "/user-avatar/" + imgUrl;
	}
}
