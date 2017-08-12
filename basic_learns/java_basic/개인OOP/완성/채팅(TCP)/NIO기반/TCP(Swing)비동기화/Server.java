package asynchronous;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Vector;
import java.util.concurrent.Executors;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Server extends JFrame {
	private AsynchronousChannelGroup channelGroup;
	private AsynchronousServerSocketChannel serverSocketChannel;
	private Vector<Client> connections = new Vector<Client>();
	private Queue<Integer> quitQueue = new PriorityQueue<Integer>(50);
	private static int numOfClient = 0;

	public Server() {
		super("Zac`s program[Server]");
		initJFrame();
	}

	private void startServer(){
		//서버 시작 코드
		try{
			//AsynchronousChannelGroup 생성
			channelGroup=AsynchronousChannelGroup.withFixedThreadPool(
					Runtime.getRuntime().availableProcessors(),
					Executors.defaultThreadFactory()
			);
			
			//5001번 포트에서 클라이언트의 연결을 수락
			serverSocketChannel = AsynchronousServerSocketChannel.open(channelGroup);
			serverSocketChannel.bind(new InetSocketAddress(5001));
		}catch(Exception e){
			if(serverSocketChannel.isOpen())
				stopServer();
			return;
		}
		
		//UI 처리
		displayText("[서버 시작]");
		btnStartStop.setText("stop");
		//System.out.println("처음 accept() 호출!");	
		serverSocketChannel.accept(null,
				new CompletionHandler<AsynchronousSocketChannel,Void>(){
					@Override
					public void completed(AsynchronousSocketChannel socketChannel,Void attachment){
						try{
							String message="[연결 수락: "+socketChannel.getRemoteAddress() + ": "+Thread.currentThread().getName();
							displayText(message);
						}catch(IOException e){}
						
						Integer id=null;
						
						if(!quitQueue.isEmpty())
							id=quitQueue.poll();
						else
							id=numOfClient++;						
						
						Client client=new Client(socketChannel,id);
						connections.add(client);						
						displayText("[연결 개수 : "+connections.size()+"}");
						//System.out.println("accept() 재 호출!");	
						serverSocketChannel.accept(null,this); // accept() 다시 호출
					}
					@Override
					public void failed(Throwable exc,Void attachment){
						if(serverSocketChannel.isOpen())
							stopServer();
					}
		});
	}

	private void stopServer() {
		// 서버 종료 코드
		try {
			connections.clear();
			if (channelGroup != null && !channelGroup.isShutdown())
				channelGroup.shutdownNow();
			displayText("[서버 멈춤]");
			btnStartStop.setText("start");
		} catch (Exception e) {
		}
	}

	class Client { // 데이터 통신 코드
		int id;
		AsynchronousSocketChannel socketChannel;

		Client(AsynchronousSocketChannel socketChannel, int id) {
			this.socketChannel = socketChannel;
			this.id = id;
			receive();
		}

		Integer getId() {
			return Integer.valueOf(id);
		}

		void receive() {
			// 데이터 받기 코드
			ByteBuffer byteBuffer = ByteBuffer.allocate(100);
			socketChannel.read(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
				@Override
				public void completed(Integer result, ByteBuffer attachment) {
					try {
						String message = "[요청 처리: " + socketChannel.getRemoteAddress() + ": "
								+ Thread.currentThread().getName();
						displayText(message);

						// 문자열 변환
						attachment.flip();
						Charset charset = Charset.forName("UTF-8");
						String data = charset.decode(attachment).toString();

						// 모든 클라이언트에게 보내기
						for(Client client : connections){
							if(Client.this!=client)
								client.send(id + "]  " + data);
						}
						
						ByteBuffer byteBuffer = ByteBuffer.allocate(100);
						socketChannel.read(byteBuffer, byteBuffer, this); // 다시 데이터 읽기
						
					} catch (Exception e) {
					}
				}

				@Override
				public void failed(Throwable exc, ByteBuffer attachment) {
					try {
						String message = "[클라이언트 통신 안됨 : " + socketChannel.getRemoteAddress() + ": "
								+ Thread.currentThread().getName();
						displayText(message);
						
						// 모든 클라이언트에게 퇴장 메시지 보내기
						for(Client client : connections){
							if(Client.this!=client)
								client.send(id + "] 님이 나갔습니다.");
						}
						
						quitQueue.add(Client.this.id); //우선순위 큐에 저장
						connections.remove(Client.this); //connections에서 제거
						socketChannel.close();
					} catch (IOException e) {
					}

				}
			});

		}

		void send(String data) {
			// 데이터 전송 코드
			Charset charset = Charset.forName("UTF-8");
			ByteBuffer byteBuffer = charset.encode(data);
			socketChannel.write(byteBuffer, null, new CompletionHandler<Integer, Void>() {
				@Override
				public void completed(Integer result, Void attachment) {
				}

				@Override
				public void failed(Throwable exc, Void attachment) {
					try {
						String message = "[클라이언트 통신 안됨: " + socketChannel.getRemoteAddress() + ": "
								+ Thread.currentThread().getName();
						displayText(message);
						connections.remove(Client.this);
						socketChannel.close();
					} catch (IOException e) {
					}
				}
			});
		}
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

	private void displayText(String text) {
		txtDisplay.append(text + "\n");
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
