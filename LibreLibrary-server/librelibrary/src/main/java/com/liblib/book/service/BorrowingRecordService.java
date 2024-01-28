package com.liblib.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liblib.book.model.BorrowingRecord;
import com.liblib.book.model.BorrowingRecordDto;
import com.liblib.book.model.BorrowingRecordRepository;

@Service
public class BorrowingRecordService implements IBorrowingRecordService {

	@Autowired
	private BorrowingRecordRepository brRepo;
	
	@Override
	public boolean borrowBooks( Integer userId,List<Integer> ids) {
		int bookAmount = ids.size();
		try {
			for (int i = 0; i < bookAmount; i++) {
				brRepo.borrowBooks(userId,ids.get(i));
			}
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
	}

	@Override
	public boolean returnBooks(Integer userId,List<Integer> ids) {
		int bookAmount = ids.size();
		try {
			for (int i = 0; i < bookAmount; i++) {
				brRepo.returnBooks(userId,ids.get(i));
			}
			return true;
		} catch (Exception e) {
			e.getStackTrace();
			return false;
		}
	}

	@Override
	public List<BorrowingRecord> findRecordsByBorrowed(Integer userId) {
		
		List<BorrowingRecord> resultBean = brRepo.findByUserIdAndReturnTimeIsNull(userId);
		
		return  resultBean;
	}
	
	

}
