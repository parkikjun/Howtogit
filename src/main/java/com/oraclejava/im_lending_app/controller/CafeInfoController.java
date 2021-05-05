package com.oraclejava.im_lending_app.controller;

import java.awt.print.Pageable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.oraclejava.im_lending_app.dao.CafeInfoRepository;
import com.oraclejava.im_lending_app.dto.CafeInfo;
import com.oraclejava.im_lending_app.dto.UserInfo;
import com.oraclejava.im_lending_app.service.FileUploadUtil;


@Controller
public class CafeInfoController {

	@Autowired
	private CafeInfoRepository cafeRepository;
	
	private static final int PAGE_SIZE = 8;

	@Autowired
	ResourceLoader resourceLoader;
	
	
	@RequestMapping(value={"/cafe","/cafe/{pageNumber}"},method=RequestMethod.GET)
	public ModelAndView CafeInfo(@PathVariable Optional<Integer> pageNumber) {
		ModelAndView mav = new ModelAndView();
		
		//mav.addObject("cafeList", cafeRepository.findTo6Item());
		
		mav.addObject("contents", "cafeinfo :: cafeboard");
		
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
	
	@RequestMapping(value="/cafe/cafeWrite",
			method=RequestMethod.GET)
	public String create(Model model) {
		return "cafeWrite";
	}
	
	@RequestMapping(value="/cafe/cafeView/{cafeId}",
			method=RequestMethod.GET)
	public String view(@PathVariable Integer cafeId, Model model) {
		CafeInfo cafeinfo = cafeRepository.findById(cafeId).get();
		model.addAttribute("cafe", cafeinfo);
		return "cafeView";
	}
	
	
	@RequestMapping(value="/writeAction",  method= RequestMethod.POST)
   public String saveImg(CafeInfo cafeinfo,
		   @RequestParam("image") MultipartFile multipartFile,
		   @RequestParam("cafe_name")String cafe_name, 
           @RequestParam("cafe_contents")String cafe_contents
		   ) throws IOException{
		
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		cafeinfo.setCafe_image(fileName);
		
		CafeInfo saved = cafeRepository.save(cafeinfo);
		
		String uploadDir = "static/image/" + saved.getCafeId();
		
		 FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		return "redirect:/cafe";
	}

	
	@GetMapping("/cafe/cafeUpdate/{cafeId}")
	public String update(@PathVariable Integer cafeId, Model model) {
		CafeInfo cafeinfo = cafeRepository.findById(cafeId).get();
		model.addAttribute("cafe", cafeinfo);
		return "cafeUpdate";
	}
	
	@PostMapping(params = "update", value="/cafe/cafeUpdate")
	public String update(@Validated CafeInfo cafeInfo, Model model,
			   @RequestParam("image") MultipartFile multipartFile
			   ) throws IOException{
			
			String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
			cafeInfo.setCafe_image(fileName);
			
			CafeInfo saved = cafeRepository.save(cafeInfo);
			
			String uploadDir = "static/image/" + saved.getCafeId();
			
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		
		
		CafeInfo cafeinfo = cafeRepository.findById(cafeInfo.getCafeId()).get();
		
		cafeinfo.setCafe_name(cafeInfo.getCafe_name());
		cafeinfo.setCafe_image(cafeInfo.getCafe_image());
		cafeinfo.setCafe_contents(cafeInfo.getCafe_contents());
		cafeRepository.save(cafeinfo);
		return "redirect:/cafe";
	}
	
	@PostMapping(params="delete", value="/cafe/cafeUpdate")
	public String delete(CafeInfo cafeInfo) {
		CafeInfo cafeinfo = cafeRepository.findById(cafeInfo.getCafeId()).get();
		cafeRepository.delete(cafeinfo);
		return "redirect:/cafe";
	}
	

	

    
}
