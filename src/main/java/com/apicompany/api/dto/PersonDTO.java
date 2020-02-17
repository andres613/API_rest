package com.apicompany.api.dto;

public class PersonDTO {
	String person_id;
	String name;
	String email;
	int age;

	public PersonDTO(String person_id, String name, String email, int age) {
		super();
		this.person_id = person_id;
		this.name = name;
		this.email = email;
		this.age = age;
	}

	public String getPerson_id() {
		return person_id;
	}

	public void setPerson_id(String person_id) {
		this.person_id = person_id;
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
