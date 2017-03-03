package com.mypet.persistence;

import java.util.List;

import com.mypet.domain.CarouselVO;

public interface ProductDAO {
	
	public List<CarouselVO> CarouselList() throws Exception;
	

}
