package com.macrosoft.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
			.userDetailsService(userDetailsService)
			.passwordEncoder(passwordEncoder());
	}
	
	/*

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select USERNAME, PASSWORD, 1 as enabled from USER_AND_ROLE where USERNAME=?")
				.authoritiesByUsernameQuery("select USERNAME, ROLE from USER_AND_ROLE where USERNAME=?");
	}*/

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
		.antMatchers("/").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		.antMatchers("/add").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/modify").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/update").access("hasRole('ROLE_ADMIN')")
		.antMatchers("/delete").access("hasRole('ROLE_ADMIN')").and()
				.formLogin().loginProcessingUrl("/login").loginPage("/login")
				//.defaultSuccessUrl("/")
				.successHandler(successHandler())
				.failureUrl("/login?error=true")
				.usernameParameter("email").passwordParameter("password").and()
				.logout()
				.logoutUrl("/logout") 
				.logoutSuccessUrl("/login")
	            .and().csrf().disable();
				
		
        http.csrf().disable();
        http.headers().frameOptions().disable();
	}
	
	@Bean
	public LoginSuccessHandler successHandler() {
	    return new LoginSuccessHandler();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web
	       .ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
}