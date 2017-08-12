package tcpchatting;

public class Message {
	String type;
	String sender;
	String content;
	String[] recipients;
	
	public Message(String type,String sender,String content,String[] recipients){
		this.type = type;
		this.sender = sender;
		this.content = content;
		this.recipients = recipients;
	}
}
