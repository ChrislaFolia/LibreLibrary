package com.liblib.book.service;

import java.util.List;

public interface IBorrowingRecordService {

	boolean borrowBooks(List<Integer> ids);

	boolean returnBooks(List<Integer> ids);
	
}
