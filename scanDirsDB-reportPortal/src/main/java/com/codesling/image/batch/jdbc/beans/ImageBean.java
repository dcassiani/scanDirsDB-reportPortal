package com.codesling.image.batch.jdbc.beans;

import java.io.Serializable;

public class ImageBean implements Serializable{
	private static final long serialVersionUID = 4711574110796966535L;

	private String image;
	private boolean isReady;

	
	public ImageBean(String image) {
		super();
		this.image = image;
		this.isReady = false;
	}
	
	
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public boolean isReady() {
		return isReady;
	}
	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

}
