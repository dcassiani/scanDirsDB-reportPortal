package com.codesling.image.batch.jdbc.beans;

import java.io.Serializable;

/**
 * Load of base parameters for other queries.
 * 
 * Superclass for final product.
 * 
 * @author dcassiani
 *
 */
public class CampaignVO implements Serializable {
	private static final long serialVersionUID = -4518101059584908962L;
	

	private String cmpgnNumber;
	private String cmpgnYear;
	
	public String getCmpgnNumber() {
		return cmpgnNumber;
	}
	public void setCmpgnNumber(String cmpgnNumber) {
		this.cmpgnNumber = cmpgnNumber;
	}
	public String getCmpgnYear() {
		return cmpgnYear;
	}
	public void setCmpgnYear(String cmpgnYear) {
		this.cmpgnYear = cmpgnYear;
	}

	@Override
	public String toString() {
		return "Campaign [cmpgnNumber=" + cmpgnNumber + ", cmpgnYear="
				+ cmpgnYear + "]";
	}

}
