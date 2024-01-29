package com.liblib.book.model;

import lombok.Data;

@Data
public class BorrowingRecordDto {

	private Integer recordId;
	
	private Integer inventoryId;

	private String isbn;
	
	private String name;
	
	private String author;
    
	private String borrowingTime;

	private String expirationTime;

	private Integer userId;
	
    
    
	
}
