package file.sender;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class FileSenderController extends JFrame {
	private ServerSocket serverSocket;
	private String sender;
	private String[] receivers;
	private boolean continueLoop;
	private List<Receiver> connections;
	//private int resultCount = 0;
	private File selectedFile;	
	
	public FileSenderController(ServerSocket serverSocket,File file) { 
		this.serverSocket = serverSocket;
		this.selectedFile = file;	
		connections = new Vector<>();
		continueLoop = true;
		startServer();
		initFrame();
	}
	
	public FileSenderController(ServerSocket serverSocket, String sender,String[] receivers,File file) {
		this(serverSocket,file);
		this.sender = sender;
		this.receivers = receivers;
	}
	
	private void startServer() {
		Thread thread = new Thread () {
			public void run() {
				while(continueLoop) {
					try{
						Socket socket = serverSocket.accept();
						connections.add(new Receiver(socket));					
					}catch(Exception e) {
						if(!serverSocket.isClosed())
							stopServer();
					}
				}
			}
		};
		thread.start();
	}	
	
	private void stopServer() {
		try {
			Iterator<Receiver> itr = connections.iterator();
			while(itr.hasNext()) {
				Receiver r = itr.next();
				if(r.socket!=null)
					try{r.socket.close();}catch(IOException ignored){}
				itr.remove();
			}			
			if(serverSocket!=null && !serverSocket.isClosed())
				serverSocket.close();
		} catch(Exception ignored){}		
	}
	
	class Receiver {
		Socket socket;
		String name;
		JProgressBar statusBar;
		public Receiver(Socket socket) {
			this.socket = socket;
			receive();			
		}
		void receive() { //파일 받는 사람으로부터 이름 받기
			Thread thread = new Thread() {
				@Override
				public void run() {
					try{
						InputStream is = socket.getInputStream();						
						byte[] byteArr = new byte[100];
						int idx = 0;						
						while(true) {
							int data = is.read();
							if(data == -1) //상대방이 정상적으로 소켓 종료
								throw new IOException();
							if(data!='\n'){
								byteArr[idx++] = (byte)data;
							} else {
								break;
							}							
						}
						name = new String(byteArr,0,idx);
						for(int i=0;i<receivers.length;i++) {
							if(receivers[i].equals(name)) {
								statusPanel[i].setProgressBar(statusBar);
							}
						}
						send();
					}catch(IOException e){
						
					}
				}
			};
			thread.start();
		}
		void send() { //파일 전송
			///////
			//GUI 추가하기
			statusBar.setMaximum((int)selectedFile.length()/1024);
			statusBar.setMinimum(0);
			//try resource catch
			try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream((selectedFile)));
					 OutputStream os = new BufferedOutputStream(socket.getOutputStream())
					 ){	
				byte[] byteArr = new byte[1024];
				int readLen = -1;
				while(true) {	
					readLen = bis.read(byteArr);
					statusBar.setValue(statusBar.getValue()+1);
					if(readLen == -1)
						break;
					os.write(byteArr, 0, readLen);
					os.flush();
				}				
				JOptionPane.showMessageDialog(null, name+"님에게 파일 전송 완료");
			} catch(IOException e) {
				e.printStackTrace();
			} 
		}		
		JProgressBar getStatusBar() {
			return statusBar;
		}
	}
	public void interrupt() {
		continueLoop = false;
	}
	public void deniedUser(String name) {
		
	}
	
	/////////////
	//GUI	
	private FileStatusPanel[] statusPanel;
	private JLabel fileLabel;
	private JLabel filePathLabel;
	private void initFrame() {
		super.setSize(400, 500);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		int xpos = (int) (screen.getWidth() / 2 - this.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - this.getHeight() / 2);
		setLayout(new BorderLayout());
		
		fileLabel = new JLabel("파일 : ");
		filePathLabel = new JLabel(selectedFile.getAbsolutePath());
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.add(fileLabel, BorderLayout.WEST);
		northPanel.add(filePathLabel,BorderLayout.CENTER);
		add(northPanel,BorderLayout.NORTH);		
		
		
		//상태 panel
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridLayout(0,1));
		statusPanel = new FileStatusPanel[receivers.length];		
		for(int i=0;i<statusPanel.length;i++) {
			statusPanel[i] = new FileStatusPanel(receivers[i]);
			centerPanel.add(statusPanel[i]);
		}
		add(centerPanel,BorderLayout.CENTER);
		setVisible(true);		
	}
	
	class FileStatusPanel extends JPanel {	
		JLabel nameLabel;
		JLabel status;
		JProgressBar statusBar;
		FileStatusPanel(String name) {
			nameLabel = new JLabel(name);
			initPanel();
		}		
		void initPanel() {
			setLayout(new BorderLayout());
			add(nameLabel,BorderLayout.WEST);
			add(status,BorderLayout.CENTER);			
		}
		
		void setProgressBar(JProgressBar bar) {
			statusBar = bar;
			remove(status);
			add(statusBar,BorderLayout.CENTER);
			invalidate();
			repaint();
		}
		
	}	
}	
