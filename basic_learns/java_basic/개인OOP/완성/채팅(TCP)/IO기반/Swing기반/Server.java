import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame {
	ExecutorService executorService;
	ServerSocket serverSocket;
	List<Client> connections = new Vector<Client>();
	
	// 생성자
	public Server() {
		super("Server");
		initJFrame();
	}

	// 서버 시작 코드
	void startServer(){
		//CPU 코어 수만큼 스레드 풀 생성
		executorService=Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
			
		//ServerSocket 생성 및 포트 바인등
		try{
			serverSocket=new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost",5001));
		}catch(Exception e){
			if(!serverSocket.isClosed()){
				stopServer();
				return;
			}
		}		
		
		//연결 수락
		Runnable runnable=new Runnable(){
			@Override
			public void run(){
				displayText("[서버 시작]");				
				btnStartStop.setText("stop");
				while(true){
					try{
						Socket socket=serverSocket.accept();
						String message="[연결 수락:"+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
						displayText(message);
						
						//Client 객체 저장
						Client client=new Client(socket);
						connections.add(client);
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
			Iterator<Client> itr=connections.iterator();
			while(itr.hasNext()){
				Client client=itr.next();
				client.socket.close();
				itr.remove();
			}
			
			if(serverSocket!=null && !serverSocket.isClosed())
				serverSocket.close();
			
			if(executorService!=null && !executorService.isShutdown())
				executorService.isShutdown();
			displayText("[서버 멈춤]");
			btnStartStop.setText("start");			
		}catch(Exception e){}
	}

	//Client 클래스(서버에서 클라이언트 관리해기 위한 클래스)
	class Client{
		Socket socket;
		
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
							byte[] byteArr=new byte[100];
							InputStream inputStream=socket.getInputStream();
							
							int readByteCount=inputStream.read(byteArr);
							
							if(readByteCount==-1)
								throw new IOException();
							
							String message="[요청 처리 : "+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
							displayText(message);
							
							String data=new String(byteArr,0,readByteCount,"UTF-8");							
							System.out.println("받은 데이터 : "+data);
							for(Client client : connections){	
								if(client.socket!=socket)
									client.send(data);								
							}							
						}
					}catch(Exception e){
						try{
							connections.remove(Client.this);
							String message="[클라이언트 통신 안됨: "+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
							displayText(message);
							socket.close();							
						}catch(IOException e2){}						
					}		
				}
			};
			executorService.submit(runnable);
		}		
		//데이터 전송
		void send(String data){
			Runnable runnable=new Runnable(){
				@Override
				public void run(){
					try{
						//클라이언트로 데이터 보내기
						byte[] byteArr=data.getBytes("UTF-8");						
						OutputStream outputStream=socket.getOutputStream();
						outputStream.write(byteArr);
						outputStream.flush();						
					}catch(Exception e){
						try{
							String message="[클라이언트 통신 안됨 :"+socket.getRemoteSocketAddress()+": "+Thread.currentThread().getName()+"]";
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
		scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
	}
	

	//////////////////
	// UI 코드
	JTextArea txtDisplay;
	JButton btnStartStop;
	JScrollPane scroll;

	public void initJFrame() {		
		this.setBounds(120, 200,400, 400);
		this.setLayout(new BorderLayout());

		txtDisplay = new JTextArea();
		txtDisplay.setEditable(false);	
		scroll=new JScrollPane(txtDisplay);

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
		
		this.add(scroll, BorderLayout.CENTER);
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
		Server server = new Server();
		server.start();

	}

}
