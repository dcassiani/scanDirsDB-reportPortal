package com.codesling.image.batch.jdbc.beans;

import java.io.Serializable;
import java.util.List;

public class CampaignDTO extends CampaignVO implements Serializable {
	private static final long serialVersionUID = 8594327061853868824L;
	
	private List<ProductsVO> listProducts;
	private List<SkuVO> listSKU;
	private List<AtributoVO> listAtributos;
	private String fileName;

	public CampaignDTO(CampaignVO camp) {
		super();
		this.setCmpgnNumber(camp.getCmpgnNumber());
		this.setCmpgnYear(camp.getCmpgnYear());
		this.fileName = "camp-"+ camp.getCmpgnNumber() + "-year-" + camp.getCmpgnYear() + ".json";
	}

	public List<ProductsVO> getListProducts() {
		return listProducts;
	}

	public void setListProducts(List<ProductsVO> listProducts) {
		this.listProducts = listProducts;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public List<SkuVO> getListSKU() {
		return listSKU;
	}

	public void setListSKU(List<SkuVO> listSKU) {
		this.listSKU = listSKU;
	}

	public List<AtributoVO> getListAtributos() {
		return listAtributos;
	}

	public void setListAtributos(List<AtributoVO> listAtributos) {
		this.listAtributos = listAtributos;
	}
}
