package com.oraclejava.im_lending_app.dto;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name = "cafe_info")

public class CafeInfo {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator="CafeInfoGenerator")
	@SequenceGenerator(name="CafeInfoGenerator", sequenceName = "cafe_seq", allocationSize = 1)
	@Column(name="cafe_id")
	private int cafeId;
	
	private String cafe_name;
	private String cafe_image;
	private String cafe_contents;
	

	public int getCafeId() {
		return cafeId;
	}
	public void setCafeId(int cafeId) {
		this.cafeId = cafeId;
	}
	public String getCafe_name() {
		return cafe_name;
	}
	public void setCafe_name(String cafe_name) {
		this.cafe_name = cafe_name;
	}
	public String getCafe_image() {
		return cafe_image;
	}
	public void setCafe_image(String file) {
		this.cafe_image = file;
	}
	public String getCafe_contents() {
		return cafe_contents;
	}
	public void setCafe_contents(String cafe_contents) {
		this.cafe_contents = cafe_contents;
	}
	public CafeInfo(int cafeId, String cafe_name, String cafe_image, String cafe_contents) {
		super();
		this.cafeId = cafeId;
		this.cafe_name = cafe_name;
		this.cafe_image = cafe_image;
		this.cafe_contents = cafe_contents;
	}
	
	public CafeInfo() {
		// TODO Auto-generated constructor stub
	}

	
	
}
