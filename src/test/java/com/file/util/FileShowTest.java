package com.file.util;

import com.web.crawling.WebCrawling;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileShowTest {

    FileSearch fileSearch = null;

    FileCopy fileCopy = null;


    @Before
    public void beforeTest(){
        if(fileSearch == null){
            fileSearch = new FileSearch();
        }

        if(fileCopy == null){
            fileCopy = new FileCopy();
        }
    }

    @Test
    public void search(){
        String folderPath ="경로설정";//ex)C:\\eclipse-workspace
        File files = new File(folderPath);

        try {
            fileSearch.search(files);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Test
    public void copy(){
        String sourceFile ="C:\\Users\\userName\\Desktop\\테스트.txt";//ex)C:\\eclipse-workspace
        String targetDir ="C:\\Users\\userName\\NEW";//ex)C:\\eclipse-workspace
        File file = new File(sourceFile);

        try {
            fileCopy.newFile(file,targetDir);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


}