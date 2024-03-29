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
@Entity
@Table(name = "BorrowingRecord")
public class BorrowingRecord {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recordId;
    
	@Column(name = "userId",nullable = false, columnDefinition = "int")
	private Integer userId;
	
	@Column(name = "inventoryId",nullable = false, columnDefinition = "int")
    private Integer inventoryId;
	
	@Column(name = "borrowingTime",nullable = false, columnDefinition = "datetime2")
    private Date borrowingTime;
	
	@Column(name = "returnTime", columnDefinition = "datetime2")
    private Date returnTime;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="userId",insertable = false,updatable = false)
	private LibUser libUser;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="inventoryId",insertable = false,updatable = false)
	private Inventory inventory;
	
}
