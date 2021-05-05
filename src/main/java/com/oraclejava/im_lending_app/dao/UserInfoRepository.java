package com.oraclejava.im_lending_app.dao;

import org.springframework.data.repository.CrudRepository;

import com.oraclejava.im_lending_app.dto.UserInfo;

public interface UserInfoRepository 
	extends CrudRepository<UserInfo, Integer>{
	UserInfo findByUsername(String username);
}
