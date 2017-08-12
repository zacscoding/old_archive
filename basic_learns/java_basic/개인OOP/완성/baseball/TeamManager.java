package baseball;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TeamManager {	
	private static TeamManager inst = null;	
	public static TeamManager getInstance(){
		if(inst == null)
			inst = new TeamManager();
		return inst;
	}
	
	private Hashtable<String,Team> hTable;
	private File file;
	
	private TeamManager(){
		hTable = new Hashtable<>();
		file = new File("data.dat");
		readFromFile();
	}
		
	//입력
	public void addPlayer(String teamName,String name,String salaryVal,String position,Map<String,Boolean>errors){
		Team team = hTable.get(teamName);
		if(team == null){
			errors.put("notExistTeam",Boolean.TRUE);
			return;
		}
		//이름
		if(team.existPlayer(name)){
			errors.put("duplicateName",Boolean.TRUE);
			return;
		}		
		//연봉
		int salary = 0;
		try{
			salary = Integer.parseInt(salaryVal);
		}catch(NumberFormatException e){
			errors.put("invalidSalary",Boolean.TRUE);
			return;
		}		
		Set<Player> set = team.getPlayersSet();
		set.add(new Player(teamName,name,salary,position));		
	}
	//삭제
	public void removePlayer(String teamName,String name,Map<String,Boolean> errors){
		Team team = hTable.get(teamName);
		if(team == null){
			errors.put("notExistTeam",Boolean.TRUE);
			return;
		}			
		Set<Player> set = team.getPlayersSet();
		Iterator<Player> itr = set.iterator();
		while(itr.hasNext()){
			Player p = itr.next();
			if(name.equals(p.getName())){
				itr.remove();
				return;
			}
		}
		errors.put("notExistName",Boolean.TRUE);				
	}
	//수정
	public void modifyPlayer(String teamName,String name,String salaryVal,String position,Map<String,Boolean> errors){
		Team team = hTable.get(teamName);
		if(team == null){
			errors.put("notExistTeam",Boolean.TRUE);
			return;		
		}
		//연봉
		int salary = 0;
		try{
			salary = Integer.parseInt(salaryVal);
		}catch(NumberFormatException e){
			errors.put("invalidSalary",Boolean.TRUE);
			return;
		}		
		
		//이름 존재 유무
		Set<Player> set = team.getPlayersSet();
		Iterator<Player> itr = set.iterator();
		while(itr.hasNext()){
			Player p = itr.next();
			if(name.equals(p.getName())){
				p.setSalary(salary);
				p.setPosition(position);
				return;
			}
		}
		errors.put("notExistName",Boolean.TRUE);					
	}
	
	//정보 출력
	public Player[] getPlayersArray(String teamName){
		Team team = hTable.get(teamName);
		if(team == null)
			return null;
		Set<Player> set = team.getPlayersSet();
		Player[] player = new Player[set.size()];
		set.toArray(player);
		return player;
	}
	
	//파일 저장
	public void saveToFile(){
		BufferedOutputStream buffOut = null;
		ObjectOutputStream objOut = null;
		try{
			buffOut = new BufferedOutputStream(new FileOutputStream(file));
			objOut = new ObjectOutputStream(buffOut);
			
//			Iterator<Map.Entry<String,Team>> entryIterator = hTable.entrySet().iterator();
//			while(entryIterator.hasNext()){
//				Team team =entryIterator.next().getValue();
//				objOut.writeObject(team);
//			}
			
			Set<Map.Entry<String,Team>> entrySet = hTable.entrySet();
			for(Entry<String,Team> entry : entrySet){				
				objOut.writeObject(entry.getValue());
			}					
			objOut.writeObject(null);
			objOut.flush();
		}catch(IOException e){
			System.out.println("IOException");
			e.printStackTrace();
		}finally{
			if(objOut!=null)
				try{objOut.close();}catch(IOException e){}
		}		
	}
	
	//파일 읽기
	private void readFromFile(){
		if(!file.exists()){
			for(int i=0;i<Team.defaultTeam.length;i++){
				String name = Team.defaultTeam[i];
				hTable.put(name,new Team(name));
			}
			return;
		}		
		
		BufferedInputStream buffIn = null;
		ObjectInputStream objIn = null;
		try{
			buffIn = new BufferedInputStream(new FileInputStream(file));
			objIn = new ObjectInputStream(buffIn);
			while(true){
				Team team = (Team)objIn.readObject();
				if(team == null) break;
				hTable.put(team.getName(), team);
			}					
		}catch(IOException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();			
		}finally{
			if(objIn!=null)
				try{objIn.close();}catch(IOException e){}
		}
		
	}
}
