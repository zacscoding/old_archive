package calendar.swing;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import calendar.Schedule;
import calendar.ScheduleHandler;

public class ScheduleFrame extends JFrame {
	private final int LIST_SIZE = 9;	
	private int listSize;	
	private List<String> list;
	private ScheduleHandler scheduleManager;
	
	//UI
	// private JButton btnWeek;
	private JButton btnNew;	
	private JLabel titleJLabel;	
	private Font defualtFont = new Font("", Font.BOLD, 15);
	private Font topFont = new Font("", Font.BOLD, 18);
	private ListJPanel[] listJPanel;			
	
	public ScheduleFrame(String date) {
		super(date+"`s Schedule");		
		titleJLabel = new JLabel(date+"`s Schedule");
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

	public void init() {
		///////////////////////////////////////////
		// 초기 설정
		this.setSize(400, 400);
		this.setLocation(600, 200);
		this.setResizable(false);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				scheduleManager.saveFile();
				dispose();
			}
		});		
		this.setLayout(new GridLayout(10,1,5,5));		
		JPanel topJPanel = new JPanel();
		topJPanel.setLayout(new BorderLayout());		
		titleJLabel.setFont(topFont);
		
		btnNew = new JButton("NEW");
		btnNew.setFont(topFont);
		
		topJPanel.add(titleJLabel, BorderLayout.CENTER);
		topJPanel.add(btnNew, BorderLayout.EAST);
		
		this.add(topJPanel);		
		listJPanel = new ListJPanel[LIST_SIZE];
		for(int i=0;i<LIST_SIZE;i++){
			listJPanel[i] = new ListJPanel();
			this.add(listJPanel[i].getListJPanel());		
		}
		fillList();		
		btnNew.addActionListener(event -> newBtnHandler(event) );	
		setVisible(true);
	}
	
	private void fillList(){
		int size = list.size();
		for(int i=0;i<size;i++){			
			listJPanel[i].setText(list.get(i));			
		}
		for(int i=size; i<LIST_SIZE;i++){
			listJPanel[i].setText("");		
		}
	}
	
	private void newBtnHandler(ActionEvent event){
		listJPanel[listSize].btnEditSave.setEnabled(true);
		listJPanel[listSize].editBtnHandler();
	}
	
	class ListJPanel{
		JButton btnEditSave;
		JButton btnDel;
		JTextField listJLabel;
		String prevContent;
		boolean isNew;
		
		ListJPanel(){
			btnEditSave = new JButton("Edit");
			btnDel = new JButton("Delete");
			listJLabel = new JTextField();
			init();			
		}
		void init(){
			btnEditSave.setFont(defualtFont);						
			btnDel.setFont(defualtFont);
			btnDel.setEnabled(false);			
			listJLabel.setFont(defualtFont);
			listJLabel.setEditable(false);
			
			btnEditSave.addActionListener(event -> editBtnHandler() );
			btnDel.addActionListener(event -> delBtnHandler());
			isNew = false;
		}
		void setText(String text){
			listJLabel.setText(text);
			if(text.isEmpty()){ //스케줄이 없으면				
				btnDel.setEnabled(false);
				isNew = true;
			}
			else{ //스케줄이 있으면
				btnDel.setEnabled(true);				
			}
		}
		
		JPanel getListJPanel(){
			JPanel JPanel = new JPanel();
			JPanel.setLayout(new BorderLayout());		
			
			JPanel eastJPanel = new JPanel();
			eastJPanel.setLayout(new GridLayout(1,2,5,5));
			eastJPanel.add(btnEditSave); eastJPanel.add(btnDel);
			
			JPanel.add(listJLabel, BorderLayout.CENTER);
			JPanel.add(eastJPanel, BorderLayout.EAST);
			
			return JPanel;
		}
		
		private void editBtnHandler(){		
			String nowState = btnEditSave.getActionCommand();
			if(nowState.equals("Edit")){
				listJLabel.setEditable(true);
				btnEditSave.setText("Save");
				prevContent = listJLabel.getText();
				btnDel.setEnabled(false);
			}else if(nowState.equals("Save")){
				String newContent = listJLabel.getText();
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
				btnEditSave.setText("Edit");
				btnDel.setEnabled(true);
				listJLabel.setEditable(false);	
			} 
		}		
		private void delBtnHandler(){
			String removeContent = listJLabel.getText();			
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
}
