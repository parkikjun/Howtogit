package com.oraclejava.im_lending_app.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "lending_app")
public class LendingApp {

	@Id
	@GeneratedValue(generator = "lendingAppGenerator", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "lendingAppGenerator", sequenceName = "lending_app_seq", allocationSize = 1)
	private int lending_app_id;
	private String status;
	private int lending_user_id;
	private int applicant_user_id;

	public int getLending_app_id() {
		return lending_app_id;
	}

	public void setLending_app_id(int lending_app_id) {
		this.lending_app_id = lending_app_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLending_user_id() {
		return lending_user_id;
	}

	public void setLending_user_id(int lending_user_id) {
		this.lending_user_id = lending_user_id;
	}

	public int getApplicant_user_id() {
		return applicant_user_id;
	}

	public void setApplicant_user_id(int applicant_user_id) {
		this.applicant_user_id = applicant_user_id;
	}

}
