package com.aspn.community.schedule.backups;

import com.aspn.community.schedule.utils.FTPProperties;
import com.aspn.community.schedule.utils.FTPUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class FileService {

    private final static Logger logger = LoggerFactory.getLogger(FileService.class);

    @Resource
    private FTPProperties ftpProperties;

    private final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");

    /**
     * @Author nanguangjun
     * @Description // 把备份的mysql dump 放到ftp文件夹里
     * @Date 13:38 2021/10/14
     * @Param []
     * @return void
     **/

//    @Scheduled(cron = "1 20 10 * * ?") //每天0点30
//    @Scheduled(fixedRate = 1000*60*10)
    public void uploadMysqlDumpToFtp(){
        //读取本地文件
        String mysqlBackupFileName = ftpProperties.getMysqlBackup();
        String localPath = ftpProperties.getLocalFile() + mysqlBackupFileName;
        File file  = FileUtils.getFile(localPath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            logger.info("##### uploadMysqlDumpToFtp success !!!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            logger.error("##### uploadMysqlDumpToFtp " + e.getMessage());
        }
        String name = FilenameUtils.getName(localPath);
        logger.info("## start ### uploadMysqlDumpToFtp start" + formatter.format(new Date()));
        //上传文件
        try{
            this.uploadFile(fileInputStream,name);
        }catch (Exception e){
            logger.error("######uploadMysqlDumpToFtp upload file error:" + e.getMessage());
        }
        logger.info("## end ### uploadMysqlDumpToFtp end" + formatter.format(new Date()));
    }

    /**
     * @Author nanguangjun
     * @Description // 把备份的minIo包放到ftp文件夹里
     * @Date 13:38 2021/10/14
     * @Param []
     * @return void
     **/
//    @Scheduled(cron = "1 30 10 * * ?") //每天凌晨1点
//    @Scheduled(fixedRate = 1000*60*20)
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
            this.uploadFile(fileInputStream,name);
        }catch (Exception e){
            logger.error("######uploadMinIoToFtp upload file error:" + e.getMessage());
        }
        logger.info("## end ### uploadMinIoToFtp end" + formatter.format(new Date()));
    }


    /**
     * @Author nanguangjun
     * @Description //把文件上传至FTP文件夹里
     * @Date 13:43 2021/10/14
     * @Param [inputStream, originalFilename]
     * @return void
     **/
    public void uploadFile(InputStream inputStream, String originalFilename){
        try{
            boolean flag = FTPUtils.uploadFile(ftpProperties.getHostName(),
                    ftpProperties.getUserName(), ftpProperties.getPassWord(),
                    Integer.valueOf(ftpProperties.getPort()), ftpProperties.getBaseFile(), originalFilename,
                    inputStream);
            if (flag){
                logger.info("### 上传成功:" + originalFilename);
            }else {
                logger.info("### 上传失败:" + originalFilename);
            }
        }catch (Exception e){
            logger.error("### 上传失败失败 Exception" + e.getMessage());
        }
    }


}
