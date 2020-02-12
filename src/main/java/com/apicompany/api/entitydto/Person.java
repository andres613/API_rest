package com.apicompany.api.entitydto;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

/**
 * Se indica que esta clase representa a una tabla de base de datos con el mismo nombre.
 * Para cambiar el nombre de la tabla se puede usar la anotación "@Table"
 * @author debian
 */

@Entity
public class Person implements Serializable {
	/**
	 * Es buena práctica hacer las entidades serializables.
	 * Algunos proveedores de persistencia (Hibernate, EclipseLink, etc.)
	 * pueden presentar excepciones si en algunos casos particulares la entidad no es serializable.
	 */
	private static final long serialVersionUID = 4894729030347835498L;
	/**
	 * La anotación "@Id" indica que este atributo será la clave primaria y 
	 * "@GeneratedValue" indica la forma en que se generarán los valores de la clave primaria.
	 * En este caso se usará el valor por defecto que hace que se use el valor que genere la base de datos.
	 * El comportamiento se puede cambiar por ejemplo, a una secuencia de Oracle, usando el parámetro «strategy»
	 */
	@Id
	// @GeneratedValue solo se activa en caso de usar H2 como BBDD simulada en RAM
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	@NotEmpty(message = "Por favor, ingrese un nombre")
	@Size(min = 3, max = 10, message = "Tamaño de nombre incorrecto")
	String name;
	@NotEmpty(message = "Por favor, ingrese un correo electrónico")
	@Email(message = "El formato del e-mail es incorrecto")
	String email;
	@Positive(message = "La edad debe ser mayor a cero (0)")
	int age;
	
	public Person() {}
	
	public Person (Long id, String name, String email, int age) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
