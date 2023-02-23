package com.datn.entity;

public enum OrderStatuses {

	CHUA_XAC_NHAN("Chưa xác nhận"), DA_XAC_NHAN("Đã xác nhận"), DANG_GIAO_HANG("Đang giao hàng"),
	DA_GIAO_HANG("Đã giao hàng");

	private final String displayValue;

	private OrderStatuses(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getDisplayValue() {
		return displayValue;
	}
}
