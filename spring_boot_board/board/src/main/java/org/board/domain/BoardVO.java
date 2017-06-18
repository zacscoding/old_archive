package org.board.domain;

import java.util.Arrays;
import java.util.Date;

public class BoardVO {
	
	/*=================================
	 * Member Fields
	 *================================= */
	
	/** 	board id	*/	
	private int boardNo;
	
	/**		title 		*/
	private String title;
	
	/**		content		*/
	private String content;
	
	/**		user sequence	*/	
	private int userNo;
	
	/**		category id		*/
	private int cateNo;
	
	/**		reply count		*/
	private int replyCount;
	
	/**		like count		*/
	private int likeCount;
	
	/**		view count		*/
	private int viewCount;
	
	/**		regist date		*/
	private Date regDate;
	
	/**		modify date		*/
	private Date modDate;
	
	/**		attachments	*/
	private String[] files;
	
	/**		images in textarea	*/
	private String[] images;
	
	/**		tags		*/	
	private String[] tags;
	
	
	

	/*=================================
	 * Setters , Getters, toString
	 *================================= */
	
	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getCateNo() {
		return cateNo;
	}

	public void setCateNo(int cateNo) {
		this.cateNo = cateNo;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public int getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(int likeCount) {
		this.likeCount = likeCount;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public Date getModDate() {
		return modDate;
	}

	public void setModDate(Date modDate) {
		this.modDate = modDate;
	}

	public String[] getFiles() {
		return files;
	}

	public void setFiles(String[] files) {
		this.files = files;
	}

	public String[] getImages() {
		return images;
	}

	public void setImages(String[] images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", content=" + content + ", userNo=" + userNo
				+ ", cateNo=" + cateNo + ", replyCount=" + replyCount + ", likeCount=" + likeCount + ", viewCount="
				+ viewCount + ", regDate=" + regDate + ", modDate=" + modDate + ", files=" + Arrays.toString(files)
				+ ", images=" + Arrays.toString(images) + ", tags=" + Arrays.toString(tags) + "]";
	}


		
}
