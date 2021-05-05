package com.oraclejava.im_lending_app.controller;

import java.util.ArrayList;
import java.util.List;

import com.oraclejava.im_lending_app.dto.LendingApp;
import com.oraclejava.im_lending_app.dto.LendingBook;

public class LendingAppForm {
	
	private int lending_user_id;
	private LendingApp lendingApp;
	private List<LendingBook> lendingBookList;
	
	//get, set
	public int getLending_user_id() {
		return lending_user_id;
	}
	public void setLending_user_id(int lending_user_id) {
		this.lending_user_id = lending_user_id;
	}
	public LendingApp getLendingApp() {
		return lendingApp;
	}
	public void setLendingApp(LendingApp lendingApp) {
		this.lendingApp = lendingApp;
	}
	public List<LendingBook> getLendingBookList() {
		return lendingBookList;
	}
	public void setLendingBookList(List<LendingBook> lendingBookList) {
		//this.lendingBookList = lendingBookList;
		this.lendingBookList = new ArrayList<>();
		for (LendingBook lendingBook : lendingBookList) {
			LendingBook newLendingBook = new LendingBook();
			newLendingBook.setLending_book_id(lendingBook.getLending_book_id());
			newLendingBook.setLending_app_flg(lendingBook.getLending_app_flg());
			newLendingBook.setLending_app_reason(lendingBook.getLending_app_reason());
			this.lendingBookList.add(newLendingBook);
		}
	}
	
	
	
}













