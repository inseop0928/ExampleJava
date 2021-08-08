package com.file.util;

import com.web.crawling.WebCrawling;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class FileShowTest {

    FileSearch fileSearch = null;


    @Before
    public void beforeTest(){
        if(fileSearch == null){
            fileSearch = new FileSearch();
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

}