package com.mypet.domain;

import java.util.List;

public class AnimalVO {
	
	private Integer animal_no;
	private String animal_name;
	private List<CategoryVO> cateList;
	
	public Integer getAnimal_no() {
		return animal_no;
	}
	public void setAnimal_no(Integer animal_no) {
		this.animal_no = animal_no;
	}
	public String getAnimal_name() {
		return animal_name;
	}
	public void setAnimal_name(String animal_name) {
		this.animal_name = animal_name;
	}
	public List<CategoryVO> getCateList() {
		return cateList;
	}
	public void setCateList(List<CategoryVO> cateList) {
		this.cateList = cateList;
	}
	@Override
	public String toString() {
		return "AnimalVO [animal_no=" + animal_no + ", animal_name=" + animal_name + ", cateList=" + cateList + "]";
	}
	
}
