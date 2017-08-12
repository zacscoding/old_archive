package nonblocking;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame {
	private static int cnt=1;
	private Selector selector;
	private ServerSocketChannel serverSocketChannel;
	private List<Client> connections=new Vector<>();
	
	
	//서버 시작 코드
	private void startServer(){
		try{
			selector=Selector.open(); //Selector 생성
			serverSocketChannel=ServerSocketChannel.open();
			serverSocketChannel.configureBlocking(false); //넌블로킹 설정
			serverSocketChannel.bind(new InetSocketAddress(5001));
			
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT); //Selector에 작업 유형 등록
		}catch(Exception e){
			if(serverSocketChannel.isOpen())
				stopServer();
			return;
		}
		
		Thread thread=new Thread(){
			@Override
			public void run(){
				while(true){
					try{
						int keyCount=selector.select(); //작업 처리 준비가 된 채널이 있을 때까지 대기
						if(keyCount==0)
							continue;
						Set<SelectionKey> selectedKeys=selector.selectedKeys();
						Iterator<SelectionKey> itr=selectedKeys.iterator();
						while(itr.hasNext()){
							SelectionKey selectionKey=itr.next();
							if(selectionKey.isAcceptable()){ //연결 수락 작업일 경우
								accept(selectionKey);
							}else if(selectionKey.isReadable()){ // 읽기 작업일 경우
								Client client=(Client)selectionKey.attachment();
								client.receive(selectionKey);
							}else if(selectionKey.isWritable()){ //쓰기 작업일 경우
								Client client=(Client)selectionKey.attachment();
								client.send(selectionKey);
							}
							itr.remove(); //선택된 키셋에서 처리 완료된 SelectionKey를 제거
						}						
					}catch(Exception e){
						if(serverSocketChannel.isOpen())
							stopServer();
						break;
					}
				}
			}
		};
		thread.start(); //스레드 시작
		displayText("[서버 시작]");
		btnStartStop.setText("stop");		
	}
	
	
	//서버 종료 코드
	private void stopServer(){
		try{
			Iterator<Client> itr=connections.iterator();
			while(itr.hasNext()){
				Client client=itr.next();
				client.socketChannel.close();
				itr.remove();
			}
			if(serverSocketChannel!=null && serverSocketChannel.isOpen())
				serverSocketChannel.close();
			if(selector!=null && selector.isOpen())
				selector.close();
			
			displayText("[서버 멈춤]");
			btnStartStop.setText("start");
		}catch(Exception e){
			
		}
	}
	
	//연결 수락 코드
	private void accept(SelectionKey selectionKey){
		try{
			//ServerSocketChannel 얻기
			ServerSocketChannel serverSocketChannel=(ServerSocketChannel)selectionKey.channel();
			//연결 수락
			SocketChannel socketChannel=serverSocketChannel.accept();
			
			String message="[연결 수락 : "+socketChannel.getRemoteAddress()+": "+Thread.currentThread().getName()+"]";
			displayText(message);
			
			Client client=new Client(socketChannel);
			connections.add(client);
			
			displayText("[연결 개수 : "+connections.size()+"]");			
		}catch(Exception e){
			if(serverSocketChannel.isOpen())
				stopServer();
		}
	}
	
	//데이터 통신 코드
	class Client{
		int id;
		SocketChannel socketChannel;
		String sendData;
		
		Client(SocketChannel socketChannel) throws IOException {
			id=cnt++;
			this.socketChannel=socketChannel;
			socketChannel.configureBlocking(false);			
			SelectionKey selectionKey=socketChannel.register(selector, SelectionKey.OP_READ); //읽기 작업 유형으로 Selector에 등록
			selectionKey.attach(this); //SelectionKey에 자기 자신을 첨부 객체로 저장
		}
		
		//데이터 받기 코드
		void receive(SelectionKey selectionKey){
			try{
				ByteBuffer byteBuffer=ByteBuffer.allocate(100);
				
				//상대방이 비정상 종료를 했을 경우 자동 IOException 발생
				int byteCount=socketChannel.read(byteBuffer);
				
				//상대방이 SocketChannel의 close() 메소드를 호출할 경우
				if(byteCount==-1)
					throw new IOException();
				
				String message="[요청 처리 : "+socketChannel.getRemoteAddress()+": "+Thread.currentThread().getName()+"]";
				displayText(message);
				
				byteBuffer.flip();
				Charset charset=Charset.forName("UTF-8");
				String data=Client.this.id+"] : "+charset.decode(byteBuffer).toString();
				sendAll(data);								
			}catch(Exception e){
				try{
					String data=Client.this.id+"]님이 나갔습니다.";
					sendAll(data);
					connections.remove(this);
					String message="[클라이언트 통신 안됨 : "+socketChannel.getRemoteAddress()+": "+Thread.currentThread().getName()+"]";
					displayText(message);
					socketChannel.close();
				}catch(IOException e2){
					
				}				
			}
		}
		
		void sendAll(String data){
			for(Client client : connections){
				if(Client.this!=client){
					client.sendData=data;
					SelectionKey key=client.socketChannel.keyFor(selector);
					key.interestOps(SelectionKey.OP_WRITE);
				}
			}
			selector.wakeup(); //변경된 작업 유형을 감지하도록 하기 위해 Selector의 select() 블로킹을 해제하고 다시 실행
		}
		
		
		//데이터 전송 코드
		void send(SelectionKey selectionKey){
			try{
				Charset charset=Charset.forName("UTF-8");
				ByteBuffer byteBuffer=charset.encode(sendData);
				socketChannel.write(byteBuffer);
				selectionKey.interestOps(SelectionKey.OP_READ); //작업 유형 변경
				selector.wakeup(); //변경된 작업 유형을 감지하도록 Selector의 select() 블로킹 해제
			}catch(Exception e){
				try{
					String message="[클라이언트 통신 안됨: "+socketChannel.getRemoteAddress()+": "+Thread.currentThread().getName()+"]";
					displayText(message);
					connections.remove(this);
					socketChannel.close();
				}catch(IOException e2){
					
				}
			}
		}
	}
	
	
	public Server(){
		initJFrame();
	}
	

	//////////////////
	// UI 코드
	private JTextArea txtDisplay;
	private JButton btnStartStop;
	private JScrollPane scroll;

	public void initJFrame() {
		this.setBounds(120, 200, 400, 400);
		this.setLayout(new BorderLayout());

		txtDisplay = new JTextArea();
		txtDisplay.setEditable(false);
		scroll = new JScrollPane(txtDisplay);

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
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
	}	
	
	private void displayText(String text){		
		txtDisplay.append(text+"\n");
		scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
	}
	
	

	public void start() {
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.start();

	}
}
