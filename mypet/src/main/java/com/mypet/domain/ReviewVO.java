package com.mypet.domain;

import java.util.Date;

public class ReviewVO {
	private Integer review_no;
	private String review_title;
	private String review_writer;
	private String review_content;
	private Integer product_no_fk;
	private String review_image;
	private Date regdate;
	
	
	public Integer getReview_no() {
		return review_no;
	}
	public void setReview_no(Integer review_no) {
		this.review_no = review_no;
	}
	public String getReview_title() {
		return review_title;
	}
	public void setReview_title(String review_title) {
		this.review_title = review_title;
	}
	public String getReview_writer() {
		return review_writer;
	}
	public void setReview_writer(String review_writer) {
		this.review_writer = review_writer;
	}
	public String getReview_content() {
		return review_content;
	}
	public void setReview_content(String review_content) {
		this.review_content = review_content;
	}
	public Integer getProduct_no_fk() {
		return product_no_fk;
	}
	public void setProduct_no_fk(Integer product_no_fk) {
		this.product_no_fk = product_no_fk;
	}
	public String getReview_image() {
		return review_image;
	}
	public void setReview_image(String review_image) {
		this.review_image = review_image;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		return "ReviewVO [review_no=" + review_no + ", review_title=" + review_title + ", review_writer="
				+ review_writer + ", review_content=" + review_content + ", product_no_fk=" + product_no_fk
				+ ", review_image=" + review_image + ", regdate=" + regdate + "]";
	}
	
	
	
	
	
}
