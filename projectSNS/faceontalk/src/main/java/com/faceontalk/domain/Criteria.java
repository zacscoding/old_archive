package com.faceontalk.domain;

/**
 *  페이징 처리시 페이지 정보, 페이지당 보여줄 개수를 담는 Entity 
 *
 */

public class Criteria {
	private static final int DEFAULT_PAGE = 1;
	private static final int DEFAULT_PER_PAGE_NUM = 9;
	private int page;	
	private int perPageNum;
	
	public Criteria(){
		this.page = DEFAULT_PAGE;
		this.perPageNum = DEFAULT_PER_PAGE_NUM;
	}

	public void setPage(int page){
		//check invalid page number
		if(page <= 0){
			this.page = DEFAULT_PAGE;
			return;
		}		
		this.page = page;
	}

	public void setPerPageNum(int perPageNum) {		
		if(perPageNum <= 0 || perPageNum > 100){
			this.perPageNum = DEFAULT_PER_PAGE_NUM;
			return;
		}		
		this.perPageNum = perPageNum;
	}
	
	public int getPage() {
		return page;
	}	
	
	//method for MyBatis SQL Mapper - 
	public int getPageStart() {		
		return (this.page -1)* perPageNum;
	}
	
	//method for MyBatis SQL Mapper 
	public int getPerPageNum(){		
		return this.perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + "]";
	}
}
