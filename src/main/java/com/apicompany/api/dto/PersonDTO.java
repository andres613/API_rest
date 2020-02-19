package com.apicompany.api.dto;

//import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PersonDTO {
	
	
	String id;
	
	@NotEmpty(message = "Por favor, ingrese un número de identificación")
	@Size(min = 6, max = 10, message = "Número de identificación no válido")
	@Pattern(regexp = "^[0-9]*$", message = "Número de identificación no válido; sólo se admiten números")
	String document;
	
	@NotEmpty(message = "Por favor, ingrese un nombre")
	@Size(min = 3, max = 10, message = "Tamaño de nombre incorrecto")
	String name;
	
	@NotEmpty(message = "Por favor, ingrese un correo electrónico")
	//@Email(message = "El formato del e-mail es incorrecto")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message = "El email ingresado no tiene un formato válido")
	String email;
	
	/*@NotEmpty(message = "Por favor, ingrese la edad")*/
	@Positive(message = "La edad debe ser mayor a cero (0)")
	int age;

	public PersonDTO(String id, String document, String name, String email, int age) {
		this.id = id;
		this.document = document;
		this.name = name;
		this.email = email;
		this.age = age;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
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
