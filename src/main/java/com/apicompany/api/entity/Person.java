package com.apicompany.api.entity;


import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
public class Person implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Es buena práctica hacer las entidades serializables. Algunos proveedores de
	 * persistencia (Hibernate, EclipseLink, etc.) pueden presentar excepciones si
	 * en algunos casos particulares la entidad no es serializable.
	 *
	 *
	 * La anotación "@Id" indica que este atributo será la clave primaria y
	 * "@GeneratedValue" indica la forma en que se generarán los valores de la clave
	 * primaria. En este caso se usará el valor por defecto que hace que se use el
	 * valor que genere la base de datos. El comportamiento se puede cambiar por
	 * ejemplo, a una secuencia de Oracle, usando el parámetro «strategy»
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // @GeneratedValue solo se activa en caso de usar H2 como BBDD simulada en RAM
	@Column(name = "id")
	String id;
	@Column(name = "document")
	String document;
	@Column(name = "name")
	String name;
	@Column(name = "email")
	String email;
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

	public Person(String id, String document, String name, String email, int age) {
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
