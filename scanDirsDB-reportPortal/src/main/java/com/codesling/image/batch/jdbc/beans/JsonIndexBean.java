package com.codesling.image.batch.jdbc.beans;

import java.io.Serializable;

public class JsonIndexBean implements Serializable{
	private static final long serialVersionUID = 8077058178190248135L;
	
	private String jsonName;
	private String label;

	
	public JsonIndexBean(String jsonName, String label) {
		super();
		this.jsonName = jsonName;
		this.label = label;
	}
	
	
	public String getJsonName() {
		return jsonName;
	}
	public void setJsonName(String jsonName) {
		this.jsonName = jsonName;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}



}
