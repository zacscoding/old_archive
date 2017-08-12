package calendar.awt;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {
	public static void main(String[] args) {
		 ShowCalendar calFrm = new ShowCalendar();
		 calFrm.start();

//		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("schedule.ser"))) {
//			while (true) {
//				Object obj = in.readObject();
//				if (obj instanceof Schedule) {
//					Schedule inst = (Schedule) obj;					
//					System.out.println(inst.getDate());
//					for (String str : inst.getList())
//						System.out.println(str);
//				}
//			}
//		} catch (EOFException e){
//			return;
//		}catch (IOException e) {
//			e.printStackTrace();
//		}catch(ClassNotFoundException e){
//			e.printStackTrace();
//		}

	}
}
