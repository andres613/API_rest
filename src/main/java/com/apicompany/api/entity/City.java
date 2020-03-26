package com.apicompany.api.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="city")
public class City implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
<<<<<<< HEAD
	@Column(name = "name", nullable = false, unique = true)
=======
	@Column(name = "name")
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
	private String name;
	
	/**
	 * Se tiene una relación uno a muchos (@OneToMany). Esto significa que un registro de la tabla “city” tendrá asociados uno o múltiples
	 * registros de la tabla “person”. Pero los registros de la tabla person tendrán sólo un registro de la tabla “city” asociado;
	 * traduciendo, una ciudad tiene múltiples personas pero una persona sólo puede pertenecer a una ciudad.
	 * La anotación mappedBy=”city”  significa que en la clase Person habrá un atributo llamado city que representará la clave foránea,
	 * en este caso, la ciudad de la persona.
	 */
	@OneToMany(mappedBy="city", cascade=CascadeType.MERGE, fetch=FetchType.LAZY)
	private List<Person> people = new ArrayList<>();
	
	public City() {	}
	
	public City (int id, String name) {
		this.id = id;
		this.name = name;
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
	
}