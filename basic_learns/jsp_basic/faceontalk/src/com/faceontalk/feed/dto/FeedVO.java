package com.faceontalk.feed.dto;

import java.util.Date;

public class FeedVO {
	private int feedNumber; //게시글 번호
	private WriterVO writer; //작성자
	private String content; //내용
	private Date regDate; //등록 날자
	private Date modifiedDate; //수정 날자
	private int likeCount; //좋아요 수
	
	public FeedVO(){}
	//게시글 생성 관련 생성자
	public FeedVO(WriterVO writer,String content, Date regDate, Date modifiedDate, int likeCount){
		this.writer = writer;
		this.content = content;
		this.regDate = regDate;
		this.modifiedDate = modifiedDate;
		this.likeCount = likeCount;
	}
	
	//
	public FeedVO(int number,WriterVO writer,String content, Date regDate, Date modifiedDate, int likeCount){
		this(writer,content,regDate,modifiedDate,likeCount);
		this.feedNumber = number;		
	}			
	
	//게시글 수정 관련 생성자
	public FeedVO(int feedNumber,WriterVO writer,String content,Date modifiedDate){
		this.feedNumber = feedNumber;
		this.writer = writer;
		this.content = content;		
		this.modifiedDate = modifiedDate;		
	}
	
	//getter
	public Integer getFeedNumber() {
		return feedNumber;
	}
	public WriterVO getWriter() {
		return writer;
	}
	public String getContent() {
		return content;
	}
	public Date getRegDate() {
		return regDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public int getLikeCount() {
		return likeCount;
	}
}
