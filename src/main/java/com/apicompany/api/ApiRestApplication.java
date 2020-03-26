package com.apicompany.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiRestApplication.class, args);
	}

}

<<<<<<< HEAD
/* Ésta es la clase de inicia de la aplicación.
 * Luego, una vez iniciada la aplicación, la clase ClaseController queda atenta a cualquier evento y
 * es quien escucha las peticiones para cada uno de los verbos manejados. Ésta clase, emplea a ClaseDTO para capturar la informacion, filtrarla o validarla
 * y posteriormente, mapearlo a Clase. Person (por ejemplo) es un entity, es decir, la entidad base para mapear la tabla que irá en la base de datos.
 * La clase ClaseRepository, usa los entitys para guardarlos en base de datos y para enviarlos a ClaseService para transformarlos en DTO
 * y ser expuestos al usuario.
 * 
 * La clase CustomGlobalExceptionHandler administra errores y lanza los mensajes correspondientes a la excepcion ocurrida. */
=======
/**
 * Ésta es la clase de inicio de la aplicación.
 * Luego, una vez iniciada la aplicación, la clase ClaseController queda atenta a cualquier evento y
 * es quien escucha las peticiones en cada uno de los verbos manejados. Ésta clase, emplea a ClaseDTO para capturar la informacion, filtrarla o validarla
 * y posteriormente a ello, mapearlo a Clase. Person (por ejemplo) es un entity, es decir, la entidad base para mapear la tabla que ira en base de datos.
 * Laclase ClaseRepository, usa los entitys para guardarlos en base de datos y para enviarlos a ClaseService para transformarlos en DTO
 * y ser expuestos al usuario.
 * 
 * La clase CustomGlobalExceptionHandler administra errores y lanza los mensajes correspondientes a la excepcion ocurrida. 
 */
>>>>>>> 6cf6953b0de66567209c4d20605d700dc51bcec2
