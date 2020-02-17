package com.apicompany.api.dto;

public class PersonDTO {
	String personid;
	String name;
	String email;
	int age;

	public PersonDTO(String personid, String name, String email, int age) {
		//super();
		this.personid = personid;
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
