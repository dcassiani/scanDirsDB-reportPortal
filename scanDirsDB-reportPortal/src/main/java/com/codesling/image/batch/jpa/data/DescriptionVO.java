package com.codesling.image.batch.jpa.data;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table (name="DESC", schema="SCH")
public class DescriptionVO implements Serializable {
	private static final long serialVersionUID = 8978655668443211640L;

	@Id
	@Column(name="ID")
	private BigInteger sysId;
	
	@Column(name="FULLIMAGE")
	private String fullimage;
	
	@Column(name="THUMBNAIL")
	private String thumbnail;
	
	@Column(name="NAME")
	private String name;
	

	public BigInteger getSysId() {
		return sysId;
	}

	public void setSysId(BigInteger sysId) {
		this.sysId = sysId;
	}

	public String getFullimage() {
		return fullimage;
	}

	public void setFullimage(String fullimage) {
		this.fullimage = fullimage;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "DescriptionVO [sysId=" + sysId + ", fullimage=" + fullimage
				+ ", thumbnail=" + thumbnail + ", name=" + name + "]";
	}
}
