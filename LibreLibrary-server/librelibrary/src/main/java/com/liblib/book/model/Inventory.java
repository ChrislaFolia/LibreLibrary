package com.liblib.book.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Inventory")
public class Inventory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer inventoryId;
	
	@Column(name = "isbn",nullable = false, columnDefinition = "varchar(20)")
    private String isbn;

	@Column(name = "storeTime",nullable = false, columnDefinition = "datetime2")
    private Date storeTime;

	@Column(name = "status",nullable = false, columnDefinition = "nvarchar(25)")
    private String status;
	
	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "inventory")
	private List<BorrowingRecord> borrowingRecord = new ArrayList<>();
	
	@JsonIgnoreProperties({"introduction","isbn"})
	@ManyToOne
	@JoinColumn(name="isbn",insertable = false,updatable = false)
	private Book book;
}
