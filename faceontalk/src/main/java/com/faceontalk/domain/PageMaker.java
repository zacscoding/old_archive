package com.faceontalk.domain;

import org.springframework.web.util.UriComponentsBuilder;


/**
 * 
 * 페이지번호( << 1 2 3 4 5 6 .. >>)를 계산하기 위한 클래스
 * 
 */
public class PageMaker {
	private int totalCount;
	private int startPage;
	private int endPage;
	private boolean prev;
	private boolean next;
	
	private int displayPageNum = 10;
	
	private Criteria cri;


	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	public void setTotalCount(int totalCount) {
		
		this.totalCount = totalCount;		
		calcData();
	}

	/*		calc start,end page		*/
	private void calcData() {		
		
		endPage = (int) (Math.ceil(cri.getPage() / (double)displayPageNum ) * displayPageNum);		
		startPage = (endPage - displayPageNum) + 1;		
		int tempEndPage = (int)(Math.ceil(totalCount / (double)cri.getPerPageNum()));		
		if(endPage > tempEndPage){
			endPage = tempEndPage;
		}		
		prev = startPage ==1 ? false : true;		
		next = endPage * cri.getPerPageNum() >= totalCount ? false : true;		
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}
	
	
	/*	make query string	*/
	public String makeQuery(int page) {		
		return	UriComponentsBuilder.newInstance()
				.queryParam("page", page)
				.queryParam("perPageNum", cri.getPerPageNum())
				.build()
				.toUriString();
	}
	
	/*	make search query string	*/
	public String makeSearch(int page) {		
		return UriComponentsBuilder.newInstance()
				.queryParam("page",page)
				.queryParam("perPageNum",cri.getPerPageNum())
				.queryParam("keyword",((SearchCriteria)cri).getKeyword())
				.build()
				.toUriString();
	}	
	
	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", startPage="
				+ startPage + ", endPage=" + endPage + ", prev=" + prev
				+ ", next=" + next + ", displayPageNum=" + displayPageNum
				+ ", cri=" + cri + "]";
	}
}
