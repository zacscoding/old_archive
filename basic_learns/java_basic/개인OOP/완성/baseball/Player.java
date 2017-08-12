package baseball;

import java.io.Serializable;

public class Player implements Serializable{
	//variables
	private String teamName;
	private String name;
	private int salary;
	private String position;
	
	//constructor
	public Player(String teamName,String name,int salary,String position){
		this.teamName = teamName;
		this.name = name;
		this.salary = salary;
		this.position = position;
	}
	
	//hashset비교용생성자
	public Player(String name){
		this.name = name;
	}
	
	//setter
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public void setPosition(String position) {
		this.position = position;
	}	
	//getter
	public int getSalary() {
		return salary;
	}
	public String getPosition() {
		return position;
	}	
	public String getName() {
		return name;
	}	
	public String getTeamName(){
		return teamName;
	}
	public String getPlayerInfo(){		
		return name+"\t"+salary+"\t"+position+"\n";
	}	
	
	//hash
	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Player) {
			Player comp = (Player)obj;
			return name.equals(comp.getName());			
		}
		return false;
	}
}
