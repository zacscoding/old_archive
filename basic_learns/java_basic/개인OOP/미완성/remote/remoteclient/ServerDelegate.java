package remoteclient;

import java.awt.Robot;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

//서버로 부터 받은 명령을 실행

public class ServerDelegate extends Thread {
	Socket socket = null;
	Robot robot = null;
	boolean continueLoop = true;
	
	public ServerDelegate(Socket socket,Robot robot){
		this.socket = socket;
		this.robot = robot;
		start();		
	}
	
	public void run(){
		Scanner scanner = null;
		try{
			//prepare Scanner Object
			scanner = new Scanner(new BufferedInputStream(socket.getInputStream()));
			
			while(continueLoop){
				System.out.println("waitting for command");
				int command = scanner.nextInt();
				switch(command){
				case -1:
					robot.mousePress(scanner.nextInt());
					break;
				case -2:
					robot.mouseRelease(scanner.nextInt());
					break;
				case -3:
					robot.keyPress(scanner.nextInt());
					break;
				case -4:
					robot.keyRelease(scanner.nextInt());
					break;
				case -5:
					robot.mouseMove(scanner.nextInt(),scanner.nextInt());
					break;				
				}
			}			
		}catch(IOException e){
			continueLoop = false;
			e.printStackTrace();
		}		
	}
}
