package com.mypet.domain;

public class CategoryVO {
	private Integer category_no;
	private String cate_name;
	private Integer animal_no_fk;
	private String animal_name_fk;
	
	
	public Integer getCategory_no() {
		return category_no;
	}
	public void setCategory_no(Integer category_no) {
		this.category_no = category_no;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public Integer getAnimal_no_fk() {
		return animal_no_fk;
	}
	public void setAnimal_no_fk(Integer animal_no_fk) {
		this.animal_no_fk = animal_no_fk;
	}
	public String getAnimal_name_fk() {
		return animal_name_fk;
	}
	public void setAnimal_name_fk(String animal_name_fk) {
		this.animal_name_fk = animal_name_fk;
	}
	
	@Override
	public String toString() {
		return "CategoryVO [category_no=" + category_no + ", cate_name=" + cate_name + ", animal_no_fk=" + animal_no_fk
				+ ", animal_name_fk=" + animal_name_fk + "]";
	}
}
