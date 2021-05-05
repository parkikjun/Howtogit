package com.oraclejava.im_lending_app.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oraclejava.im_lending_app.dto.CafeInfo;
import com.oraclejava.im_lending_app.dto.ReviewInfo;


public interface ReviewInfoRepository 
	extends PagingAndSortingRepository<ReviewInfo, Integer>{
	Page<ReviewInfo> findAllByCafeInfo(CafeInfo cafeInfo, Pageable pageable);

}
