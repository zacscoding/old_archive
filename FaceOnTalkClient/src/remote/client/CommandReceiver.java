package remote.client;

import java.awt.Robot;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import remote.Commands;

public class CommandReceiver {	
	private Socket socket;
	private Robot robot;	
	private Boolean isContinue;
	
	public CommandReceiver(Socket socket, Robot robot) {
		this.socket = socket;
		this.robot = robot;
		isContinue = true;
		receive();
	}
	
	private void receive() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				Scanner scanner = null;
				try {
					scanner = new Scanner(socket.getInputStream());					
					while(isContinue) {
						int command = scanner.nextInt();						
						switch(command) {
						case Commands.KEY_PRESS :
							robot.keyPress(scanner.nextInt());
							break;
						case Commands.KEY_RELEASE :
							robot.keyRelease(scanner.nextInt());
							break;
						case Commands.MOUSE_MOVE :							
							robot.mouseMove(scanner.nextInt(),scanner.nextInt());
							break;
						case Commands.MOUSE_PRESS :
							robot.mousePress(scanner.nextInt());
							break;
						case Commands.MOUSE_RELEASE :
							robot.mouseRelease(scanner.nextInt());
							break;
						}
					}					
				} catch(IOException e) {
					e.printStackTrace();
				}				
			}
		};
		thread.start();
	}	
	
	public void interrupt() {
		isContinue = false;
	}
}
