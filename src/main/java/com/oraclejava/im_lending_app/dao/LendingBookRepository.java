package com.oraclejava.im_lending_app.dao;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.oraclejava.im_lending_app.dto.LendingBook;

public interface LendingBookRepository 
	extends PagingAndSortingRepository<LendingBook, Integer>{
	
	@Query("select b from LendingBook b order by b.lending_book_id desc")
	List<LendingBook> findAllOrderByBookIdDesc(Pageable pageable);
	
	//Java8의 디폴트 메소드
	default List<LendingBook> findTo12Books() {
		return findAllOrderByBookIdDesc(PageRequest.of(0, 12));
	}
	
	@Query("select b from LendingBook b order by b.book_name")
	List<LendingBook> findAllOrderByBookName();

}
