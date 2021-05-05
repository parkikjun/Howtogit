package com.TextMining.web.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Login {

	

	@GetMapping("/login2")
	public String index() {

		return "login2";
	}

	@PostMapping("/CheckLogin")
	public String checkLogin() {

		return "ok";
	}

	@GetMapping("/join")
	public String Join() {

		return "join";
	}

	@PostMapping("/joinMember")
	public String JoinMember() {
		return "login";
	}
}
