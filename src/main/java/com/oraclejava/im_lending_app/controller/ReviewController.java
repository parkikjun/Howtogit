package com.oraclejava.im_lending_app.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.oraclejava.im_lending_app.dao.CafeInfoRepository;

import com.oraclejava.im_lending_app.dao.ReviewInfoRepository;
import com.oraclejava.im_lending_app.dao.UserInfoRepository;
import com.oraclejava.im_lending_app.dto.CafeInfo;

import com.oraclejava.im_lending_app.dto.ReviewInfo;
import com.oraclejava.im_lending_app.dto.UserInfo;
@Controller
public class ReviewController {
	private static final int PAGE_SIZE = 5;
	@Autowired
	private ReviewInfoRepository reviewInfoRepository;
	@Autowired
	private UserInfoRepository userInfoRepository;
	@Autowired
	private CafeInfoRepository cafeInfoRepository;
	@GetMapping("/cafeReviewList/{cafeId}") 

    public String cafeReviewList(Principal principal , 
    		@PageableDefault Pageable pageable,

    		@PathVariable Integer cafeId, Model model) {
		CafeInfo cafeInfo = cafeInfoRepository.findById(cafeId).get();
		UserInfo userInfo = userInfoRepository.findByUsername(principal.getName());

		Page<ReviewInfo> reviewInfos = reviewInfoRepository.findAllByCafeInfo(cafeInfo,
				PageRequest.of(
						(pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1),
						PAGE_SIZE, 
						Sort.by("createdate").descending())
				);
		int current = reviewInfos.getNumber() +1;
		int hegin = 1;
		int end = reviewInfos.getTotalPages();
		
		model.addAttribute("cafeReviewList", reviewInfos);
		model.addAttribute("beginIndex", hegin);
		model.addAttribute("endIndex", end);
		model.addAttribute("currentIndex", current);
		//model.addAttribute("cafeReviewList",  reviewInfoRepository.findAllByCafeInfo(cafeInfo));
		//model.addAttribute("cafeReviewList",  reviewInfos);
		model.addAttribute("userInfo",  userInfo);
		//model.addAttribute("cafeReviewInfoList", cafeInfo.getReviewInfos());
		model.addAttribute("contents", "cafeReviewList :: cafeReviewList_contents");

		return "reviewLayout";
    }
	//사용자목록(GET)
	@GetMapping("/reviewUserList")
	public String getReviewUserList(Model model) {
		model.addAttribute("userList", userInfoRepository.findAll());
			
		return "reviewUserList";
	}
	@GetMapping("/reviewList/{user_id}")
    public String reviewList(@PathVariable Integer user_id, Model model) {
		UserInfo userInfo = userInfoRepository.findById(user_id).get();
		model.addAttribute("localDate", LocalDate.now());
		model.addAttribute("userInfo",  userInfo);

		model.addAttribute("user_id",  user_id);
		model.addAttribute("reviewInfoList", userInfo.getReviewInfos());
		model.addAttribute("contents", "reviewList :: reviewList_contents");

		return "reviewLayout";
    }
	@GetMapping("/reviewUpdate/{user_id}/{review_id}/{cafeId}")
    public String reviewUpdate(@PathVariable Integer user_id, 
    		@PathVariable Integer review_id , @PathVariable Integer cafeId, Model model) {
		UserInfo userInfo = userInfoRepository.findById(user_id).get();
		model.addAttribute("user_id",  user_id);
		ReviewInfo reviewInfo = reviewInfoRepository.findById(review_id).get();
		model.addAttribute("reviewInfo",  reviewInfo);
		model.addAttribute("reviewInfoAllList", reviewInfoRepository.findAll());
		model.addAttribute("contents", "reviewUpdate :: reviewUpdate_contents");
		return "reviewLayout";
    }
	@PostMapping(params = "update", value = "/reviewUpdate/{user_id}/{review_id}/{cafeId}")
    public String reviewUpdate(@PathVariable Integer user_id,
    		@PathVariable Integer review_id,@PathVariable Integer cafeId,
    		@Validated ReviewInfo reviewInfo, 
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			UserInfo userInfo = userInfoRepository.findById(user_id).get();

			
			model.addAttribute("reviewInfo",  reviewInfo);
			model.addAttribute("userInfo",  userInfo);

			model.addAttribute("user_id",  user_id);
			model.addAttribute("reviewInfoList", userInfo.getReviewInfos());
			model.addAttribute("contents", "reviewUpdate :: reviewUpdate_contents");
			return "reviewLayout";
		}
		reviewInfoRepository.findById(reviewInfo.getReview_id()).get();

		UserInfo userInfo = userInfoRepository.findById(user_id).get();
		LocalDateTime  date = LocalDateTime.now();
		reviewInfo.setCreatedate(date);
		CafeInfo cafeInfo= cafeInfoRepository.findById(cafeId).get();
		reviewInfo.setCafeInfo(cafeInfo);
		reviewInfo.setUserInfo(userInfo);
		reviewInfoRepository.save(reviewInfo);

		return "redirect:/cafeReviewList/{cafeId}";
    }
	@PostMapping(params = "delete", value = "/reviewUpdate/{user_id}/{review_id}/{cafeId}")
    public String reviewDelete(@PathVariable Integer user_id,
    		@PathVariable Integer review_id,@PathVariable Integer cafeId,
    		@Validated ReviewInfo reviewInfo, 
			BindingResult bindingResult,
			Model model) {
		reviewInfoRepository.deleteById(reviewInfo.getReview_id());

		return "redirect:/cafeReviewList/{cafeId}";
    }
	@GetMapping("/reviewSave/{user_id}/{cafeId}")
    public String reviewSave(@PathVariable Integer user_id, 
    		@PathVariable Integer cafeId,
    		Model model) {
		UserInfo userInfo = userInfoRepository.findById(user_id).get();

		ReviewInfo reviewInfo = new ReviewInfo();
		model.addAttribute("reviewInfo",  reviewInfo);
		model.addAttribute("contents", "reviewSave :: reviewSave_contents");
		model.addAttribute("reviewInfoAllList", reviewInfoRepository.findAll());

		return "reviewLayout";
    }

	@PostMapping(params = "save", value = "/reviewSave/{user_id}/{cafeId}")
    public String reviewSave(@PathVariable Integer user_id, 
    		@PathVariable Integer cafeId,
    		@Validated ReviewInfo reviewInfo, 
			BindingResult bindingResult,
			Model model) {
		if (bindingResult.hasErrors()) {
			UserInfo userInfo = userInfoRepository.findById(user_id).get();
			
			model.addAttribute("reviewInfo",  reviewInfo);
			model.addAttribute("userInfo",  userInfo);
			model.addAttribute("contents", "reviewSave :: reviewSave_contents");
			model.addAttribute("user_id",  user_id);
			model.addAttribute("reviewInfoList", userInfo.getReviewInfos());
			
			return "reviewLayout";
		}

		UserInfo userInfo = userInfoRepository.findById(user_id).get();
		userInfo.getReviewInfos().add(reviewInfo);
		LocalDateTime  date = LocalDateTime.now();
		reviewInfo.setCreatedate(date);
		reviewInfo.setUserInfo(userInfo);

		CafeInfo cafeInfo = new CafeInfo(); 
		cafeInfo = cafeInfoRepository.findById(cafeId).get();
		reviewInfo.setCafeInfo(cafeInfo);

		reviewInfoRepository.save(reviewInfo);
		userInfoRepository.save(userInfo);
		return "redirect:/cafeReviewList/{cafeId}";
    }

	@GetMapping("/saved/{user_id}")
    public String saved(@PathVariable Integer user_id, 
    		@PathVariable Integer review_id, 
    		Model model) {

		UserInfo userInfo = userInfoRepository.findById(user_id).get();
		ReviewInfo reviewInfo = reviewInfoRepository.findById(review_id).get();
		model.addAttribute("userInfo",  userInfo);
		model.addAttribute("reviewInfo",  reviewInfo);
		model.addAttribute("user_id",  user_id);
		model.addAttribute("reviewInfoList", userInfo.getReviewInfos());
		return "saved";
    }
}
