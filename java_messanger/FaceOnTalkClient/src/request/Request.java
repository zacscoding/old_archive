package request;

import java.io.Serializable;
import java.util.Hashtable;

public class Request implements Serializable { 
	private static final long serialVersionUID = 1L;
	private String type;
	private Hashtable<String,Object> parameterTable;	
	
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
		Object obj = parameterTable.get(key);
		if(obj instanceof String)
			return (String)obj;
		else
			return null;		
	}	
	public Object getAttribute(String key) {		
		return parameterTable.get(key);
	}	
}
