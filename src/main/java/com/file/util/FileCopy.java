package com.file.util;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import com.common.util.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileCopy {
    Logger logger = Logger.getLogger(this.getClass());

    public void newFile(File sourceFile,String targetDir) throws Exception{

        if(!sourceFile.isFile() || !sourceFile.exists()){
            throw new Exception("해당 파일이 존재하지 않습니다.");
        }

        String fileName = sourceFile.getName();
        
        logger.info("source file : "+ sourceFile.getAbsolutePath());
        
        targetDir = newPathDir(sourceFile.getParent(),targetDir);
        
        mkDir(targetDir);
        
        File targetFile  = new File(targetDir+"\\"+fileName);
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(targetFile);

        int fileByte = 0;
        // fis.read()가 -1 이면 파일을 다 읽은것
        while((fileByte = fis.read()) != -1) {
            fos.write(fileByte);
        }
        logger.info("new file : "+ targetFile.getAbsolutePath());

        fos.close();
        fis.close();
    }
    
    
    private void mkDir(String targetDir) {
    	File dir = new File(targetDir);
        if(!dir.exists()){
            dir.mkdir();
        }
    }
    
    private String newPathDir(String sourcePath,String targetDir) throws Exception{
    	
    	if(StringUtil.isNullorEmpty(sourcePath) || StringUtil.isNullorEmpty(sourcePath)) {
    		throw new Exception("해당 파일이 존재하지 않습니다.");
    	}

    	return targetDir + Stream.of(sourcePath.split("\\\\")).skip(Stream.of(targetDir.split("\\\\")).count()).reduce("",(a,b) -> a.concat("\\").concat(b));//이전 경로와 같은 뎁스 경로 추출	
    }
}
