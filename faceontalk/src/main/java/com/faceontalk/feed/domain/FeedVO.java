package com.faceontalk.feed.domain;

import java.util.Date;

public class FeedVO {
	private Integer feed_no;
	private Integer user_id_fk;
	private String user_name_fk;
	private String content;
	private Date regdate;
	private Date moddate;
	private Integer like_count;
	private String file_name;
	
	public Integer getFeed_no() {
		return feed_no;
	}
	public void setFeed_no(Integer feed_no) {
		this.feed_no = feed_no;
	}
	
	public Integer getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(Integer user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	public String getUser_name_fk() {
		return user_name_fk;
	}
	public void setUser_name_fk(String user_name_fk) {
		this.user_name_fk = user_name_fk;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public Date getModdate() {
		return moddate;
	}
	public void setModdate(Date moddate) {
		this.moddate = moddate;
	}
	public Integer getLike_count() {
		return like_count;
	}
	public void setLike_count(Integer like_count) {
		this.like_count = like_count;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	
	@Override
	public String toString() {
		return "FeedVO [feed_no=" + feed_no + ", user_id_fk=" + user_id_fk + ", user_name_fk=" + user_name_fk
				+ ", content=" + content + ", regdate=" + regdate + ", moddate=" + moddate + ", like_count="
				+ like_count + ", file_name=" + file_name + "]";
	}
}
