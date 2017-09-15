package dto;

import java.util.Date;

public class MessageVO {
	private static final long serialVersionUID = 1L;
	private int message_id;
	private String group_id;
	private int sender;
	private String content;
	private Date sendDate;	
	
	//insert »ı¼ºÀÚ
	public MessageVO(String group_id,int sender,String content,Date sendDate) {
		this.group_id = group_id;
		this.sender = sender;
		this.content = content;
		this.sendDate = sendDate;
	}
	public int getMessage_id() {
		return message_id;
	}
	public String getGroup_id() {
		return group_id;
	}
	public int getSender() {
		return sender;
	}
	public String getContent() {
		return content;
	}
	public Date getSendDate() {
		return sendDate;
	}
}
