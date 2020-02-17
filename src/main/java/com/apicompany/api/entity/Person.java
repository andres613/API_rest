package com.apicompany.api.entity;

//import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * Se indica que esta clase representa a una tabla de base de datos con el mismo
 * nombre. Para cambiar el nombre de la tabla se puede usar la anotación
 * "@Table"
 * 
 * @author debian
 */

@Entity
@Table(name="person")
public class Person {
	/**
	 * Es buena práctica hacer las entidades serializables. Algunos proveedores de
	 * persistencia (Hibernate, EclipseLink, etc.) pueden presentar excepciones si
	 * en algunos casos particulares la entidad no es serializable.
	 */
	//private static final long serialVersionUID = 4894729030347835498L;
	/**
	 * La anotación "@Id" indica que este atributo será la clave primaria y
	 * "@GeneratedValue" indica la forma en que se generarán los valores de la clave
	 * primaria. En este caso se usará el valor por defecto que hace que se use el
	 * valor que genere la base de datos. El comportamiento se puede cambiar por
	 * ejemplo, a una secuencia de Oracle, usando el parámetro «strategy»
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue solo se activa en caso de usar H2 como BBDD simulada en RAM
	@Column(name = "id")
	Long id;

	@NotEmpty(message = "Por favor, ingrese un número de identificación")
	@Size(min = 6, max = 10, message = "Número de identificación no válido")
	@Column(name = "person_id")
	String person_id;

	@NotEmpty(message = "Por favor, ingrese un nombre")
	@Size(min = 3, max = 10, message = "Tamaño de nombre incorrecto")
	@Column(name = "name")
	String name;
	
	@NotEmpty(message = "Por favor, ingrese un correo electrónico")
	@Email(message = "El formato del e-mail es incorrecto")
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
	message = "El email ingresado no tiene un formato válido")
	@Column(name = "email")
	String email;

	@Positive(message = "La edad debe ser mayor a cero (0)")
	@Column(name = "age")
	int age;

	@CreationTimestamp
	@Column(name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	
	public Person() {
	}

	public Person(Long id, String person_id, String name, String email, int age) {
		this.id = id;
		this.person_id = person_id;
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

	public String getPersonId() {
		return person_id;
	}
	public void setPersonId(String person_id) {
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

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setupdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

}
