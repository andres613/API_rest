package com.apicompany.api.zvalidator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * Comprueba que la suma de los caracteres del codigo este dentro del rango valido
 */
/**
 * Esta anotación simplemente sirve para que cuando generemos el Javadoc de nuestro proyecto se incluya la anotación en las clases, métodos o atributos en las que se usa, si no se incluye en la documentación no veremos que se está utilizando la anotación.
 */
@Documented
//@Constraint(validatedBy = PersonDataValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DataPerson {
	String message() default "{productoCodigoValido.mensajePorDefecto}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
