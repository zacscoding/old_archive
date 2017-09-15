package dto;

import java.io.Serializable;
import java.util.List;

import dto.FeedVO;

public class FeedPage implements Serializable{
	private static final long serialVersionUID = 1L;
	private static final int pageSize = 5;
	/*
	 * 예)
	 * 전체 게시글수 53, 페이지당 보여줄 피드 개수 5개 , 화면 하단에 보여줄 페이지 prev [1][2][3][4][5] next or prev [6][7][8][9][10] next
	 * total = 53 totalPages = 11개
	 * feedList는 요청페이지의 피드 부터 size개    
	 *  startPage = 1 , endPage = 5 or startPage = 6 , endPage=10  
	 * 
	 */
	private int total;		//전체 피드 수 
	private int currentPage; //사용자가 요청한 페이지 번호
	private List<FeedVO> feedList;	//화면에 보여줄 feed를 담은 list
	private int totalPages; //전체 페이지 개수 
	private int startPage; //화면 하단에 보여줄 페이지 이동 링크 시작 
	private int endPage;	//화면 하단에 보여줄 페이지 이동 링크 끝	
	
	public FeedPage(int total,int currentPage,int size,List<FeedVO> feedList) {
		this.total = total;
		this.currentPage = currentPage;
		this.feedList = feedList;
			
		if(total == 0){ //작성된 게시글이 없으면
			totalPages = 0;
			startPage = 0;
			endPage = 0;			
		}else{
			//전체 feed / 보여줄 개수 에 따른 전체 페이지 수 구하기
			//e.g) total 34 , size 10 -> (34/10) == 3 , (34%10)==4개 게시글 -> 1페이지 더 필요 
			totalPages = total / size;  
			if(total%size!=0)
				totalPages++;
			
			//화면 하단에 보여줄 페이지 이동 링크 prev [1][2][3][4][5] next || prev [6][7][8][9][10] next
			/*
			 * e.g1) currentPage = 3 , pageSize = 5			  
			 * -> startPage == 3/5*5+1 ==1
			 * -> modVal == 3%5 == 3
			 * -> endPage == 1+5-1 == 5
			 * +전체 페이지가 [1][2][3][4]인 경우 endPage == 4로 바꿔줌
			 * 
			 * e.g2) currentPage =5 , pageSize = 5  
			 * ->startPage == 5/5*5+1 == 6
			 * ->modVal == 5%5 == 0
			 * ->startPage == 6-5 == 1
			 * ->endPage == 1+5-1 == 5
			 */
			
			//[1] || [6] 과 같은 시작 페이지 구함
			startPage = currentPage/pageSize*pageSize+1;
			int modVal = currentPage % pageSize; //마지막 페이지 인지 검사			
			if(modVal == 0) //마지막 페이지 인 경우
				startPage-=pageSize;
			//[5] || [10]과 같은 마지막 페이지 구함
			endPage = startPage+pageSize-1;
			if(endPage > totalPages) //마지막페이지가 totalPages보다 큰 경우
				endPage = totalPages;			
		}		
	}
	
	public boolean hasFeed(){
		return total>0;
	}
	public int getTotal(){
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
