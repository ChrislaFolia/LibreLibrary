package com.liblib.book.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liblib.book.model.BorrowAndReturnDto;
import com.liblib.book.model.BorrowingRecordDto;
import com.liblib.book.service.IBorrowingRecordService;

@RestController
@RequestMapping("/br")
@CrossOrigin()
public class BorrowAndReturnController {

	@Autowired
	private IBorrowingRecordService brService;
	
	@PostMapping("/borrow")
	public ResponseEntity<?> borrowBooks(@RequestBody BorrowAndReturnDto brBean) {
		boolean result = brService.borrowBooks(brBean.getUserId(),brBean.getInventoryIds());
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/return")
	public ResponseEntity<?> returnBooks(@RequestBody BorrowAndReturnDto brBean) {
		boolean result = brService.returnBooks(brBean.getUserId(),brBean.getInventoryIds());
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/getborrowed")
	public ResponseEntity<?> findRecordsByBorrowed(@RequestParam(name = "userId") int userId) {
		
		try {
			List<Object[]> resultObjectList = brService.findRecordsByBorrowed(userId);
			List<BorrowingRecordDto>  resultList = new ArrayList<>();
			BorrowingRecordDto tempDto = new BorrowingRecordDto();
			for(Object[] record: resultObjectList) {
				System.out.println(record);
				tempDto.setRecordId((Integer)record[0]);
				tempDto.setInventoryId((Integer)record[1]);
				tempDto.setIsbn((String)record[2]);
				tempDto.setName((String)record[3]);
				tempDto.setAuthor((String)record[4]);
				tempDto.setBorrowingTime((Date)record[5]);
				tempDto.setUserId((Integer)record[6]);
				resultList.add(tempDto);
			}
			return new ResponseEntity<>(resultList,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
