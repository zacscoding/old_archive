package com.faceontalk.domain;


/**
 * 
 * 검색 정보도 함께 보관하는 Entity
 * 추후에 키워드 타입이 다양해지면, 확장성을 위해 따로 Criteria 상속
 *  
 */
public class SearchCriteria extends Criteria {
	private String keyword;
	
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString() {		
		return super.toString() + "SearchCriteria [keyword=" + keyword + "]";
	}
	
	
	
}
