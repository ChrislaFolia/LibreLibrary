package com.liblib.book.service;

import java.util.List;

public interface IBorrowingRecordService {

	boolean borrowBooks(Integer userId,List<Integer> ids);

	boolean returnBooks(Integer userId,List<Integer> ids);
	
	List<Object[]> findRecordsByBorrowed(Integer userId);
	
}
