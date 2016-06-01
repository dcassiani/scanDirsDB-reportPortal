package com.codesling.image.batch.jpa.data;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table (name="CMPGN_CALNDR", schema="SCH")
public class CampaignEntityVO implements Serializable {
	private static final long serialVersionUID = -6613961395293521424L;

	@Id
	@Column (name="ID")
	private BigInteger sysId;
	
	@Column (name="CMPGN_NR")
	private Integer number;
	
	@Column (name="CMPGN_YR_NR")
	private Integer year;
	

	public BigInteger getSysId() {
		return sysId;
	}

	public void setSysId(BigInteger sysId) {
		this.sysId = sysId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	@Override
	public String toString() {
		return "CampaignVO [sysId=" + sysId + ", number=" + number + ", year="
				+ year + "]";
	}
}
