/***
 * 2조 프로젝트
 * Face On Talk ver - 0.9 
 * (각각의 1.0버전을 완성시킬..ㅋㅋ) 
 ***/


package main;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import chat.ChatServer;
import chat.anonymity.MultiServer;
import chat.anonymity.Room_Manager;
import jdbc.DBManager;
import web.WebServer;

/*
 * 메인 서버 : chat,web,anonymity 서버를 버튼으로 눌러서 시작
 */
public class MainServer extends JFrame implements ActionListener {
	private JButton btnWeb = new JButton("Web Server");
	private JButton btnChat = new JButton("Chat Server");
	private JButton btnAnonyChat = new JButton("AnonyChat Server");
	private WebServer webServer= null;
	private ChatServer chatServer = null;
	private MultiServer multiServer = null;
	public MainServer() {
		super("Main server");		
		init();
	}
	private void init(){
		setBounds(50,80,400,400);
		setLayout(new GridLayout(0,1,15,15));
		btnWeb.addActionListener(this);
		btnChat.addActionListener(this);
		btnAnonyChat.addActionListener(this);
		add(btnWeb);
		add(btnChat);
		add(btnAnonyChat);
		addWindowListener(new WindowAdapter() {
			@Override			
			public void windowClosing(WindowEvent e) {
				/// dispose();
				if(chatServer!=null)
					chatServer.stopServer();
				if(webServer!=null)
					webServer.stopServer();
				System.exit(0);
			}
		});		
	}
	@Override
	public void actionPerformed(ActionEvent e) {		
		Thread thread = null;		
		if(e.getActionCommand() == "Web Server"){
			thread = new Thread(){
				@Override
				public void run(){
					webServer = new WebServer();
					webServer.setVisible(true);
				}
			};			
		}else if(e.getActionCommand() == "Chat Server"){
			thread = new Thread(){
				@Override
				public void run(){
					chatServer = new ChatServer();
					chatServer.setVisible(true);					
				}
			};	
		}else if(e.getActionCommand() == "AnonyChat Server" ) {
			thread = new Thread() {
				@Override
				public void run() {
					multiServer = new MultiServer("AnonyChat Server");	
					multiServer.setVisible(true);
				}
			};
		}
		thread.start();
	}
	
	public static void main(String[] args) {
		//DB Connection 캐쉬  생성
		new DBManager();		
		new MainServer().setVisible(true);				
	}	
}
