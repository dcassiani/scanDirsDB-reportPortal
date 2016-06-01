package com.codesling.image.batch.jdbc.beans;

import java.io.Serializable;

public class ProductsVO implements Serializable{
	private static final long serialVersionUID = -6478651783483352518L;

	private String profile;
	private String name;
	private ImageBean fullImage;
	private ImageBean thumbnail;
	private String category;
	private String type;
	
	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
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
