import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame {
	Socket socket;

	public Client() {
		super("Client");
		initFrame();
	}

	void startClient() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket();
					socket.connect(new InetSocketAddress("localhost", 5001));
					displayText("[연결 완료: " + socket.getRemoteSocketAddress() + "]");
					btnConn.setText("stop");
					btnSend.setEnabled(true);
				} catch (Exception e) {
					displayText("[서버 통신 안됨]");
					if (!socket.isClosed())
						stopClient();
					return;
				}
				receive();
			}
		};
		thread.start();
	}

	void stopClient() {
		try {
			displayText("[연결 끊음]");
			btnConn.setText("start");
			btnSend.setEnabled(false);
			if (socket != null && !socket.isClosed())
				socket.close();
		} catch (IOException e) {
		}
	}

	void receive() {
		while (true) {
			try {
				byte[] byteArr = new byte[100];
				InputStream inputStream = socket.getInputStream();
				int readByteCount = inputStream.read(byteArr);
				if (readByteCount == -1)
					throw new IOException();
				String data = new String(byteArr, 0, readByteCount, "UTF-8");
				displayText("[상대방] " + data);
			} catch (Exception e) {
				displayText("[서버 통신 안됨]");
				stopClient();
				break;
			}
		}
	}

	void send(String data) {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					byte[] byteArr = new byte[100];
					byteArr = data.getBytes("UTF-8");
					OutputStream outputStream = socket.getOutputStream();
					outputStream.write(byteArr);
					outputStream.flush();
					displayText("[나] " + data);
				} catch (Exception e) {
					displayText("[서버 통신 안됨]");
					stopClient();		
				}
			}
		};
		thread.start();
	}

	void displayText(String text) {
		txtDisplay.append(text + '\n');
		//자동으로 맨밑으로 스크롤 이동
		scroll.getVerticalScrollBar().setValue(scroll.getVerticalScrollBar().getMaximum());
	}

	void sendText() {
		send(txtInput.getText());
		txtInput.setText("");
	}

	//////////////////
	// UI 코드
	JTextArea txtDisplay;
	JTextField txtInput;
	JButton btnConn, btnSend;
	JFrame frm;
	JScrollPane scroll;

	void initFrame() {		
		this.setBounds(250, 200, 400, 400);
		this.setLayout(new BorderLayout());

		txtDisplay = new JTextArea();
		txtDisplay.setEditable(false);
		scroll = new JScrollPane(txtDisplay);
		
		
		
		txtInput = new JTextField();
		txtInput.addActionListener(e -> sendText());

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
		btnSend.addActionListener(e -> sendText());

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
	}
	
	public void start(){
		this.setVisible(true);
	}
	

	public static void main(String[] args) {
		Client client = new Client();
		client.start();
		
	}

}
