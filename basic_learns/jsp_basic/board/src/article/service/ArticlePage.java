package article.service;

import java.util.List;

import article.model.Article;

/*
 * 게시글 데이터와 페이징 관련 정보를 담을 클래스
 */
public class ArticlePage {
	//instance variables
	private int total; //전체 게시글 수 보관
	private int currentPage; //사용자가 요청한 페이지 번호 보관
	private List<Article> content; //화면에 출력할 게시글 목록 보관
	private int totalPages; //전체 페이지 개수를 보관
	private int startPage; //화면 하단에 보여줄 페이지 이동 링크의 시작 (5개 보여줌)
	private int endPage; //화면 하단에 보여줄 페이지 이동 링크의 끝
	
	//constructor
	public ArticlePage(int total,int currentPage,int size,List<Article> content){
		this.total=total;
		this.currentPage=currentPage;
		this.content=content;
		if(total==0){ //게시글 개수가 0개 
			totalPages=0;
			startPage=0;
			endPage=0;			
		}else{
			//전체 게시글과 보여줄 페이지(size)에 따른 전체 페이지 수 구하기
			totalPages=total/size;
			if(total%size>0){ //전체 게시글 개수를 size로 나눈 나머지가 0보다 크면 페이지 수 1 증가
				totalPages++; //e.g) total 34, size 10 -> (34/10) ==3페이지 , (34%10)==4개의 게시글 => 1페이지 추가 필요 -> 4(3+1)페이지 필요 
			}
			
			//화면 하단에 보여줄 페이지 이동 링크의 시작 페이지 번호 구하기
			//[1,2,3,4,5] 처럼 5개 출력
			int modVal=currentPage%5; 	//e.g)currentPage==3 -> modVal==3 // currentPage=5 -> modVal == 0 
			startPage=currentPage/5*5+1;//startPage==1 					 //startPage=6
			if(modVal==0)				
				startPage-=5;			//변동X							//startPage=1
			endPage=startPage+4;		//endPage == 5					//endPage == 5
			if(endPage > totalPages)	//총 34페이지 인데, 현재 30페이지 일경우 endPage==35 -> endPage==34로 바꿈
				endPage=totalPages;			
		}		
	}
	
	public int getTotal(){
		return total;
	}
	
	public boolean hasNoArticles() {
		return total==0;
	}
	
	public boolean hasArticles(){
		return total>0;
	}
	
	public int getCurrentPage(){
		return currentPage;
	}
	
	public int getTotalPages(){
		return totalPages;
	}
	
	public List<Article> getContent(){
		return content;
	}
	
	public int getStartPage(){
		return startPage;
	}
	
	public int getEndPage(){
		return endPage;
	}
}













