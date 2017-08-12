package tcpchatting;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class Server extends JFrame {
	ExecutorService executorService; 
	ServerSocket serverSocket; 
	//List<Client> connections = new Vector<Client>(); 
	Map<String,Client> connections = new Hashtable<>();
	
	
	public Server() {
		super("Server");
		initJFrame();
	}
	void startServer(){		
		executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());			
		try{
			serverSocket=new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost",5001));
		}catch(Exception e){ 
			if(!serverSocket.isClosed()){
				stopServer(); 
				return;
			}
		}		
		
		//
		Runnable runnable=new Runnable(){
			@Override
			public void run(){
				displayText("[start server]");				
				btnStartStop.setText("stop");
				while(true){
					try{
						Socket socket=serverSocket.accept(); 
						String message="[connect :"+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
						displayText(message);						
						new Client(socket);
					}catch(Exception e){
						if(!serverSocket.isClosed()){
							stopServer();
							break;
						}
					}
				}
			}
		};	
		executorService.submit(runnable); 
	}
	
	void stopServer(){ 
		try{		
			//연결 소켓들 끊기
			Iterator<String> itr = connections.keySet().iterator();
			while(itr.hasNext()){
				String name = itr.next();
				Client c = connections.remove(name);
				c.socket.close();
			}
			
			if(serverSocket!=null && !serverSocket.isClosed())
				serverSocket.close();
			
			if(executorService!=null && !executorService.isShutdown())
				executorService.isShutdown();
			displayText("[stop server]");
			btnStartStop.setText("start");			
		}catch(Exception e){}
	}

	class Client{
		Socket socket;
		String name;
		Client(Socket socket){
			this.socket=socket;
			receive();
		}		
		void receive(){ 
			Runnable runnable=new Runnable(){ 
				@Override
				public void run(){
					try{
						while(true){	
							ObjectInputStream objIn = new ObjectInputStream(
									new BufferedInputStream(socket.getInputStream()));
							
							Message req = (Message)objIn.readObject();
							
							if(req == null) 
								throw new IOException();
							
							if(req.type.equals("login")){
								name = req.sender;
								connections.put(name, Client.this);
							}else if(req.type.equals("msg")){
								String[] recipients = req.recipients;
								for(int i=0;i<recipients.length;i++){
									Client client = connections.get(recipients[i]);
									client.send(req.sender+" : "+req.content);
								}								
							}								
						}
					}catch(Exception e){
						try{
							connections.remove(Client.this);
							String message="[disconnect : "+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
							displayText(message);
							socket.close();
						}catch(IOException e2){}						
					}		
				}
			};
			executorService.submit(runnable);
		}		
		
		void send(String data){
			Runnable runnable=new Runnable(){ 
				@Override
				public void run(){
					try{
						byte[] byteArr=data.getBytes("UTF-8");						
						OutputStream outputStream=socket.getOutputStream();
						outputStream.write(byteArr);
						outputStream.flush();						
					}catch(Exception e){
						try{
							String message="[disconnect :"+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
							displayText(message);
							connections.remove(Client.this);
							socket.close();
						}catch(IOException e2){}
						
					}
				}				
			};
			executorService.submit(runnable);
		}			
	}
	
	
	void displayText(String text){		
		txtDisplay.append(text+"\n");
	}
	

	//////////////////
	// UI 
	JTextArea txtDisplay;
	JButton btnStartStop;

	public void initJFrame() {		
		this.setBounds(120, 200,400, 200);
		this.setLayout(new BorderLayout());

		txtDisplay = new JTextArea();
		txtDisplay.setEditable(false);		

		btnStartStop = new JButton("start");
		btnStartStop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {				
				if (btnStartStop.getText().equals("start")) {									
					startServer();
				} else if (btnStartStop.getText().equals("stop")) {					
					stopServer();
				}
			}
		});
		
		this.add(txtDisplay, BorderLayout.CENTER);
		this.add(btnStartStop, BorderLayout.SOUTH);	
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosed(WindowEvent e){
				System.exit(0);
			}
		});		
	}
	
	public void start(){
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new Server().start();
	}

}
