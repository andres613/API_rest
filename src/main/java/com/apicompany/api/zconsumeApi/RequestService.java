package com.apicompany.api.zconsumeApi;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class RequestService {
	
	public void captureData () throws MalformedURLException, IOException {
		
		String url = "http://localhost:8080/api_rest/api/person/";
		//String url = "http://api.thecatapi.com/v1/images/search";
		String reply = 
				new Scanner(			// A new scanner is created
						new URL(url)	// the scanner takes a Stream 
                        				// which is obtained from a URL
						.openStream(),	// - openStream returns the stream
						"UTF-8")		// Now the scanner can parse the        
                						// stream character by character
                						// with UTF-8 encoding
				.useDelimiter("\\A")	// Now the scanner set as 
                						// delimiter the [Regexp for \A][1]
                						// \A stands for :start of a string!
				.next();				// Here it returns the first(next) 
        								// token that is before another
        								// start of string. 
        								// Which, I'm not sure 
        								// what it will be
		
/**
 * La clase ObjectMapper también puede ser usado para construir un árbol gerárquico de nodos a partir de datos JSON.
 * En el modelo de árbol de JSON, se puede acceder a un nodo especifico y leer este valor. En el Modelo de Arbol, cada nodo
 * es del tipo JsonNode que provee diferentes metodos para trabajar con llaves especificas.
 * 
 * This method is especially useful when you are interested in only a few keys values. In this scenario, it does not make sense to convert the complete JSON file into Java object. Below code snippet converts customer.json file into a Tree Model and then read the name and phone nodes values.

//create ObjectMapper instance
ObjectMapper objectMapper = new ObjectMapper();

//read customer.json file into tree model
JsonNode rootNode = objectMapper.readTree(new File("customer.json"));

//read name and phone nodes
System.out.println("Customer Name: " + rootNode.path("name").asText());
System.out.println("Customer Phone: " + rootNode.path("phone").asText());
System.out.println("Customer Age: " + rootNode.path("age").asInt());
System.out.println("Customer City: " + rootNode.path("address").path("city").asText());
System.out.println("Customer Project: " + rootNode.path("projects").get(0).asText());
In the code above, we used the readTree() method from ObjectMapper class to read our customer.json file in a JSON Tree Model. JsonNode class provides path() method that can be used to specify the node name we are interested in to read the value. Since path() returns another JsonNode object, it can be chained to get nested node (like city value in above code). asText() method returns the value of the node as a string.

Another key thing to note in the above code snippet is the use of get() method instead of path() to read the first project value. It works similar to path() method - returns the specific node as JsonNode. However, it returns a null value when the node is not present or is empty, instead of missing node object as returned by the path() method. The get() method is also used to access the unknown keys and array indexes.

 */
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		JsonNode rootNode = objectMapper.readTree(reply);
		
		System.out.println("Person: " + rootNode);
		System.out.println("Person Name: " + rootNode.get(0).path("id").asText());
				
	}

}
