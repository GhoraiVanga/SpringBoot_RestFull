package com.ghorai.api.ui.model.response;

public class UserDetailsModelResponse {

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
	  public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	  private Long id;
	  private String userId;
	  private String firstName;
	  private String lastName;
	  private String email;
	  private String password;
	
	
}
