package com.liblib.book.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.liblib.book.model.Book;
import com.liblib.book.service.IBookService;

@RestController
@RequestMapping("/book")
@CrossOrigin(exposedHeaders = { "total-pages", "number-of-elements" })
public class BookController {

	@Autowired
	private IBookService bookService;
	
	@GetMapping("/findAll")
	public ResponseEntity<?> findAllBooks() {
		try {
			List<Book> resultList = bookService.findAll();
			return new ResponseEntity<>(resultList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/page")
	public ResponseEntity<?> showAllBooksInPage(@RequestParam(name = "p", defaultValue = "1") Integer pageNumber,
			@RequestParam(name = "size", defaultValue = "5") Integer dataSize) {
		try {
			// put book data in response body
			Page<Book> page = bookService.findByPage(pageNumber, dataSize);
			List<Book> courseResultList = page.getContent();

			// put TotalPages, numberOfElements in response header
			MultiValueMap<String, String> mvm = new LinkedMultiValueMap<>();
			mvm.add("total-pages", Integer.toString(page.getTotalPages()));
			mvm.add("number-of-elements", Integer.toString(page.getNumberOfElements()));

			return new ResponseEntity<>(courseResultList, mvm, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}
	
	@GetMapping("/single")
	public ResponseEntity<?> findBook(@RequestParam(name = "isbn", defaultValue = "") String isbn) {
		if(isbn.length()==0) return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
			
		try {
			Book resultBean = bookService.findByIsbn(isbn);
			return new ResponseEntity<>(resultBean, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
