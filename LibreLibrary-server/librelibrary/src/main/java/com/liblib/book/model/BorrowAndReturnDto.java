package com.liblib.book.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class BorrowAndReturnDto {

	private List<Integer> inventoryIds;
	private int userId;
	
}
