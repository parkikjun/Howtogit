package com.oraclejava.im_lending_app.controller;

import java.util.ArrayList;  
import java.util.List;

import com.oraclejava.im_lending_app.dto.CafeInfo;
import com.oraclejava.im_lending_app.dto.LendingBook;

public class CafeForm {
	
	private int cafe_id;
	private CafeInfo cafeInfo;
	private List<CafeInfo> cafeInfoList;
	
	//get, set
	public int getCafe_id() {
		return cafe_id;
	}
	public void setCafe_id(int cafe_id) {
		this.cafe_id = cafe_id;
	}
	public CafeInfo getCafeInfo() {
		return cafeInfo;
	}
	public void setCafeInfo(CafeInfo cafeInfo) {
		this.cafeInfo = cafeInfo;
	}
	public List<CafeInfo> getCafeInfoList() {
		return cafeInfoList;
	}
	public void setCafeInfoList(List<CafeInfo> cafeInfoList) {
		//this.lendingBookList = lendingBookList;
		this.cafeInfoList = new ArrayList<>();
		for (CafeInfo cafeInfo : cafeInfoList) {
			CafeInfo newCafeInfo = new CafeInfo();
			newCafeInfo.setCafeId(cafeInfo.getCafeId());
			newCafeInfo.setCafe_name(cafeInfo.getCafe_name());
			this.cafeInfoList.add(newCafeInfo);
		}
	}
	
	
	
}













