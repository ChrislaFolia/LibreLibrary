package com.liblib.book.service;

import java.util.List;

import com.liblib.book.model.BorrowingRecord;

public interface IBorrowingRecordService {

	boolean borrowBooks(Integer userId,List<Integer> ids);

	boolean returnBooks(Integer userId,List<Integer> ids);
	
	List<BorrowingRecord> findRecordsByBorrowed(Integer userId);
	
}
