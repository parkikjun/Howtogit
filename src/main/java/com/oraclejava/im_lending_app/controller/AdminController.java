package com.oraclejava.im_lending_app.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.oraclejava.im_lending_app.dao.LendingBookRepository;
import com.oraclejava.im_lending_app.dao.UserInfoRepository;
import com.oraclejava.im_lending_app.dto.LendingBook;
import com.oraclejava.im_lending_app.dto.UserInfo;
import com.oraclejava.im_lending_app.service.BookListService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Autowired
	private LendingBookRepository lendingBookRepository;
	
	@Autowired
	private BookListService bookListService;
	
	//라디오버튼용 필드
	private Map<String, String> radioEnable;
	
	//라디오버튼 초기화 메소드
	private Map<String, String> initRadioEnable() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("사용", "true");
		map.put("미사용", "false");
		return map;
	}
	
	//라디오버튼용 필드(신청여부)
	private Map<String, String> radioAppFlg;
	
	//라디오버튼 초기화 메소드
	private Map<String, String> initRadioAppFlg() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("신청안함", "0");
		map.put("신청됨", "1");
		return map;
	}
	
	//라디오버튼용 필드(승인상황)
	private Map<String, String> radioAppStat;
	
	//라디오버튼 초기화 메소드
	private Map<String, String> initRadioAppStat() {
		Map<String, String> map = new LinkedHashMap<>();
		map.put("승인", "1");
		map.put("거부", "2");
		return map;
	}
	
	//사용자목록(GET)
	@GetMapping("/userList")
	public String getUserList(Model model) {
		model.addAttribute("userList", userInfoRepository.findAll());
		
		model.addAttribute("contents", 
				"admin/userList :: userList_contents");
		
		return "admin/adminLayout";
	}
	
	//사용자수정(GET)
	@GetMapping("/userUpdate/{user_id}")
	public String getUser(@PathVariable Integer user_id, Model model) {
		UserInfo userInfo = userInfoRepository.findById(user_id).get();
		userInfo.setPassword(userInfo.getPassword().substring(6));
		model.addAttribute("userInfo", userInfo);
		
		//라디오버튼 초기화메소드 호출
		radioEnable = initRadioEnable();
		
		//라디오버튼용 Map을 Model에 등록
		model.addAttribute("radioEnable", radioEnable);
		
		model.addAttribute("contents", 
				"admin/userUpdate :: userUpdate_contents");
		
		return "admin/adminLayout";
	}
	
	//사용자수정(POST)
	@PostMapping(params = "update", value = "/userUpdate")
	public String userUpdate(@Validated UserInfo userInfo, 
			BindingResult bindingResult,
			Model model) {
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("contents", 
					"admin/userUpdate :: userUpdate_contents");
			
			return "admin/adminLayout";
		}
		userInfo.setPassword("{noop}" + userInfo.getPassword());
		userInfoRepository.save(userInfo);
		
		return "redirect:/admin/userList";
		
	}
	
	//사용자삭제(POST)
	@PostMapping(params = "delete", value = "/userUpdate")
	public String userDelete(UserInfo userInfo) {
		userInfoRepository.deleteById(userInfo.getUser_id());
		return "redirect:/admin/userList";
	}
	
	//도서목록(GET)
	@GetMapping("/bookList")
	public String bookList(Model model) {
		model.addAttribute("contents", "admin/bookList :: bookList_contents");
		
		model.addAttribute("bookList", lendingBookRepository.findAll());
		
		return "admin/adminLayout";
	}
	
	//도서수정(GET)
	@GetMapping("/bookUpdate/{book_id}")
	public String getBook(@PathVariable Integer book_id, Model model) {
		LendingBook lendingBook = lendingBookRepository.findById(book_id).get();
		
		radioAppFlg = initRadioAppFlg();
		radioAppStat = initRadioAppStat();
		
		model.addAttribute("contents", "admin/bookUpdate :: bookUpdate_contents");
		
		model.addAttribute("lendingBook", lendingBook);
		model.addAttribute("radioAppFlg", radioAppFlg);
		model.addAttribute("radioAppStat", radioAppStat);
		
		return "admin/adminLayout";
		
	}
	
	//도서수정(POST)
	@PostMapping(params = "update", value="/bookUpdate")
	public String bookUpdate(@Validated LendingBook lendingBook, BindingResult bindingResult,
				Model model) {
		if (bindingResult.hasErrors()) {
			
			radioAppFlg = initRadioAppFlg();
			radioAppStat = initRadioAppStat();
			model.addAttribute("radioAppFlg", radioAppFlg);
			model.addAttribute("radioAppStat", radioAppStat);
			
			model.addAttribute("contents", "admin/bookUpdate :: bookUpdate_contents");
			return "admin/adminLayout";
		}
		
		lendingBookRepository.save(lendingBook);
		return "redirect:/admin/bookList";
	}
	
	//도서삭제(POST)
	@PostMapping(params = "delete", value="/bookUpdate")
	public String bookDelete(LendingBook lendingBook) {
		lendingBookRepository.deleteById(lendingBook.getLending_book_id());
		return "redirect:/admin/bookList";
	}
	
	//도서목록 파일업로드(POST)
	@PostMapping("/bookList/fileupload")
	public String fileupload(@Validated UploadBookListForm uploadBookListForm
					, BindingResult bindingResult
					, Model model) {
		if (bindingResult.hasErrors()) {
			model.addAttribute("contents", "admin/bookList :: bookList_contents");
			return "admin/adminLayout";
		}
		
		bookListService.saveBookListCsvFile(uploadBookListForm);
		
		return "redirect:/admin/bookList";
	}
	
}









