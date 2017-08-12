package grade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GradeHandler {
	// Singleton
	public static GradeHandler inst = null;

	public static GradeHandler getInstance() {
		if (inst == null)
			inst = new GradeHandler();
		return inst;
	}

	// variables
	private Hashtable<String, Student> hTable; // 스레드에 안전
	private boolean isUpdate = false;
	private Student[] stuArr;

	//constructor
	private GradeHandler() {
		hTable = new Hashtable<>();
	}

	//1.입력
	public boolean inputStuInfo(String name,int[] score) {
		if (hTable.containsKey(name))
			return false;
		hTable.put(name, new Student(name,score));
		isUpdate = true;
		return true;
	}
	
	//2.출력
	public List<String> getAllStuInfo() {
		if (hTable.isEmpty())
			return null;
		if (isUpdate)
			updateRank();
		List<String> list = new ArrayList<String>(hTable.size());
		Set<String> nameKey = hTable.keySet();
		Iterator<String> itr = nameKey.iterator();
		while (itr.hasNext()) {
			String info = hTable.get(itr.next()).showInfo();
			list.add(info);
		}
		return list;
	}	
	//3.삭제
	public boolean remove(String name) {
		Student stu = hTable.remove(name);
		if (stu == null) {
			return false;
		}
		isUpdate = true;
		return true;
	}
	
	// 4.수정
	public boolean modify(String name, int[]score) {
		Student stu = hTable.get(name);
		if (stu == null)
			return false;
				
		for(int i=0;i<score.length;i++){
			stu.setScore(i, score[i]);
		}		
		isUpdate = true;		
		return true;
	}
	
	//학생 배열 얻기
	public Student[] getStuArray() {
		if (stuArr==null || isUpdate) {
			stuArr = new Student[hTable.size()];
			Set<String> nameKey = hTable.keySet();
			Iterator<String> itr = nameKey.iterator();
			int idx = 0;
			while (itr.hasNext()) {
				stuArr[idx++] = hTable.get(itr.next());
			}
		}
		return stuArr;
	}
	
	private void updateRank() {
		Student[] arr = getStuArray();
		Arrays.sort(arr,new Comparator<Student>(){
			@Override
			public int compare(Student stu1, Student stu2) {
				return (stu1.getTotal()-stu2.getTotal());
			}
		});
		int rank = 1;
		int prevScore = arr[0].getTotal();
		arr[0].initRank();
		
		for(int i=1;i<arr.length;i++){
			if(prevScore!=arr[i].getTotal())
				rank++;
			arr[i].setRank(rank);
		}
	}
}










