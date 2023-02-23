package com.datn.service.impl;

import java.time.YearMonth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.repository.StatsRepo;
import com.datn.service.StatsService;

@Service
public class StatsServiveImpl implements StatsService {

	@Autowired
	private StatsRepo repo;

	@Override
	public String[][] getTotalPriceLast6Months() {
		String[][] result = new String[2][6];
		YearMonth currentTimes = YearMonth.now();
		for (int i = 0; i < 6; i++) {
			String month = currentTimes.minusMonths((long) i).getMonthValue() + "";
			String year = currentTimes.minusMonths((long) i).getYear() + "";
			result[0][5 - i] = month + "-" + year;
			result[1][5 - i] = repo.getTotalPricePerMonth(month, year);
		}
		return result;
	}

	@Override
	public String[][] getTotalPriceAndTotalOrderLast6Months() {
		String[][] result = new String[3][6];
		YearMonth currentTimes = YearMonth.now();
		for (int i = 0; i < 6; i++) {
			String month = currentTimes.minusMonths((long) i).getMonthValue() + "";
			String year = currentTimes.minusMonths((long) i).getYear() + "";
			result[0][5 - i] = month + "-" + year;
			result[1][5 - i] = repo.getTotalPricePerMonth(month, year);
			result[2][5 - i] = repo.getTotalOrderPerMonth(month, year);
		}
		return result;
	}

	@Override
	public String[][] getTotalPriceAndTotalOrderAndTotalUserLast6Months() {
		String[][] result = new String[4][6];
		YearMonth currentTimes = YearMonth.now();
		for (int i = 0; i < 6; i++) {
			String month = currentTimes.minusMonths((long) i).getMonthValue() + "";
			String year = currentTimes.minusMonths((long) i).getYear() + "";
			result[0][5 - i] = month + "-" + year;
			result[1][5 - i] = repo.getTotalPricePerMonth(month, year);
			result[2][5 - i] = repo.getTotalOrderPerMonth(month, year);
			result[3][5 - i] = repo.getTotalUserPerMonth(month, year);
		}
		return result;
	}

}
