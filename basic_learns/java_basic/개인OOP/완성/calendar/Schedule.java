package calendar;
import java.io.Serializable;
import java.util.List;

public class Schedule implements Serializable{
	private String date;
	private List<String> list;
	
	public Schedule(String date, List<String> list){
		this.date = date;
		this.list = list;
	}
	
	public String getDate(){
		return date;
	}
	public List<String> getList(){
		return list;
	}
}
