package com.mypet.domain;

public class ProductSearchCriteria extends SearchCriteria {
	private Integer cateType;
	private String conditionType;
	private String productType;
	
	public Integer getCateType() {
		return cateType;
	}

	public void setCateType(Integer cateType) {
		this.cateType = cateType;
	}

	public String getConditionType() {
		return conditionType;
	}

	public void setConditionType(String conditionType) {
		this.conditionType = conditionType;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	@Override
	public String toString() {
		return "ProductSearchCriteria [cateType=" + cateType + ", conditionType=" + conditionType + ", productType="
				+ productType + "]";
	}

	
}
