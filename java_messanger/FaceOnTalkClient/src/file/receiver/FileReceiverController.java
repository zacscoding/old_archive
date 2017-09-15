package file.receiver;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

public class FileReceiverController {	
	private String sender;
	private String userName;
	private String ip;
	private int port;
	private String path;
	private String fileName;
	
	public FileReceiverController(String path,String fileName,
			String sender,String userName, String ip, int port) {
		this.path = path;
		this.fileName = fileName;
		this.sender = sender;
		this.userName = userName;
		this.ip = ip;
		this.port = port;
		startReceiver();
	}
	
	private void startReceiver() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				Socket socket = null;
				OutputStream buffOut = null;
				InputStream buffIn = null;
				try {
					socket = new Socket(ip,port);
					buffOut = new BufferedOutputStream((socket.getOutputStream()));
					buffIn = new BufferedInputStream(socket.getInputStream());										
					buffOut.write(userName.getBytes());
					buffOut.flush();
					receiveFile(buffIn);
				} catch(IOException e) {					
					
				} finally {
					if(socket!=null) 
						try{socket.close();}catch(IOException ignored){}
				}
			}
		};
		thread.start();
	}
	
	private void receiveFile(InputStream buffIn) throws IOException{
		File file = new File(path,fileName);
		OutputStream fos = null;
		try {
			fos =new BufferedOutputStream(new FileOutputStream(file));						
			int readLen = -1;			
			while (true) {
				byte[] buffer = new byte[1024];
				readLen = buffIn.read(buffer);
				if (readLen == -1) {					
					break;
				}
				fos.write(buffer, 0, readLen);
				fos.flush();
			}	
			JOptionPane.showMessageDialog(null, "성공적으로 저장하였습니다.");
		}catch(IOException e) {			
			throw e;
		} finally {
			if(fos!=null)
				try{fos.close();}catch(IOException e2){}
		}
	}
}
