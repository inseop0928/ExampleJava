package com.file.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class FileSearch {

	Logger logger = Logger.getLogger(this.getClass());
	Class<?> firstClass = this.getClass();
	
	private List<File> files = null;
	
	public FileSearch(){
		
		if(files == null) {
			files = new ArrayList<File>();
		}
	}
	
	public List<File> getFiles() {
		return files;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}
	
	public void search(File dirPath) throws Exception {
		
		if(dirPath == null) {
			new Exception("경로를 입력하시기 바랍니다.");
		}
		
		File file = null;

		for(String fileName : dirPath.list()) {
			file = new File(dirPath + "\\" + fileName);

			if (!file.isDirectory()) {
				System.out.println(file.getAbsolutePath());
				//fileContentSearch(file);
				files.add(file);
			} else {
				Method method = this.getClass().getDeclaredMethod("search", file.getClass());
				method.invoke(this, file);
			}
		}
	}

	public void fileContentShow(File targetFile) throws Exception{
		//targetFile.canRead();

		if(targetFile == null || !targetFile.isFile()){
			throw new Exception("파일이 없습니다.");
		}

		if(targetFile.isFile()){
			//파일을 바이트 단위로 데이터를 읽음
			FileInputStream inputStream = new FileInputStream(targetFile);
			//InputStreamReader reader=new InputStreamReader(inputStream,"UTF-8");
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
			
			logger.info("----------------------"+targetFile.getName()+" START-----------------------------");
			String line;
			StringBuffer sbf = new StringBuffer();
			long num = 1;
			while((line = bufferedReader.readLine())!=null){
				
				//sbf.append(line+System.getProperty("line.separator"));
				sbf.append(num + ":" + line+"\n");
				num++;
			}
			//logger.info(sbf.toString());
			
		
			bufferedReader.close();
			inputStream.close();
			logger.info("-------------------------END-------------------------------");
		}
	}
	
	public void fileContentSearch(String searchTxt,String fileContent) throws Exception{
		String srchText = "</script>"; 

		long fromIdx = 1;
		
		if(fileContent.indexOf(srchText)>=0) {
			
			//logger.info(targetFile.getName());
			logger.info(fileContent.length());
			logger.info(fileContent.indexOf(srchText));
			
			logger.info(srchText.length());
			
			fromIdx = fileContent.indexOf(srchText);
		}
		
	}
}
