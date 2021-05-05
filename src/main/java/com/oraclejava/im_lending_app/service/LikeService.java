package com.oraclejava.im_lending_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraclejava.im_lending_app.controller.CafeForm;
import com.oraclejava.im_lending_app.controller.LendingAppForm;
import com.oraclejava.im_lending_app.dao.CafeInfoRepository;
import com.oraclejava.im_lending_app.dao.LendingAppRepository;
import com.oraclejava.im_lending_app.dao.LendingBookRepository;
import com.oraclejava.im_lending_app.dto.CafeInfo;
import com.oraclejava.im_lending_app.dto.LendingApp;
import com.oraclejava.im_lending_app.dto.LendingBook;

@Service
@Transactional(readOnly = true)
public class LikeService {

	@Autowired
	private LendingAppRepository lendingAppRepository;
	
	@Autowired
	private CafeInfoRepository cafeInfoRepository;
	
	@Transactional
	public void apply(CafeForm cafeForm) {
		CafeInfo cafeInfo = new CafeInfo();
		cafeInfo.setCafeId(
				cafeForm.getCafe_id());

		cafeInfo = cafeInfoRepository.save(cafeInfo);
		
	
	}
}












