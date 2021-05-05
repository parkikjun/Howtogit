package com.oraclejava.im_lending_app.dto;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="user_info")
public class UserInfo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, 
		generator = "userInfoGenerator")
	@SequenceGenerator(name = "userInfoGenerator", 
		sequenceName = "user_info_seq", allocationSize = 1)
	private int user_id;
	
	@Length(min = 5, message = "아이디는 5자 이상 입력하세요")
	@NotEmpty(message = "아이디를 입력하세요")
	private String username;
	
	@Length(min = 5, message = "패스워드는 5자 이상 입력하세요")
	@NotEmpty(message = "패스워드를 입력하세요")
	private String password;
	
	@Transient
	private String password2;
	
	//get, set...
	@Email(message = "이메일 형식이 올바르지 않습니다.")
	@NotEmpty(message = "이메일을 입력하세요")
	private String mail_address;
	
	private boolean enabled;
	
	@OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
	private Set<UserRole> userRoles;
	@OneToMany(mappedBy = "userInfo", cascade = CascadeType.ALL)
	private List<ReviewInfo> reviewInfos;
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword2() {
		return password2;
	}
	public void setPassword2(String password2) {
		this.password2 = password2;
	}
	public String getMail_address() {
		return mail_address;
	}
	public void setMail_address(String mail_address) {
		this.mail_address = mail_address;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public Set<UserRole> getUserRoles() {
		return userRoles;
	}
	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
	public List<ReviewInfo> getReviewInfos() {
		return reviewInfos;
	}
	public void setReviewInfos(List<ReviewInfo> reviewInfos) {
		this.reviewInfos = reviewInfos;
	}

	
}
