package com.picverse.model;

public class ContactModel {
	private int id;
	private String name;
	private Long phoneNumber;
	private String email;
	private String subject;
	private String message;

	public ContactModel() {
		super();
	}
	
	public ContactModel(String name, Long phoneNumber, String email, String subject, String message) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.subject = subject;
		this.message = message;
	}
	
	public ContactModel(int id, String name, Long phoneNumber, String email, String subject, String message) {
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.subject = subject;
		this.message = message;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Long getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(long i) {
		this.phoneNumber = i;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSubject() {
		return subject;
	}
	
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}	
}
