package tcpblockingchannel;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame {
	SocketChannel socketChannel;
	
	public Client(){
		super("Zac`s client program");
		initJFrame();
	}

	// 연결 시작 코드
	void startClient() {
		Thread thread=new Thread(){
			@Override 
			public void run(){
				//연결 
				try{
					socketChannel=SocketChannel.open(); //Socket Channel 생성
					socketChannel.configureBlocking(true); //블러킹 방식 설정(기본값임)
					socketChannel.connect(new InetSocketAddress("localhost",5001)); //5001포트로 연결
					//UI처리
					displayText("[연결완료 : "+socketChannel.getRemoteAddress()+"]");
					btnConn.setText("stop");
					btnSend.setEnabled(true);										
				}catch(Exception e){
					displayText("[서버 통신 안됨]");
					if(socketChannel.isOpen())
						stopClient();
					return;
				}
				
				//서버에서 보낸 데이터 받기
				receive(); 
				
			}			
		};
		thread.start(); //스레드 시작
	}

	// 연결 끊기 코드
	void stopClient() {
		try{
			//UI 처리
			displayText("[연결 끊음]");
			btnConn.setText("start");
			btnSend.setEnabled(false);
			
			//socketChannel이 열려 있으면 닫기 
			if(socketChannel!=null && socketChannel.isOpen())
				socketChannel.close();
		}catch(IOException e){}
	}
	
	// 데이터 받기 코드
	void receive() {
		while(true){
			try{
				ByteBuffer byteBuffer=ByteBuffer.allocate(100); //ByteBuffer 100바이트 할당
				
				//서버가 비정상적으로 종료했을 경우 IOExcpetion 발생
				int readByteCount=socketChannel.read(byteBuffer); //서버가 데이터 보내기전 까지 스레드 블러킹 되있음 
				
				//서버가 정상적으로 Socket의 close()를 호출했을 경우 IOException 발생 시키기
				if(readByteCount==-1)
					throw new IOException();
				
				byteBuffer.flip(); // limit을 pos로 pos 0 으로
				Charset charset=Charset.forName("UTF-8");
				String data=charset.decode(byteBuffer).toString(); //byteBuffer에 있는 데이터 UTF-8로 디코딩 & CharBuffer.toString
				
				displayText("[상대방] : "+data);				
			}catch(Exception e){
				displayText("[서버 통신 안됨]");
				stopClient(); 
				break; //while문 종료
			}
		}
	}

	// 데이터 전송 코드
	void send(String data) {
		Thread thread=new Thread(){
			@Override
			public void run(){
				try{
					Charset charset=Charset.forName("UTF-8");
					ByteBuffer byteBuffer=charset.encode(data); //data를 UTF-8로 인코딩한 바이트 데이터 & 버퍼에 저장
					socketChannel.write(byteBuffer); //전송 & 예외 발생 가능
					displayText("[나] : "+data);
				}catch(Exception e){ //wrtie()과정에서 예외 발생 시 
					displayText("[서버 통신 안됨]");
					stopClient();
				}
				
			}
		};
		thread.start();
	}
	
	private void displayText(String str){
		txtDisplay.append(str+"\n");
		scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
	}

	///////////////////////////
	// UI 생성코드
	//////////////////
	// UI 코드
	JTextArea txtDisplay;
	JTextField txtInput;
	JButton btnConn, btnSend;
	JScrollPane scroll;

	void initJFrame() {		
		this.setBounds(300, 300, 400, 400);
		this.setLayout(new BorderLayout());

		txtDisplay = new JTextArea();
		txtDisplay.setEditable(false);		
		scroll=new JScrollPane(txtDisplay);

		txtInput = new JTextField();
		txtInput.addActionListener(event -> {
			send(txtInput.getText());
			txtInput.setText("");
			});

		btnConn = new JButton("start");
		btnConn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnConn.getText().equals("start")) {
					startClient();
				} else if (btnConn.getText().equals("stop")) {
					stopClient();
				}
			}
		});

		btnSend = new JButton("send");
		btnSend.setEnabled(false);
		btnSend.addActionListener(event -> {
			send(txtInput.getText());
			txtInput.setText("");
			});

		Panel panel = new Panel(new BorderLayout());

		panel.add(txtInput, BorderLayout.CENTER);
		panel.add(btnConn, BorderLayout.WEST);
		panel.add(btnSend, BorderLayout.EAST);

		this.add(panel, BorderLayout.SOUTH);
		this.add(scroll, BorderLayout.CENTER);

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setVisible(true);
	}
	
	public void start(){
		this.setVisible(true);
	}
	
	
	public static void main(String[] args){
		Client client=new Client();
		client.start();
	}

}
