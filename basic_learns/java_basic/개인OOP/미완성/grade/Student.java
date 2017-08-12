package grade;

public class Student{
	private String name;
	private int[] score;
	private int rank;
	private int total;	
	
	public Student(String name,int[] score){
		this.name=name;
		score = new int[Subject.SIZE];
		
		for(int i=0;i<score.length;i++){
			this.score[i] = score[i];
			total += score[i];
		}			
		
		rank = 1;
	}	
	
	//Setter
	public boolean setScore(int idx,int score){
		if(score<0||score>100)
			return false;
		total+=score-this.score[idx];		
		return true;
	}	
	public void updateTot(){
		total = 0;
		for(int i=0;i<score.length;i++){
			total+=score[i];
		}
	}	
	public void setRank(int rank){
		this.rank = rank;
	}
	public void initRank(){
		rank=1;
	}
	
	//Getter
	public String getName() {
		return name;
	}
	public int getScore(int idx) {
		return score[idx];
	}		
	public int getTotal(){
		return total;
	}	
	public int getRank(){
		return rank;
	}
	
	public String showInfo(){
		StringBuffer sb = new StringBuffer(name);
		for(int i=0;i<score.length;i++){
			sb.append("\t"+score[i]);
		}
		sb.append("\t"+total+"\t"+rank+"\n");
		return  sb.toString();
	}	
	
	@Override
	public int hashCode(){
		return name.hashCode();
	}	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Student){
			Student comp=(Student)obj;
			if(name.equals(comp.getName()))
				return true;
		}
		return false;
	}
	
}
