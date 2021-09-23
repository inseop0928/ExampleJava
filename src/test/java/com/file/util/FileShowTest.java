package com.file.util;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileShowTest {

    Logger log = Logger.getLogger(this.getClass());

    FileSearch fileSearch = null;
    FileCopy fileCopy = null;
    
    Long runnigTime = 0L;
    

    @Before
    public void beforeTest(){
        if(fileSearch == null){
            fileSearch = new FileSearch();
        }

        if(fileCopy == null){
            fileCopy = new FileCopy();
        }
        
        log.info("---------- START -----------");
        
        runnigTime = System.currentTimeMillis();
    }
    
    @After
    public void afterTest(){

    	log.info("---------- END -----------");    	
    	log.info("running time : "  + (System.currentTimeMillis() - runnigTime) + "ms");
    }
    
    //@Test
    public void search(){
        String folderPath ="경로설정";//ex)C:\\eclipse-workspace
        File files = new File(folderPath);

        try {
            fileSearch.search(files);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
    
    //@Test
    public void showContent() {
    	
    	String folderPath ="C:\\Users\\user\\Desktop\\NEW_OS";//ex)C:\\eclipse-workspace
        File files = new File(folderPath);

        try {
            fileSearch.search(files);
            
            for(File file :fileSearch.getFiles()) {
        		fileSearch.fileContentShow(file);
        	}
           
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
        }
    	
    }

    //@Test
    public void copy(){
        String sourceFile ="C:\\Users\\userName\\Desktop\\테스트.txt";//ex)C:\\eclipse-workspace
        String targetDir ="C:\\Users\\userName\\NEW";//ex)C:\\eclipse-workspace
        File file = new File(sourceFile);

        try {
            fileCopy.newFile(file,targetDir);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    @Test
    public void fileContentTxtSrch(){
        String sourceDir ="C:\\Users\\user\\Desktop\\작성중\\새 폴더";//ex)C:\\eclipse-workspace
        String searchText ="텍스트";//ex)텍스트
        File sourceFile = new File(sourceDir);

        try {
            fileSearch.search(sourceFile);

            for(File file :fileSearch.getFiles()) {
                fileSearch.fileContentTxtSrch(file, searchText,0);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }

    //@Test
    public void allCopy(){
        String sourceDir ="C:\\Users\\user\\Desktop\\백업";//ex)C:\\eclipse-workspace
        String targetDir ="C:\\Users\\user\\Desktop\\개인2";//ex)C:\\eclipse-workspace
        File sourceFile = new File(sourceDir);

        try {
        	
        	fileSearch.search(sourceFile);
        	
        	for(File file :fileSearch.getFiles()) {
        		fileCopy.newFile(file,targetDir);
        	}
        	
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            log.error(e.getMessage());
        }
    }
}