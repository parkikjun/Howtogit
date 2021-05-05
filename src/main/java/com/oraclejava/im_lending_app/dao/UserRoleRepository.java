package com.oraclejava.im_lending_app.dao;

import org.springframework.data.repository.CrudRepository;

import com.oraclejava.im_lending_app.dto.UserRole;

public interface UserRoleRepository 
	extends CrudRepository<UserRole, Integer>{

}
