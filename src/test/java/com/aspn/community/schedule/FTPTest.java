package com.aspn.community.schedule;


import com.aspn.community.schedule.backups.FileService;
import com.aspn.community.schedule.utils.FTPProperties;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

//@SpringBootTest
public class FTPTest {

    private final static Logger logger = LoggerFactory.getLogger(FTPTest.class);

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");

    public static final String PATH = "D:\\logs\\web_debug.log";
    public static final String PARENT_DIR = "D:\\logs";

    @Resource
    public FileService fileService;

    @Resource
    public FTPProperties ftpProperties;


//    @Test
    public void uploadMysqlDumpToFtp(){
        //读取本地文件
        String mysqlBackupFileName = ftpProperties.getMysqlBackup();
        String localPath = ftpProperties.getLocalFile() + mysqlBackupFileName;
        File file  = FileUtils.getFile(localPath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String name = FilenameUtils.getName(localPath);

        //上传文件
        try{
            fileService.uploadFile(fileInputStream,name);
        }catch (Exception e){

        }

    }

//    @Test
    public void uploadMinIoToFtp(){
        //读取本地文件
        logger.info("##### uploadMinIoToFtp start !!!");
        String minIoFileName = ftpProperties.getMinIoBackup();
        String localPath = ftpProperties.getLocalFile() + minIoFileName;
        File file  = FileUtils.getFile(localPath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            logger.info("##### uploadMinIoToFtp fileInputStream !!!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("##### uploadMinIoToFtp " + e.getMessage());
            logger.error("##### uploadMinIoToFtp " + e.getMessage());
        }
        String name = FilenameUtils.getName(localPath);
        logger.info("## start ### uploadMinIoToFtp start" + formatter.format(new Date()));
        //上传文件
        try{
            fileService.uploadFile(fileInputStream,name);
        }catch (Exception e){
            logger.error("######uploadMinIoToFtp upload file error:" + e.getMessage());
        }
        logger.info("## end ### uploadMinIoToFtp end" + formatter.format(new Date()));
    }

//    @Test
    public void getInputStreamAndFileName() throws FileNotFoundException {
        File file  = FileUtils.getFile(PATH);
        FileInputStream fileInputStream = new FileInputStream(file);
        String name = FilenameUtils.getName(PATH);
        fileService.uploadFile(fileInputStream,name);
    }

//    @Test
    public void readFile() throws IOException {
        String fullPath = FilenameUtils.getFullPath(PATH);
        String name = FilenameUtils.getName(PATH);
        String extension = FilenameUtils.getExtension(PATH);
        String baseName = FilenameUtils.getBaseName(PATH);
        System.out.println("###################### fullPath:" + fullPath);
        System.out.println("###################### name:" + name);
        System.out.println("###################### extension:" + extension);
        System.out.println("###################### baseName:" + baseName);

        File file  = FileUtils.getFile(PATH);
        LineIterator iter  = FileUtils.lineIterator(file);

        System.out.println("Contents of exampleTxt...");
//        while (iter.hasNext()) {
//            System.out.println("\t" + iter.next());
//        }
        iter.close();

        File parent  = FileUtils.getFile(PARENT_DIR);

        System.out.println("Parent directory contains exampleTxt file: " +
                FileUtils.directoryContains(parent ,file));
    }



//    @Test
    public void uploadFileTest(){
        System.out.println("######################" + ftpProperties.getLocalFile() + ftpProperties.getMinIoBackup());
        System.out.println("######################" + ftpProperties.getLocalFile() + ftpProperties.getMysqlBackup());
        System.out.println("######################test");
    }




}
