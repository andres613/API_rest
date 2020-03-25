package com.apicompany.api.response;

import java.sql.Timestamp;

import com.apicompany.api.dto.PersonDTO;
import com.fasterxml.jackson.databind.JsonNode;

public class Response {
	//Atributes
	private Timestamp timestamp;
	private JsonNode data;
	private int responseCode;
	private String status;
	private PersonDTO persondto;
	
	//Getters
	public Timestamp getTimestamp() {
		return timestamp;	
	}
	public JsonNode getData() {
		return data;
	}
	public int getResponseCode() {
		return responseCode;	
	}
	public String getStatus() {
		return status;
	}
	public PersonDTO getPersonDTO() {
		return persondto;
	}
	
	//Setters
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}	
	public void setData(JsonNode data) {
		this.data = data;	
	}
	public void setRespondeCode(int responseCode) {
		this.responseCode = responseCode;	
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setStatus(PersonDTO persondto) {
		this.persondto = persondto;
	}
	//Empty Contructor
	public Response() {}
	//Contructor with Args
	public Response(Timestamp timestamp, JsonNode data, int responseCode, String status, PersonDTO persondto) {
		this.timestamp = timestamp;
		this.data = data;
		this.responseCode = responseCode;
		this.status = status;
		this.persondto = persondto;
	}

}