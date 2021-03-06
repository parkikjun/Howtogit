package com.oraclejava.im_lending_app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="review_info")	//review_info 대신 ReviewInfo를 쓰겠다는 의미.
public class ReviewInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reviewInfoGenerator")
	@SequenceGenerator(name = "reviewInfoGenerator", 
			sequenceName = "review_info_seq", allocationSize = 1)
	private int review_id;
	
	//private int user_id;
	@Length(min = 5, message = "5자 이상 입력하세요")
	@NotEmpty
	private String contents;
	
	private float star;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserInfo userInfo;
	
	@ManyToOne
	@JoinColumn(name = "cafeId")
	private CafeInfo cafeInfo;

    private LocalDateTime  createdate	;
	
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}

	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public float getStar() {
		return star;
	}
	public void setStar(float star) {
		this.star = star;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public CafeInfo getCafeInfo() {
		return cafeInfo;
	}
	public void setCafeInfo(CafeInfo cafeInfo) {
		this.cafeInfo = cafeInfo;
	}
	public LocalDateTime getCreatedate() {
		return createdate;
	}
	public void setCreatedate(LocalDateTime createdate) {
		this.createdate = createdate;
	}


}
