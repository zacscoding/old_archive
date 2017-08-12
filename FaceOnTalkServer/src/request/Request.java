package request;

import java.io.Serializable;
import java.util.Hashtable;

public class Request implements Serializable { 
	private static final long serialVersionUID = 1L;
	private String type; //요청 및 응답 type을 정해주는 멤버 필드(e.g 가입 : "join")
	private Hashtable<String,Object> parameterTable; //서버와 클라이언트가 통신하는데 필요한 인스턴스들(MemberVO,FeedPage 등등)을 담는 해시테이블	
	
	public Request(String type) {
		this.type = type;		
		parameterTable = new Hashtable<>();		
	}	
	//setter
	public void setParameter(String key,String value) {				
		setAttribute(key,value);
	}	
	public void setAttribute(String key,Object value) {		
		parameterTable.put(key, value);
	}	
	//getter
	public String getType() {
		return type;
	}
	public String getParameter(String key) {		
		Object obj = parameterTable.get(key); //일단 Object로 		
		//1)
		if(obj instanceof String) //String으로 타입 변환 가능하면
			return (String)obj; //String으로 형변환 해서 반환
		else
			return null; //아니면 null 반환
	}	
	public Object getAttribute(String key) {		
		return parameterTable.get(key);
	}	
}
