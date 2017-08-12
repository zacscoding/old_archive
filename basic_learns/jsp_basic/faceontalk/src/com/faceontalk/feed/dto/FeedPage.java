package com.faceontalk.feed.dto;

import java.util.List;

public class FeedPage {
	public static final int pageSize = 5;	
	private int total;
	private int currentPage;
	private List<FeedVO> feedList;
	private int totalPages;
	private int startPage;
	private int endPage;
	
	public FeedPage(int total,int currentPage,int size,List<FeedVO> feedList) {
		this.total = total;
		this.currentPage = currentPage;
		this.feedList = feedList;		
		initVariables(size);		
	}	
	
	private void initVariables(int size) {
		if(total == 0) {
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		} else {
			totalPages = total / size;
			if(total%size!=0)
				totalPages++;
			
			startPage = currentPage/pageSize*pageSize+1;
			int modVal = currentPage % pageSize;
			if(modVal == 0)
				startPage -=pageSize;
			endPage = startPage+pageSize-1;
			if(endPage > totalPages)
				endPage = totalPages;		
		}
	}

	public static int getPagesize() {
		return pageSize;
	}

	public int getTotal() {
		return total;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public List<FeedVO> getFeedList() {
		return feedList;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}
}
