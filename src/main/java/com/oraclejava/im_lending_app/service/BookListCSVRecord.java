package com.oraclejava.im_lending_app.service;

import com.univocity.parsers.annotations.Parsed;

public class BookListCSVRecord {
	
	@Parsed
	private String isbn;
	
	@Parsed(field = "제목")
	private String bookName;

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	
	
	//get, set
}
