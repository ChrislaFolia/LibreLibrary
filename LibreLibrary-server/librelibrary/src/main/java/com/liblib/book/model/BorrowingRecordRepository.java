package com.liblib.book.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface BorrowingRecordRepository extends JpaRepository<BorrowingRecord, Integer> {

	
	@Procedure(procedureName="INSERT_BORROWING_RECORD_BORROW")
	void borrowBooks(@Param("userId") Integer userId, @Param("inventoryId") Integer inventoryId);

	@Procedure(procedureName="UPDATE_BORROWING_RECORD_RETURN")
	void returnBooks(@Param("userId") Integer userId, @Param("inventoryId") Integer inventoryId);
}
