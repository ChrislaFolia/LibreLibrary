package com.liblib.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liblib.book.model.Inventory;
import com.liblib.book.service.IInventoryService;

@RestController
@RequestMapping("/book/detail")
@CrossOrigin()
public class InventoryController {

	@Autowired
	private IInventoryService iService;
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllInventories() {
		try {
			List<Inventory> resultList = iService.findAll();
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping()
	public ResponseEntity<?> findInventory(@RequestParam(name = "isbn", defaultValue = "") String isbn) {
		if(isbn.length()==0) return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
			
		try {
			List<Inventory> resultList = iService.findByIsbn(isbn);
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
