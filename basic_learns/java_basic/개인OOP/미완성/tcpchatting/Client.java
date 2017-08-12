package tcpchatting;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Client extends JFrame {
	Socket socket;
	String name;

	public Client(String name) {
		this.name = name;
		initFrame();
	}

	void startClient() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				try {
					socket = new Socket();
					socket.connect(new InetSocketAddress("localhost", 5001));
					displayText("[접속 정보 : " + socket.getRemoteSocketAddress() + "]");
					ObjectOutputStream obj = new ObjectOutputStream(socket.getOutputStream());
					obj.writeObject(new Message("login","name",null,null));
					obj.flush();					
					btnConn.setText("stop");
					btnSend.setEnabled(true);
				} catch (Exception e) {
					displayText("[failed to connect server]");
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
			displayText("[disconnect]");
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
				ObjectInputStream objIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
				Message msg = (Message)objIn.readObject();
								
				if (msg == null)
					throw new IOException();
				String data = msg.content;
				displayText(data);
			} catch (Exception e) {
				displayText("[disconnect]");
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
					String[] recipient = new String[1];
					recipient[0] = "hiva";
					Message msg = new Message("msg",name,txtInput.getText(),recipient);
					
					ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
					objOut.writeObject(msg);
					objOut.flush();
					displayText(name+" : " + data);
				} catch (Exception e) {
					displayText("[disconnect]");
					stopClient();		
				}
			}
		};
		thread.start();
	}

	void displayText(String text) {
		txtDisplay.append(text + '\n');
	}

	void sendText() {
		send(txtInput.getText());
		txtInput.setText("");
	}

	//////////////////
	// UI
	JTextArea txtDisplay;
	JTextField txtInput;
	JButton btnConn, btnSend;
	JFrame frm;

	void initFrame() {
		frm = new JFrame("Client");
		frm.setBounds(300, 500, 400, 200);
		frm.setLayout(new BorderLayout());

		txtDisplay = new JTextArea();
		txtDisplay.setEditable(false);
		JScrollPane simpleScroll = new JScrollPane(txtDisplay);
		txtDisplay.setLineWrap(true);
		txtDisplay.setWrapStyleWord(true);

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

		frm.add(panel, BorderLayout.SOUTH);
		frm.add(simpleScroll, BorderLayout.CENTER);

		frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		frm.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				System.exit(0);
			}
		});
		frm.setVisible(true);
	}

	public static void main(String[] args) {
		String name = JOptionPane.showInputDialog("nick name : ");
		new Client(name);
	}

}
