package com.liblib.book.model;

import java.util.Date;

import lombok.Data;

@Data
public class BorrowingRecordDto {

	private Integer recordId;
	
	private Integer inventoryId;

	private String isbn;
	
	private String name;
	
	private String author;
    
	private Date borrowingTime;

	private Integer userId;
	
    
    
	
}
