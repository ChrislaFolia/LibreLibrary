package com.liblib.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.liblib.book.service.IBorrowingRecordService;

@RestController
@RequestMapping("/br")
@CrossOrigin()
public class BorrowAndReturnController {

	@Autowired
	private IBorrowingRecordService brService;
	
	@PostMapping("/borrow")
	public ResponseEntity<?> borrowBooks(@RequestBody List<Integer> ids) {
		boolean result = brService.borrowBooks(ids);
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/return")
	public ResponseEntity<?> returnBooks(@RequestBody List<Integer> ids) {
		boolean result = brService.returnBooks(ids);
		if(result){
			return new ResponseEntity<>(HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
