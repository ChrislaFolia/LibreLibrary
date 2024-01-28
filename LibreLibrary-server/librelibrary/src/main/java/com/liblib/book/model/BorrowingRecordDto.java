package com.liblib.book.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.liblib.user.model.LibUser;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
public class BorrowingRecordDto {


	private Integer recordId;
    
	private Integer userId;
	
    private Integer inventoryId;
	
    private Date borrowingTime;
	
}
