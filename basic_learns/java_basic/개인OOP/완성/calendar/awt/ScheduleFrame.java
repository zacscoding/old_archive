package calendar.awt;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import calendar.Schedule;
import calendar.ScheduleHandler;

public class ScheduleFrame extends JFrame {
	private final int LIST_SIZE = 9;	
	private int listSize;	
	private List<String> list;
	private ScheduleHandler scheduleManager;
	
	//UI
	// private Button btnWeek;
	private Button btnNew;	
	private Label titleLabel;	
	private Font defualtFont = new Font("", Font.BOLD, 15);
	private Font topFont = new Font("", Font.BOLD, 18);
	private ListPanel[] listPanel;		
	
	
	public ScheduleFrame(String date) {
		super(date+"`s Schedule");
		titleLabel = new Label(date+"`s Schedule");
		scheduleManager = ScheduleHandler.getInstance();
		Schedule inst = scheduleManager.getSchedule(date);
		if(inst == null){
			list = new ArrayList<String>();
			inst = new Schedule(date,list);
			scheduleManager.add(date,inst);
		}else{
			list = inst.getList();
		}		
		listSize = list.size();
		init();
	}
	
	public void close(){		
		this.dispose();		
	}

	public void init() {
		///////////////////////////////////////////
		// 초기 설정
		this.setSize(600, 500);
		this.setLocation(600, 200);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				close();
			}
		});		
		this.setLayout(new GridLayout(10,1,5,5));		
		Panel topPanel = new Panel();
		topPanel.setLayout(new BorderLayout());		
		titleLabel.setFont(topFont);
		
		btnNew = new Button("NEW");
		btnNew.setFont(topFont);
		
		topPanel.add(titleLabel, BorderLayout.CENTER);
		topPanel.add(btnNew, BorderLayout.EAST);
		
		this.add(topPanel);		
		listPanel = new ListPanel[LIST_SIZE];
		for(int i=0;i<LIST_SIZE;i++){
			listPanel[i] = new ListPanel();
			this.add(listPanel[i].getListPanel());		
		}
		fillList();		
		btnNew.addActionListener(event -> newBtnHandler(event) );		
	}
	
	private void fillList(){
		int size = list.size();
		for(int i=0;i<size;i++){			
			listPanel[i].setText(list.get(i));			
		}
		for(int i=size; i<LIST_SIZE;i++){
			listPanel[i].setText("");		
		}
	}
	
	private void newBtnHandler(ActionEvent event){
		listPanel[listSize].btnEditSave.setEnabled(true);
		listPanel[listSize].editBtnHandler();
	}
	
	class ListPanel{
		Button btnEditSave;
		Button btnDel;
		TextField listLabel;
		String prevContent;
		boolean isNew;
		
		ListPanel(){
			btnEditSave = new Button("Edit");
			btnDel = new Button("Delete");
			listLabel = new TextField();
			init();			
		}
		void init(){
			btnEditSave.setFont(defualtFont);						
			btnDel.setFont(defualtFont);
			btnDel.setEnabled(false);			
			listLabel.setFont(defualtFont);
			listLabel.setEditable(false);
			
			btnEditSave.addActionListener(event -> editBtnHandler() );
			btnDel.addActionListener(event -> delBtnHandler());
			isNew = false;
		}
		void setText(String text){
			listLabel.setText(text);
			if(text.isEmpty()){ //스케줄이 없으면				
				btnDel.setEnabled(false);
				isNew = true;
			}
			else{ //스케줄이 있으면
				btnDel.setEnabled(true);				
			}
		}
		
		Panel getListPanel(){
			Panel panel = new Panel();
			panel.setLayout(new BorderLayout());		
			
			Panel eastPanel = new Panel();
			eastPanel.setLayout(new GridLayout(1,2,5,5));
			eastPanel.add(btnEditSave); eastPanel.add(btnDel);
			
			panel.add(listLabel, BorderLayout.CENTER);
			panel.add(eastPanel, BorderLayout.EAST);
			
			return panel;
		}
		
		private void editBtnHandler(){		
			String nowState = btnEditSave.getActionCommand();
			if(nowState.equals("Edit")){
				listLabel.setEditable(true);
				btnEditSave.setLabel("Save");
				prevContent = listLabel.getText();
				btnDel.setEnabled(false);
			}else if(nowState.equals("Save")){
				String newContent = listLabel.getText();
				if(isNew){
					if(newContent.isEmpty())
						return;
					
					list.add(listSize,newContent);
					listSize++;
				}else{
					if(!newContent.equals(prevContent)){
						for(int i=0;i<list.size();i++){
							if(prevContent.equals(list.get(i))){
								list.remove(i);
								list.add(i,newContent);
							}						
						}
					}									
				}	
				btnEditSave.setLabel("Edit");
				btnDel.setEnabled(true);
				listLabel.setEditable(false);	
			} 
		}		
		private void delBtnHandler(){
			String removeContent = listLabel.getText();			
			System.out.println(removeContent);
			for(int i=0;i<list.size();i++){
				if(removeContent.equals(list.get(i))){
					list.remove(i);
					listSize--;
				}					
			}			
			fillList();
			for(String str : list){
				System.out.println(str);
			}
		}	
	}
	

	public void start() {
		this.setVisible(true);
	}
	
//	public static void main(String[] args){				
//		ScheduleFrame frm = new ScheduleFrame("20161206");
//		frm.start();
//	}
}
