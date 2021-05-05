package com.oraclejava.im_lending_app.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.im_lending_app.dao.CafeInfoRepository;
import com.oraclejava.im_lending_app.dao.LendingBookRepository;
import com.oraclejava.im_lending_app.dto.CafeInfo;

@Controller
public class HomeController {
	
	@Autowired
	private LendingBookRepository lendingBookRepository;
	@Autowired
	private CafeInfoRepository cafeRepository;
	
	private static final int PAGE_SIZE = 4;

	@Autowired
	ResourceLoader resourceLoader;
	
	@RequestMapping(value={"/","/{pageNumber}"}, method=RequestMethod.GET)
	public ModelAndView home(@PathVariable Optional<Integer> pageNumber) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("lendingBooks", lendingBookRepository.findTo12Books());
		
		mav.addObject("contents", "index :: endGame");
		mav.setViewName("homeLayout");
		
		
		Page<CafeInfo> ItemList = cafeRepository.findTo6Item(
				PageRequest.of(pageNumber.isPresent() ? pageNumber.get()-1 : 0, PAGE_SIZE, Sort.by(Sort.Direction.DESC, "cafeId")));
				
		int current = ItemList.getNumber() +1;
		int begin = 1;
		int end = ItemList.getTotalPages();
		
		mav.addObject("cafeList", ItemList);
		mav.addObject("beginIndex", begin);
		mav.addObject("endIndex", end);
		mav.addObject("currentIndex", current);
		return mav;
	}
	
	@RequestMapping(value="/allbooks", method=RequestMethod.GET)
	public ModelAndView allbooks() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("lendingBooks", lendingBookRepository.findAllOrderByBookName());
		
		mav.addObject("contents", "allbook :: allbook_contents");
		mav.setViewName("homeLayout");
		return mav;
	}
	
	@RequestMapping(value="/403", method=RequestMethod.GET)
	public ModelAndView accessDenied(Principal user) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("403");
		if (user != null) {
			//로그인 완료시
			mav.addObject("msg", 
				user.getName() + "님아, 이 페이지에 들어오지마오!");
		} else {
			mav.addObject("msg", "님아, 이 페이지에 들어오지마오!");
		}
		return mav;
	}
}








