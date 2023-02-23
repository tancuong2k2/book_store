package com.datn.service;

public interface StatsService {

	String[][] getTotalPriceLast6Months();

	String[][] getTotalPriceAndTotalOrderLast6Months();

	String[][] getTotalPriceAndTotalOrderAndTotalUserLast6Months();
}
