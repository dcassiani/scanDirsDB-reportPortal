package com.codesling.image.batch.jpa;

import java.util.Iterator;

import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.codesling.image.batch.jpa.data.ProductVO;
import com.codesling.image.batch.jpa.repository.ProductRepository;

public class ImgItemReader implements ItemReader<ProductVO> {

	@Autowired
	private ProductRepository dao;
	
	private Iterator <ProductVO> prodIterator;
	private int page;

	public ImgItemReader(){
		System.out.println("******** INIT: Reader");
		page = 0;
	}
	
	private void getNextPage(){
		System.out.println("********** begin: "+page+" ********** ");
		Page<ProductVO> prodPage = dao.findAll(new PageRequest(page, 50));
		prodIterator = prodPage.iterator();
		System.out.println("********** end ********** ");
	}
	
	@Override
	public ProductVO read() {
		if ((prodIterator==null) || (!prodIterator.hasNext())){
			page++;
			System.out.println("*********** Going for new page: " + page);
			getNextPage();
		}
		
		if (!prodIterator.hasNext()){
			System.out.println("*********** Exit on page: " + page);
			return null;
		}
		
//		System.out.println("**** Reader serve next Product to Processor");
		return prodIterator.next();
	}

}