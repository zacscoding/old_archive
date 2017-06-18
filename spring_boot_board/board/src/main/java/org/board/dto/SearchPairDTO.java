package org.board.dto;

/**
 * 검색 타입(searchType) 과
 * 키워드 (keyword) 를 담는 엔티티 클래스
 * @author 	:	Zaccoding
 * @date 	: 	2017. 4. 16.
 */
public class SearchPairDTO {
	
	/**
	 * 검색 타입
	 */
	private String searchType;
	
	/**
	 * 검색 키워드
	 */
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
		return "SearchPairDTO [searchType=" + searchType + ", keyword=" + keyword + "]";
	}
}
