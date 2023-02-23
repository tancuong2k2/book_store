package com.datn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.datn.entity.Orders;

@Repository
public interface StatsRepo extends JpaRepository<Orders, Long> {

	@Query(value = "{CALL sp_getTotalPricePerMonth(:month, :year)}", nativeQuery = true)
	String getTotalPricePerMonth(@Param("month") String month, @Param("year") String year);

	@Query(value = "{CALL sp_getTotalOrderPerMonth(:month, :year)}", nativeQuery = true)
	String getTotalOrderPerMonth(@Param("month") String month, @Param("year") String year);

	@Query(value = "{CALL sp_getTotalUserPerMonth(:month, :year)}", nativeQuery = true)
	String getTotalUserPerMonth(@Param("month") String month, @Param("year") String year);
}
