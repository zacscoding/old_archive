package com.faceontalk.domain;


/**
 * 
 * 검색 정보도 함께 보관하는 Entity
 * 
 * 해시 태그 피드 검색 : searchType == 't'
 * follower 피드 리스트 : searchType == 'f'
 *  
 */
public class SearchCriteria extends Criteria {
	private String searchType;
	private String keyword;
	
	public String getSearchType() {
		return searchType;
	}
	
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	
	public String getKeyword() {
		return keyword;
	}
	
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	
	@Override
	public String toString() {
		return super.toString() + " SearchCriteria [searchType=" + searchType + ", keyword="
				+ keyword + "]";
	}
}
