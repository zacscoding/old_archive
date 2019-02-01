package com.faceontalk.domain.feed;

import java.util.Date;

public class ReplyVO {
	private Integer rno;
	private Integer feed_no_fk;
	private String replytext;
	private String user_id_fk;
	private Date regdate;
	private Date moddate;	
	
	//getter,setter,toString
	public Integer getRno() {
		return rno;
	}
	public void setRno(Integer rno) {
		this.rno = rno;
	}
	public Integer getFeed_no_fk() {
		return feed_no_fk;
	}
	public void setFeed_no_fk(Integer feed_no_fk) {
		this.feed_no_fk = feed_no_fk;
	}
	public String getReplytext() {
		return replytext;
	}
	public void setReplytext(String replytext) {
		this.replytext = replytext;
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
	public String getUser_id_fk() {
		return user_id_fk;
	}
	public void setUser_id_fk(String user_id_fk) {
		this.user_id_fk = user_id_fk;
	}
	@Override
	public String toString() {
		return "ReplyVO [rno=" + rno + ", feed_no_fk=" + feed_no_fk + ", replytext=" + replytext + ", user_id_fk="
				+ user_id_fk + ", regdate=" + regdate + ", moddate=" + moddate + "]";
	}
	
	
}
