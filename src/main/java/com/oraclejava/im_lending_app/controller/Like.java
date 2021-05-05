package com.oraclejava.im_lending_app.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.oraclejava.im_lending_app.dto.CafeInfo;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, 
	proxyMode = ScopedProxyMode.TARGET_CLASS)
public class Like implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private List<CafeInfo> item;  //장바구니 아이템
	private int totalQty;  //총수량
	
	//기본 생성자(default constructor)
	public Like() {
		this.item = new ArrayList<>();
		this.totalQty = 0;
	}
	
	//물건 담기
	public void addItem(CafeInfo cafeInfo) {
		this.item.add(cafeInfo);
		this.totalQty++;
	}
	
	//장바구니 비우기
	public void clearCart() {
		item.clear();
		totalQty = 0;
	}
	
	//get, set
	public List<CafeInfo> getItem() {
		return item;
	}
	public void setItem(List<CafeInfo> item) {
		this.item = item;
	}
	public int getTotalQty() {
		return totalQty;
	}
	public void setTotalQty(int totalQty) {
		this.totalQty = totalQty;
	}
}
