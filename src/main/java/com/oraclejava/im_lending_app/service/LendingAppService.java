package com.oraclejava.im_lending_app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraclejava.im_lending_app.controller.LendingAppForm;
import com.oraclejava.im_lending_app.dao.LendingAppRepository;
import com.oraclejava.im_lending_app.dao.LendingBookRepository;
import com.oraclejava.im_lending_app.dto.LendingApp;
import com.oraclejava.im_lending_app.dto.LendingBook;

@Service
@Transactional(readOnly = true)
public class LendingAppService {

	@Autowired
	private LendingAppRepository lendingAppRepository;
	
	@Autowired
	private LendingBookRepository lendingBookRepository;
	
	@Transactional
	public void apply(LendingAppForm lendingAppForm) {
		LendingApp lendingApp = new LendingApp();
		lendingApp.setStatus("3");  //신청중
		lendingApp.setLending_user_id(
				lendingAppForm.getLending_user_id());

		lendingApp = lendingAppRepository.save(lendingApp);
		
		for (LendingBook lendingBook : lendingAppForm.getLendingBookList()) {
			LendingBook foundLendingBook = 
					lendingBookRepository.findById(lendingBook.getLending_book_id())
						.get();
			foundLendingBook.setLending_app_flg(lendingBook.getLending_app_flg());
			foundLendingBook.setLending_app_reason(lendingBook.getLending_app_reason());
			foundLendingBook.setLending_state("재고없음");
			foundLendingBook.setLending_app_id(lendingApp.getLending_app_id());
			lendingBookRepository.save(foundLendingBook);
		}
	}
}












