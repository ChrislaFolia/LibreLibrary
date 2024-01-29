package com.liblib.book.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.liblib.book.model.BorrowingRecordRepository;

import jakarta.transaction.Transactional;

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
	@Transactional
	public List<Object[]> findRecordsByBorrowed(Integer userId) {
		List<Object[]> resultBean = brRepo.findMultiTableDataByUserIdAndReturnTimeIsNull(userId);
		return  resultBean;
	}
	
	

}
