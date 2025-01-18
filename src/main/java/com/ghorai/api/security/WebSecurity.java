//package com.ghorai.api.security;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.User.UserBuilder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//
//@Configuration
//public class WebSecurity  {
//	
//	   
//	//Two beans - Authentication and authorization 
//	//authentication bean 
//	@Bean
//	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder)
//	{
//		//Create Users
//		//IN memory authentication
//		UserDetails user1 = User.withUsername("admin")
//				                .password(passwordEncoder.encode("admin123"))
//				                .roles("ADMIN","USER").build();
//		//return an objects of InmemoryUserDetailsManager
//		return new InMemoryUserDetailsManager(user1);
//	}
//	
//	@Bean
//	public PasswordEncoder encoder()
//	{
//		return new BCryptPasswordEncoder();
//	}
//	
//	@Bean
//	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
//		
//		
//		return http.authorizeHttpRequests((auth)->{
//			
//			auth.requestMatchers("/").permitAll();
//			auth.requestMatchers("/users/**").hasAnyRole("ADMIN");
//			auth.requestMatchers("/admin/**").hasAnyRole("ADMIN");
//		}).httpBasic().and().build();
//		
//	}
//
//}
