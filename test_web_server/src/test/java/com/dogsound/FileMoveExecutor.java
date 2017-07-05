package com.dogsound;

import java.io.File;
import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

public class FileMoveExecutor {
	static String srcDir;
	static String destDir;
	
	@BeforeClass
	public static void setUp() {
		srcDir = "D:\\download(in C)";
		destDir = "D:\\jar";		
	}
	
	//@Test
	public void test() {
		try {
			String cmd = "cmd.exe /c move c:\\test.txt d:\\jar";
			System.out.println(cmd);
			Runtime.getRuntime().exec(cmd);			
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
	@Test
	public void fileMovesExecutor() {
		File dir = new File(srcDir);
		String[] files = dir.list();
		for( String file : files ) {
			if( file.endsWith(".jar") ) {
				moveFile(file);
			}
		}
	}
	
	private void moveFile(String fileName) {
		try {
			String cmd = "cmd.exe /c move " + srcDir + File.separator + fileName + " " + destDir;
			System.out.println(cmd);
			Runtime.getRuntime().exec(cmd);			
		} catch(IOException e) {
			e.printStackTrace();
			System.exit(1);
		}		
	}
}
