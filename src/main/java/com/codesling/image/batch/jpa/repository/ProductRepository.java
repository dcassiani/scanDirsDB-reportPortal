package com.codesling.image.batch.jpa.repository;

import java.math.BigInteger;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.codesling.image.batch.jpa.data.ProductVO;

public interface ProductRepository extends PagingAndSortingRepository<ProductVO, BigInteger> {

//	public Page<ProductVO> findAll(Pageable pageable);
	
//    List<ProductVO> findByProfile(String profile);
//    
//    List<ProductVO> findByLineNumber(String lineNumber);
}