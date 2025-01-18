package com.ghorai.api.ui.entity;

import java.io.Serializable;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;


@Entity(name = "com_emp")
public class UserEntity implements Serializable {

	   @jakarta.persistence.Id
	   @GeneratedValue
	   private long id ;
	   public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	   @Column(nullable = false)
       private String userId ="Active";;
	   @Column(nullable = false)
	   private String firstName;
	   @Column(nullable = false)
	   private String lastName;
	   @Column(nullable = false)
	   private String email;
	   @Column(nullable = false)
	   private String password;
	   public String getEncriptedPassword() {
		return encriptedPassword;
	}
	public void setEncriptedPassword(String encriptedPassword) {
		this.encriptedPassword = encriptedPassword;
	}
	@Column(nullable = false)
	   private String encriptedPassword;
	 
	
	
	
	
	
}
