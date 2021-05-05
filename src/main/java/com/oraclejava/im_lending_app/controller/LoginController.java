package com.oraclejava.im_lending_app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.im_lending_app.dao.UserInfoRepository;
import com.oraclejava.im_lending_app.dto.UserInfo;

@Controller
public class LoginController {
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.GET)
	public String registration(Model model) {
		UserInfo userInfo = new UserInfo();
		model.addAttribute("userInfo", userInfo);
		return "registration";
	}
	
	@RequestMapping(value="/registration", method = RequestMethod.POST)
	public String createNewUser(
			@Validated UserInfo userInfo, 
			BindingResult bindingResult,
			Model model) {
		//있는 사용자인지?
		UserInfo userExists = 
				userInfoRepository.findByUsername(userInfo.getUsername());
		if (userExists != null) {
			bindingResult.rejectValue("username", 
					"error.user", "아이디가 중복되었습니다");
		}
		//패스워드가 일치한지 체크
		if (!userInfo.getPassword().equals(userInfo.getPassword2())) {
			bindingResult.rejectValue("password2", 
				"error.password", "패스워드가 일치하지 않습니다");
		}
		
		
		if (bindingResult.hasErrors()) {
			return "registration";
		} else {
			userInfo.setPassword("{noop}" + userInfo.getPassword());
			userInfo.setEnabled(true);  // 회원가입시 회원활성화
			userInfoRepository.save(userInfo);
			return "redirect:/login";
		}
	}
}









