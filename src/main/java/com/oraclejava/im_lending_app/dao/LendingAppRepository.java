package com.oraclejava.im_lending_app.dao;

import org.springframework.data.repository.CrudRepository;

import com.oraclejava.im_lending_app.dto.CafeInfo;
import com.oraclejava.im_lending_app.dto.LendingApp;

public interface LendingAppRepository 
	extends CrudRepository<LendingApp, Integer>{

	CafeInfo save(CafeInfo cafeInfo);
	

}
