package com.codesling.image.batch.jdbc.beans;

import java.io.Serializable;

public class SkuVO implements Serializable {
	private static final long serialVersionUID = -4815389741784084758L;
	
	private String lineNumber; 
	private String fscode;
	private String name; 
	private ImageBean fullImage; 
	private ImageBean thumbnail; 
	private String category;
	private String type;
	
	
	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}
	public String getFscode() {
		return fscode;
	}
	public void setFscode(String fscode) {
		this.fscode = fscode;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ImageBean getFullImage() {
		return fullImage;
	}
	public void setFullImage(String fullImage) {
		this.fullImage = new ImageBean(fullImage);
	}
	public ImageBean getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = new ImageBean(thumbnail);
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
