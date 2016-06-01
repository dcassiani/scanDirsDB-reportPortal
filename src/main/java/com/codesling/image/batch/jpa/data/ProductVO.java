package com.codesling.image.batch.jpa.data;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.codesling.image.batch.util.CalendarUtil;

@Entity
@Table (name="ENTRY", schema="SCH")
public class ProductVO implements Serializable {
	private static final long serialVersionUID = -6613961395293521424L;

	@Id
	@Column(name="ID")
	private BigInteger sysId;
	
	@Column(name="PARTNUMBER")
	private String profile;
	
	@Column(name="URL")
	private String commerceUrl;
	
	@Column(name="MFPART")
	private String lineNumber;
	
	@Column(name="LASTUPDATE")
	private Calendar lastUpdate;
	
	@OneToOne
	@JoinTable(name="OPERTNL_CMPGN_CALNDR",
			joinColumns = {@JoinColumn(name="ID")},
			inverseJoinColumns = {@JoinColumn(name="ID")})
	private CampaignEntityVO campaign;
	
	@OneToOne
	@JoinTable(name="DESC",
			joinColumns = {@JoinColumn(name="ID")},
			inverseJoinColumns = {@JoinColumn(name="ID")})
	private DescriptionVO desc;

	
	
	public BigInteger getSysId() {
		return sysId;
	}

	public void setSysId(BigInteger sysId) {
		this.sysId = sysId;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getCommerceUrl() {
		return commerceUrl;
	}

	public void setCommerceUrl(String commerceUrl) {
		this.commerceUrl = commerceUrl;
	}

	public String getLineNumber() {
		return lineNumber;
	}

	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public Calendar getLastUpdate() {
		return lastUpdate;
	}

	public void setLastUpdate(Calendar lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

	public CampaignEntityVO getCampaign() {
		return campaign;
	}

	public void setCampaign(CampaignEntityVO campaign) {
		this.campaign = campaign;
	}

	public DescriptionVO getDesc() {
		return desc;
	}

	public void setDesc(DescriptionVO desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		String ret = "ProductVO [sysId=" + sysId + ", profile=" + profile
				+ ", commerceUrl=" + commerceUrl + ", lineNumber=" + lineNumber
				+ ", lastUpdate=" + CalendarUtil.getDateTimeLabel(lastUpdate) 
				+ ", campaign= ";
		if (campaign!=null){
			ret = ret.concat(campaign.toString());
		}
		ret = ret.concat(", desc= ");
		if (desc!=null){
			ret = ret.concat(desc.toString());
		}
		ret = ret.concat("]");
		return ret; 
	}
}
