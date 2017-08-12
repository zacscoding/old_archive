package com.faceonshop.dto;

public class ProductVO {
	private Integer code;
	private String name;
	private Integer price;
	private String description;
	private String pictureUrl;
	
	//constructor
	public ProductVO(){}		
	public ProductVO(Integer code, String name, Integer price, String description, String pictureUrl) {		
		this.code = code;
		this.name = name;
		this.price = price;
		this.description = description;
		this.pictureUrl = pictureUrl;
	}


	//setter , getter
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPictureUrl() {
		return pictureUrl;
	}
	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
	
	
}
