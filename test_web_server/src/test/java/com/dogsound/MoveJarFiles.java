//package com.dogsound;
//
//import java.io.Console;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.Scanner;
//
//import org.junit.Before;
//import org.junit.Test;
//
//public class MoveJarFiles {
//	//static String WORK = "home";
//	static String WORK ="comp";
//	static String ENV = "windows";	
//	//static String ENV = "linux";
//	String src,dest;
//	
//	@Before
//	public void setUp() {
//		if( ENV.equals("windows") ) {
//			if( WORK.equals("home") ) {
//				src = "C:\\work(private_project)\\TracertAgent\\target\\TracertAgent-0.0.1-SNAPSHOT-jar-with-dependencies.jar";
//			} else if( WORK.equals("comp") ) {
//				src ="C:\\work(server)\\test_web_server\\target\\test_web_server.war";
//			}			
//			dest = "D:\\war";						
//		} else if( ENV.equals("linux") ) {
//			
//		}
//	}
//	
//	@Test
//	public void moveJarFilesByCmd() {
//		try {
//			String cmd = "cmd.exe /c copy " + src + " " + dest;			
//			Runtime.getRuntime().exec(cmd);			
//		} catch(IOException e) {
//			e.printStackTrace();
//			System.exit(1);
//		}
//	}
//	
//	//@Test
//	public void cmdIOTest() {
//		//Process process = Runtime.getRuntime().exec(cmd);
//		Console console = System.console();
//		if( console == null ) {
//			System.exit(1);
//		}
//		
//		try {
//			String cmd = "cmd.exe java c:\\test\\Test";
//			Runtime.getRuntime().exec(cmd);	
//			Scanner scanner = new Scanner(System.in);
//			System.out.println(scanner.nextLine());
//		} catch(Exception e) {
//			e.printStackTrace();
//			System.exit(1);
//		}	
//	}
//	
//	//@Test
//	public void executeJava() {
//		Scanner scanner = null;
//		try {
//			String cmd = "cmd.exe java c:\\test\\Test";
//			Process process = Runtime.getRuntime().exec(cmd);
//			scanner = new Scanner( process.getInputStream() );
//			while( scanner.hasNext() ) {
//				System.out.println(scanner.nextLine());
//			}
//		} catch(Exception e) {
//			e.printStackTrace();
//			System.exit(1);
//		} finally {
//			if(scanner != null ) {
//				scanner.close();
//			}
//		}
//	}
//	
//	// @Test
//	// 파일이동 코드 직접 구현하면, 설정파일 없다고 오류 나옴
//	// 이동에 문제가 있는듯
//	public void moveJarFiles() {
//		File destFile = new File(dest);
//		File srcFile = new File(src);
//		if( destFile.exists() ) {
//			destFile.delete();
//		}
//		
//		try (FileInputStream fis = new FileInputStream(srcFile);
//				FileOutputStream fos = new FileOutputStream(destFile)) {
//			
//			byte[] readDate = new byte[1024];
//			int readLen = 0;
//			while( (readLen = fis.read(readDate)) != -1 ) {
//				fos.write(readDate);
//			}
//		}
//		catch(Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//
//}
