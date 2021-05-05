package com.oraclejava.im_lending_app.controller;

import java.security.Principal;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.oraclejava.im_lending_app.dao.LendingBookRepository;
import com.oraclejava.im_lending_app.dao.UserInfoRepository;
import com.oraclejava.im_lending_app.dto.LendingBook;
import com.oraclejava.im_lending_app.dto.UserInfo;
import com.oraclejava.im_lending_app.service.LendingAppService;

@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private Cart cart;
	
	@Autowired
	private LendingBookRepository lendingBookRepository;
	
	@Autowired
	private LendingAppService lendingAppService;
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	private Map<String, String> selectAppFlg;
	
	private Map<String, String> initSelectAppFlg() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("하지않음", "0");
		map.put("신청", "1");
		return map;
	}
	
	@RequestMapping
	public String index(Model model) {
		model.addAttribute("contents", "cart :: cart_contents");
		model.addAttribute("cart", cart);
		
		LendingAppForm lendingAppForm = new LendingAppForm();
		lendingAppForm.setLendingBookList(cart.getItem());
		model.addAttribute("lendingAppForm", lendingAppForm);
		
		selectAppFlg = initSelectAppFlg();
		model.addAttribute("selectAppFlg", selectAppFlg);
		
		return "homeLayout";
	}
	
	@RequestMapping("/add/{book_id}")
	public String addCart(@PathVariable Integer book_id, Model model) {
		LendingBook lendingBook = lendingBookRepository.findById(book_id).get();
		cart.addItem(lendingBook);
		
		return "redirect:/cart";
	}
	
	//신청
	@RequestMapping(value="/apply", method=RequestMethod.POST)
	public String apply(@Validated LendingAppForm lendingAppForm, 
			BindingResult bindingResult, Model model, Principal user) {
		if (bindingResult.hasErrors()) {
			return index(model);
		}
		
		UserInfo userInfo = userInfoRepository.findByUsername(user.getName());
		lendingAppForm.setLending_user_id(userInfo.getUser_id());
		
		lendingAppService.apply(lendingAppForm);
		
		//장바구니 초기화
		cart.clearCart();
		
		return "redirect:/";
	}
}















