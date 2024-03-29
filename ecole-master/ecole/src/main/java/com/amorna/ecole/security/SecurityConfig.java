package com.amorna.ecole.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder=passwordEncoder();
		
		auth.jdbcAuthentication()
		    .dataSource(dataSource)
		    .usersByUsernameQuery("select username as principal ,password as credentials,active as enabled from users where username =?")
		    .authoritiesByUsernameQuery("select username as principal ,role as role from users_roles where username =?")
		    .passwordEncoder(passwordEncoder)
		    .rolePrefix("ROLE_");
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.httpBasic();
		http.formLogin().loginPage("/login");
		
		http.authorizeRequests().antMatchers("/save**/**","/delete**/**","/form**/**,/admin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/etudiants**/**").hasRole("USER");
		
		http.authorizeRequests().antMatchers("/user/**","/login","/webjars/**","/css/**","/home","/register","/progister").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		//http.csrf().disable();
		http.exceptionHandling().accessDeniedPage("/notAuthorized");
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
