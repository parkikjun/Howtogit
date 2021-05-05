package com.oraclejava.im_lending_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "lending_book")
public class LendingBook {

	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="lendingBookGenerator")
	@SequenceGenerator(name="lendingBookGenerator", sequenceName = "lending_book_seq", allocationSize = 1)
	private int lending_book_id;
	private int lending_app_id;
	
	@NotEmpty(message = "ISBN는 필수입니다.")
	private String isbn;
	
	@NotEmpty(message = "책명은 필수입니다.")
	private String book_name;
	
	private String lending_app_flg;
	private String lending_app_reason;
	private String lending_state;
	private String applicate_status;
	private String applicate_reason;

	public int getLending_book_id() {
		return lending_book_id;
	}

	public void setLending_book_id(int lending_book_id) {
		this.lending_book_id = lending_book_id;
	}

	public int getLending_app_id() {
		return lending_app_id;
	}

	public void setLending_app_id(int lending_app_id) {
		this.lending_app_id = lending_app_id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getBook_name() {
		return book_name;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getLending_app_flg() {
		return lending_app_flg;
	}

	public void setLending_app_flg(String lending_app_flg) {
		this.lending_app_flg = lending_app_flg;
	}

	public String getLending_app_reason() {
		return lending_app_reason;
	}

	public void setLending_app_reason(String lending_app_reason) {
		this.lending_app_reason = lending_app_reason;
	}

	public String getLending_state() {
		return lending_state;
	}

	public void setLending_state(String lending_state) {
		this.lending_state = lending_state;
	}

	public String getApplicate_status() {
		return applicate_status;
	}

	public void setApplicate_status(String applicate_status) {
		this.applicate_status = applicate_status;
	}

	public String getApplicate_reason() {
		return applicate_reason;
	}

	public void setApplicate_reason(String applicate_reason) {
		this.applicate_reason = applicate_reason;
	}

}
