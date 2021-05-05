package com.oraclejava.im_lending_app;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/").permitAll()  //메인 페이지를 막고 싶을 때는 주석처리함
			.antMatchers("/login").permitAll()
			.antMatchers("/registration").permitAll()
//			.antMatchers("/admin/**").permitAll()
			.antMatchers("/admin/**")
				.hasAnyAuthority("role_admin").anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login").loginProcessingUrl("/loginProcess")
			.failureUrl("/login?error=true").defaultSuccessUrl("/")
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
			.logout()
			.and()
			.exceptionHandling().accessDeniedPage("/403");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
			.antMatchers("/resources/**", "/static/**",
						"/css/**", "/js/**", "/images/**", "/owlcarousel/**","/sendEmail/**",
						"/sendEmailer/**","/join/**","/script/**","/CheckLogin/**","/joinmember/**","/image/**");
	}
	
}




