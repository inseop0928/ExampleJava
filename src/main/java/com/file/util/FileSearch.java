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

	public int getFilesCnt() {
		return (files.size()+1);
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
				//폴더일 경우 재귀호출
				Method method = this.getClass().getDeclaredMethod("search", file.getClass());
				method.invoke(this, file);
			}
		}
	}

	private String fileContent(File targetFile) throws Exception{
		//targetFile.canRead();
		//파일을 바이트 단위로 데이터를 읽음
		FileInputStream inputStream = new FileInputStream(targetFile);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));

		String line;
		StringBuffer sbf = new StringBuffer();
		int num = 1;
		while((line = bufferedReader.readLine())!=null) {

			//sbf.append(line+System.getProperty("line.separator"));
			//sbf.append(num + " : " + line + "\n");
			sbf.append(line + "\n");
			num++;
			//sbf.append(line+"\n");
		}

		bufferedReader.close();
		inputStream.close();

		return sbf.toString();
	}
	
	public void fileContentShow(File targetFile) throws Exception{
		//targetFile.canRead();

		if(targetFile == null || !targetFile.isFile()){
			throw new Exception("파일이 없습니다.");
		}else{
			logger.info("----------------------"+targetFile.getName()+" START-----------------------------");
			logger.info(fileContent(targetFile));
			logger.info("-------------------------END-------------------------------");
		}
	}
	
	public void fileContentTxtSrch(File targetFile, String searchText,int fileContentIdx) throws Exception{

		if(searchText == null ||"".equals(searchText)){
			throw new Exception("검색어를 입력하시기 바랍니다.");
		}

		if(targetFile == null || !targetFile.isFile()){
			throw new Exception("파일이 없습니다.");
		}else{
			String fileContent = fileContent(targetFile);
			long fileContentLen = fileContent.length();
			
			//logger.info(targetFile.getName()+" : "+ fileContentIdx);
						
			if(fileContent.indexOf(searchText,fileContentIdx)>=0) {

				if(fileContentIdx == 0){
					logger.info("----------------------"+targetFile.getName()+" : " + fileContentLen +"----------------------------");
				}
				int searchIdx = fileContent.indexOf(searchText,fileContentIdx);

				
				String line = (fileContent.substring(0, searchIdx).split("\n").length +1) + " : ";
				
				logger.info( line +  fileContent.substring(fileContent.lastIndexOf("\n",searchIdx),fileContent.indexOf("\n",searchIdx)));

				fileContentIdx = searchIdx + searchText.length();
				
				//끝이아닐경우 재귀 호출
				if(fileContentIdx < fileContentLen){
					Method method = this.getClass().getDeclaredMethod("fileContentTxtSrch",targetFile.getClass(),searchText.getClass(),int.class);
					method.invoke(this, targetFile,searchText,fileContentIdx);
				}

				//logger.info(fileContent.length());
				//logger.info(fileContent.indexOf(searchText));
				//logger.info(searchText.length());
				//fromIdx = fileContent.indexOf(searchText);
			}
		}
	}
}
