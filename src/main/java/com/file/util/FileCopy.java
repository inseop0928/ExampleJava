package com.file.util;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopy {
    Logger logger = Logger.getLogger(this.getClass());

    public void newFile(File sourceFile,String targetDir) throws Exception{

        if(!sourceFile.isFile() || !sourceFile.exists()){
            throw new Exception("�ش� ������ �������� �ʽ��ϴ�.");
        }

        File dir = new File(targetDir);
        if(!dir.exists()){
            dir.mkdir();
        }

        String fileName = sourceFile.getName();

        logger.info("source file : "+ sourceFile.getAbsolutePath());
        File targetFile  = new File(targetDir+"\\"+fileName);
        FileInputStream fis = new FileInputStream(sourceFile);
        FileOutputStream fos = new FileOutputStream(targetFile);

        int fileByte = 0;
        // fis.read()�� -1 �̸� ������ �� ������
        while((fileByte = fis.read()) != -1) {
            fos.write(fileByte);
        }
        logger.info("new file : "+ targetFile.getAbsolutePath());

        fos.close();
        fis.close();
    }
}
