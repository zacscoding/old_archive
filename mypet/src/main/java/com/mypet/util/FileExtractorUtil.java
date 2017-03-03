package com.mypet.util;

import java.io.File;
import java.io.FilenameFilter;

import org.springframework.http.MediaType;

/**
 * 파일 추출 유틸 클래스
 */
public class FileExtractorUtil {
	
	public static File[] getFileLists(String path, boolean onlyImages) {
		File dir = new File(path);
		File[] files = null;		
		
		if(onlyImages) { //이미지만		
			files = dir.listFiles(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					String formatName = name.substring(name.lastIndexOf(".")+1);					 
					MediaType type = MediaUtils.getMediaType(formatName);					
					return type!=null;
				}			
			});
		} else { //모든 파일
			files = dir.listFiles();
		}		
		return files;
	}
	
	public static String[] getFileNameLists(String path,boolean onlyImages) {
		File[] files = getFileLists(path,onlyImages);
		if(files == null)
			return null;
		String[] fileNames = new String[files.length];
		
		for(int i=0;i<fileNames.length;i++) {
			System.out.println(files[i].getName());
			fileNames[i] = files[i].getName();
		}
		return fileNames;
	}
}
