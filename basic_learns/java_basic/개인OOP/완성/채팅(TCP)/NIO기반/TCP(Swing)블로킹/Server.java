package tcpblockingchannel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
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
	private ExecutorService executorService; //스레드 풀
	private ServerSocketChannel serverSocketChannel; //서버 소켓 채널
	private List<Client> connections=new Vector<Client>(); //연결 Client 관리
	
	public Server(){
		super("Zac`s Chatting program");
		initJFrame();
	}
	
	private void startServer(){
		//서버 시작 코드
		executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors()); //코어 수 만큼 작업 스레드 생성
		
		//5001번 포트에서 클라이언트의 연결을 수락하는 ServerSocketChannel 생성
		try{
			serverSocketChannel=ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(true); //블러킹 방식(기본값임)
			serverSocketChannel.bind(new InetSocketAddress(5001)); //5001 포트 번호로 바인딩
			//5001포트가 사용중이면 BindException 발생
		}catch(Exception e){
			if(serverSocketChannel.isOpen())
				stopServer();
			return;
		}
		//연결 수락하는 스레드 생성
		Runnable runnable=new Runnable(){
			@Override
			public void run(){
				displayText("[서버 시작]");
				btnStartStop.setText("stop");				
				//연결 수락
				while(true){
					try{
						System.out.println("연결 대기중");
						SocketChannel socketChannel=serverSocketChannel.accept(); //연결 수락(연결 없으면 블로킹되어있음)
						System.out.println("연결 완료");
						String message="[연결 수락 : "+socketChannel.getRemoteAddress()+": "
										+Thread.currentThread().getName()+" ]";
						displayText(message);
						
						Client client=new Client(socketChannel);
						connections.add(client);
						
					}catch(Exception e){
						if(!serverSocketChannel.isOpen())
							stopServer();
						break;
					}
				}
				
			}
		};
		executorService.submit(runnable); //스레드풀에 등록		
	}

	private void stopServer(){
		//서버 종료 코드		
		try{
			//모든 SocketChannel 닫기
			Iterator<Client> iterator=connections.iterator();
			while(iterator.hasNext()){
				Client client=iterator.next();
				client.socketChannel.close();
				iterator.remove();
			}
			
			//ServerSocketChannel 닫기
			if(serverSocketChannel!=null && !executorService.isShutdown())
				serverSocketChannel.close();
			
			//ExecutorService 종료
			if(executorService!=null && !executorService.isShutdown())
				executorService.shutdown();
			
			//UI처리
			displayText("[서버 멈춤]");
			btnStartStop.setText("start");			
		}catch(Exception e){}
	}
	
	//Client 클래스 : 서버에 연결하는 클라이언트 관리
	private class Client{
		SocketChannel socketChannel;
		
		Client(SocketChannel socketChannel){
			this.socketChannel=socketChannel;
			receive();
		}
		
		//데이터 받기
		void receive(){
			Runnable runnable=new Runnable(){
				@Override
				public void run(){
					while(true){
						try{
							ByteBuffer byteBuffer=ByteBuffer.allocate(100);
						
							//클라이언트가 비정상 종료를 했을 경우 IOException 발생
							int readByteCount=socketChannel.read(byteBuffer);
							
							//클라이언트가 정상적으로 SocketChannel의 close()를 호출
							if(readByteCount==-1)
								throw new IOException();
							
							String message="[요청 처리 : "+socketChannel.getRemoteAddress()+": "
												+Thread.currentThread().getName()+"]";
							displayText(message);
							
							//문자열을 변환
							byteBuffer.flip();
							Charset charset=Charset.forName("UTF-8");
							String data=charset.decode(byteBuffer).toString();
							
							//모든 클라이언트에게 보냄
							for(Client client : connections){
								if(client.socketChannel!=socketChannel)
									client.send(data);			
							}
						}catch(Exception e){
							try{
								connections.remove(Client.this);
								String message="[클라이언트 통신 안됨 : "+socketChannel.getRemoteAddress()+": "
											+Thread.currentThread().getName()+"]";
								displayText(message);
								socketChannel.close();
							}catch(IOException e2){}
							break;
						}
						
					}
				}
			};
			//스레드풀에서 처리
			executorService.submit(runnable);			
		}
		
		//데이터 전송 
		void send(String data){
			Runnable runnable=new Runnable(){
				@Override
				public void run(){
					try{
						Charset charset=Charset.forName("UTF-8"); 
						ByteBuffer byteBuffer=charset.encode(data); //data를 UTF-8로 인코딩 & byteBuffer에 바이트로 저장
						socketChannel.write(byteBuffer);						
					}catch(Exception e){ //write()메소드 실행 중 예외 발생 
						try{
							String message="[클라이언트 통신 안됨: "+socketChannel.getRemoteAddress()+": "
											+Thread.currentThread().getName()+"]";
							displayText(message); //메시지 출력
							connections.remove(Client.this); //connections 컬렉션에서 예외가 발생한 Client 제거
							socketChannel.close(); 
						}catch(IOException e2){}
					}
				}
			};
			executorService.submit(runnable);			
		}
	}
	
	private void displayText(String txt){
		txtDisplay.append(txt+"\n");
		scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
	}
	
	/////////////////////////
	//UI 생성 코드
	private JTextArea txtDisplay;
	private JButton btnStartStop;
	private JScrollPane scroll;
	
	private void initJFrame() {		
		this.setBounds(120, 200,400, 200);
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
		
	
	
	//Main 함수
	public static void main(String[] args){
		Server server=new Server();
		server.start();
		
	}
	

}
