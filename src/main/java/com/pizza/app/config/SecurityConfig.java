package com.pizza.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final DataSource dataSource;

	@Autowired(required = false)
	public SecurityConfig(@Nullable DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
					.authorizeRequests()
					.antMatchers("/resources/**","/registration").permitAll()
					//.antMatchers("/order/**").hasRole("ADMIN")
					.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/login")
					.loginProcessingUrl("/authentication")
					.permitAll()
				.and()
					.logout()
					.permitAll();
	}
}
