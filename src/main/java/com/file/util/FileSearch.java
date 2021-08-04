package com.file.util;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

public class FileSearch {

	//Logger logger = Logger.getLogger(this.getClass());
	Class<?> firstClass = this.getClass();
	
	public void search(File dirPath) throws Exception {
		
		if(dirPath == null) {
			new Exception("경로를 입력하시기 바랍니다.");
		}
		
		File file = null;

		for(String fileName : dirPath.list()) {
			file = new File(dirPath+"\\"+fileName);
			
			if(!file.isDirectory()) {
				System.out.println(file.getAbsolutePath());
			}else {
				Method method = this.getClass().getDeclaredMethod("search",file.getClass());
				method.invoke(this, file);
			}
		}
		
	}
}
