package com.liblib.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liblib.book.model.BorrowingRecordRepository;

@Service
public class BorrowingRecordService implements IBorrowingRecordService {

	@Autowired
	private BorrowingRecordRepository brRepo;
	
	@Override
	public boolean borrowBooks(List<Integer> ids) {
		return false;
	}

	@Override
	public boolean returnBooks(List<Integer> ids) {
		return false;
	}

}
