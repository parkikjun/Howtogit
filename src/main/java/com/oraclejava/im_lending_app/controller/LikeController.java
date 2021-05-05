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

import com.oraclejava.im_lending_app.dao.CafeInfoRepository;
import com.oraclejava.im_lending_app.dao.LendingBookRepository;
import com.oraclejava.im_lending_app.dao.UserInfoRepository;
import com.oraclejava.im_lending_app.dto.CafeInfo;
import com.oraclejava.im_lending_app.dto.LendingBook;
import com.oraclejava.im_lending_app.dto.UserInfo;
import com.oraclejava.im_lending_app.service.LendingAppService;
import com.oraclejava.im_lending_app.service.LikeService;

@Controller
@RequestMapping("/like")
public class LikeController {

	@Autowired
	private Like like;
	
	@Autowired
	private CafeInfoRepository cafeInfoRepository;
	
	@Autowired
	private LikeService likeService;
	
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
		model.addAttribute("contents", "like :: like_contents");
		model.addAttribute("like", like);
		
		CafeForm cafeForm = new CafeForm();
		cafeForm.setCafeInfoList(like.getItem());
		model.addAttribute("cafeForm", cafeForm);
		
		selectAppFlg = initSelectAppFlg();
		model.addAttribute("selectAppFlg", selectAppFlg);
		
		return "homeLayout";
	}
	
	@RequestMapping("/add/{cafeId}")
	public String addLike(@PathVariable Integer cafeId, Model model) {
		CafeInfo cafeInfo = cafeInfoRepository.findById(cafeId).get();
		like.addItem(cafeInfo);
		
		return "redirect:/like";
	}
	
	//신청
		@RequestMapping(value="/apply", method=RequestMethod.POST)
		public String apply(@Validated CafeForm cafeForm, 
				BindingResult bindingResult, Model model, Principal user) {
			if (bindingResult.hasErrors()) {
				return index(model);
			}
			
			UserInfo userInfo = userInfoRepository.findByUsername(user.getName());
			cafeForm.setCafe_id(userInfo.getUser_id());
			
			likeService.apply(cafeForm);
			
			//장바구니 초기화
			like.clearCart();
			
			return "redirect:/";
		}
	
}















