package com.oraclejava.im_lending_app.dao;


import org.springframework.data.repository.CrudRepository;

import com.oraclejava.im_lending_app.dto.RatingInfo;

public interface RatingInfoRepository 
	extends CrudRepository<RatingInfo, Integer> { 

}
