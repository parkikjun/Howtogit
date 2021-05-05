package com.oraclejava.im_lending_app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oraclejava.im_lending_app.dao.UserInfoRepository;
import com.oraclejava.im_lending_app.dto.UserInfo;
import com.oraclejava.im_lending_app.dto.UserRole;

@Service
@Transactional(readOnly = true)
public class LoginService implements UserDetailsService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) 
			throws UsernameNotFoundException {
		
		UserInfo userInfo = userInfoRepository.findByUsername(username);
		if (userInfo == null) {
			throw new UsernameNotFoundException("그런 아이디는 없는데요^^;");
		}
		
		List<GrantedAuthority> list = new ArrayList<>();
		Set<UserRole> userRoles = userInfo.getUserRoles();
		for (UserRole role: userRoles) {
			list.add(new SimpleGrantedAuthority(role.getRole()));
		}
		//list.add(new SimpleGrantedAuthority("admin"));
		
		return new org.springframework.security.core.userdetails.User(userInfo.getUsername(), userInfo.getPassword(), list);
	}

}
