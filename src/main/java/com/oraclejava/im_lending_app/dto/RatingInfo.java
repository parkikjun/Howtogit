package com.oraclejava.im_lending_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
@Entity
@Table(name="rating_info")	//review_info 대신 ReviewInfo를 쓰겠다는 의미.
public class RatingInfo {
    @Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ratingGenerator")
	@SequenceGenerator(name = "ratingGenerator", 
			sequenceName = "rating_info_seq", allocationSize = 1)
    private int id;


	private float stars;
	private int rate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public float getStars() {
		return stars;
	}
	public void setStars(float stars) {
		this.stars = stars;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	
	
}
