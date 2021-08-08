package com.file.util;
import java.io.File;
import java.lang.reflect.Array;
import java.util.List;

public class FileShow {
	
	public static void main(String[] args) {
		
		String folderPath ="C:\\Users\\JEONGINSEOP\\Documents\\Douzone";//ex)C:\\eclipse-workspace
		File files = new File(folderPath);
		
		String[] pptFiles = {};//ex){"file1","file2"}
		String targetFolder = "";
		
		FileSearch fileSearch = new FileSearch();
		
		try {
			fileSearch.search(files);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		for(String fileName : files.list()) {
//			File file = new File(folderPath+"\\"+fileName);
//			System.out.println(file);
//			
//		}
		
		
//		for(String fileName : files.list()) {
//			File file = new File(folderPath+"\\"+fileName);
//			//System.out.println(fileName);
//			if(file.isDirectory()) {
//				
//				if(!".git".equals(fileName)) {
//					System.out.println(fileName);
//					
//					File newFile =   new File(targetFolder+"\\"+fileName);
//					
//					if(!newFile.isFile()) {
//						newFile.mkdir();
//					}
//				}
//				
//				for(String subFileName : file.list()) {
//					
//					for(String pptFile: pptFiles) {
//						String newPptFile = pptFile.replace("_", "").replace("-", "");
//						String newSubFileName = subFileName.replace("_", "").replace("-", "");
//
//						if(newSubFileName.contains(newPptFile)) {
//							System.out.println(subFileName);
//						}
//					}
//				}
//			}
//		}	
	}
}
