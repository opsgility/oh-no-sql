package com.opsgility.contosojobs.ContosoJobsApplication.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.opsgility.contosojobs.ContosoJobsApplication.service.AuthenticationService;

@Configuration
@EnableWebSecurity
public class ContosoSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    Environment env;
	@Autowired
	AuthenticationService authenticationService;
	@SuppressWarnings("deprecation")
	@Bean
	 public static NoOpPasswordEncoder passwordEncoder() {
	  return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	 }
	
	protected void configure(AuthenticationManagerBuilder auth, DataSource getDataSource) throws Exception {
		auth.jdbcAuthentication().dataSource(getDataSource);
	/*	auth.authenticationProvider(authenticationProvider());*/
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String[] resources = new String[]{
                "/", "/home/**","/pictureCheckCode","/include/**",
                "/css/**","/icons/**","/images/**","/js/**","/layer/**"
                ,"/member/registerUser/**","/member/registerCompany/**"
		};
		// log in
		http.authorizeRequests()
        .antMatchers(resources)
        .permitAll()
        .and()
        .authorizeRequests()
		.anyRequest().authenticated()
		.and()
		.formLogin()
		    .loginPage("/showLogin")
		    .loginProcessingUrl("/authenticateUser")
		    .defaultSuccessUrl("/",true)
		    .permitAll()
		    .and()
		    .logout().permitAll();
	}
	/*
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
	    DaoAuthenticationProvider authProvider
	      = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}
	 
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder(11);
	} */
	
    @Bean
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName(env.getProperty("spring.datasource.driverClassName"));
	    dataSource.setUrl(env.getProperty("spring.datasource.url"));
	    dataSource.setUsername(env.getProperty("spring.datasource.username"));
	    dataSource.setPassword(env.getProperty("spring.datasource.password"));
	    return dataSource;
	}

}
