package calendar;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ScheduleHandler {
	public static ScheduleHandler manager = null;
	public static ScheduleHandler getInstance(){
		if(manager == null)
			manager = new ScheduleHandler();
		return manager;
	}
	
	private Hashtable<String,Schedule> hashTable;	
	private final File dataFile = new File("schedule.dat");
	
	private ScheduleHandler(){
		hashTable = new Hashtable<>();		
		readFromFile();		
	}
	
	public Schedule getSchedule(String date){
		return hashTable.get(date);
	}
	
	public void add(String date,Schedule inst){
		hashTable.put(date, inst);
	}
	
	public void edit(String date,String original,String changed){
		Schedule schedule = hashTable.get(date);
		List<String> scheduleList = schedule.getList();
		for(String work : scheduleList){
			if(work.equals(original))
				work = changed;
		}		
	}
	
	public void delete(String date,String rSchedule){
		Schedule schedule = hashTable.get(date);
		List<String> scheduleList = schedule.getList();
		scheduleList.remove(rSchedule);	
	}
	
	private void readFromFile(){
		if(!dataFile.exists())
			return;		
		
		BufferedInputStream buffIn = null;
		ObjectInputStream objIn = null;
		try{
			buffIn = new BufferedInputStream(new FileInputStream(dataFile));
			objIn = new ObjectInputStream(buffIn);
			while(true){
				Schedule inst =(Schedule)objIn.readObject();
				if(inst == null)
					break;
				hashTable.put(inst.getDate(),inst);
			}
		}catch(EOFException e){
			return;			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(buffIn!=null) try{buffIn.close();}catch(IOException e){}
			if(objIn!=null) try{objIn.close();}catch(IOException e){}
		}		
	}
	
	public void saveFile(){
		BufferedOutputStream buffOut = null;
		ObjectOutputStream objOut = null;
		try{
			buffOut = new BufferedOutputStream(new FileOutputStream(dataFile));
			objOut = new ObjectOutputStream(buffOut);
						
			Iterator<String> itr =hashTable.keySet().iterator();
			
			while(itr.hasNext()){
				Schedule inst = hashTable.get(itr.next());
				if(!isListEmpty(inst.getList()))
					objOut.writeObject(inst);
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(buffOut!=null) try{buffOut.close();}catch(IOException e){}
			if(objOut!=null) try{objOut.close();}catch(IOException e){}
		}
	}	
	
	private boolean isListEmpty(List<String> list){
		for(int i=0;i<list.size();i++){
			String str = list.get(i);
			if(!str.trim().isEmpty())
				return false;				
		}
		return true;		
	}		
}
