package com.faceontalk.domain.feed;

import java.util.Date;

public class FeedVO {
	private Integer feed_no;
	private Integer user_no_fk;
	private String user_id_fk;
	private String profile_pic;	
	private String content;
	private Date regdate;
	private Date moddate;
	private Integer like_count;
	private Integer reply_count;
	private String file_name;
	
	//getter,setter,toString	
	public Integer getFeed_no() {
		return feed_no;
	}
	
	public String getProfile_pic() {
		return profile_pic;
	}
	public void setProfile_pic(String profile_pic) {
		this.profile_pic = profile_pic;
	}
	public void setFeed_no(Integer feed_no) {
		this.feed_no = feed_no;
	}
	public Integer getUser_no_fk() {
		return user_no_fk;
	}
	public void setUser_no_fk(Integer user_no_fk) {
		this.user_no_fk = user_no_fk;
	}
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
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

	public Integer getReply_count() {
		return reply_count;
	}

	public void setReply_count(Integer reply_count) {
		this.reply_count = reply_count;
	}

	@Override
	public String toString() {
		return "FeedVO [feed_no=" + feed_no + ", user_no_fk=" + user_no_fk + ", user_id_fk=" + user_id_fk
				+ ", profile_pic=" + profile_pic + ", content=" + content + ", regdate=" + regdate + ", moddate="
				+ moddate + ", like_count=" + like_count + ", reply_count=" + reply_count + ", file_name=" + file_name
				+ "]";
	}
	
	
	
	
		
}
