package com.oraclejava.im_lending_app.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oraclejava.im_lending_app.dto.CafeInfo;

public interface CafeInfoRepository extends 
	PagingAndSortingRepository<CafeInfo, Integer>{
	

	
	//Java8의 디폴트 메소드
	default Page<CafeInfo> findTo6Item(PageRequest pageRequest) {
		return findAll(PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(),pageRequest.getSort()));
	}
	
	

	
}
