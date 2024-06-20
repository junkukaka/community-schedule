package com.aspn.community.schedule.utils;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ftp配置文件封装
 * @Auther: hrabbit
 * @Date: 2018-04-17 下午6:13
 * @Description:
 */
@Component("ftpProperties")
@Data
public class FTPProperties {
    //ftp服务器地址
    @Value("${ftp.hostName}")
    private String hostName;

    //ftp服务器端口号默认为21
    @Value("${ftp.port}")
    private String port;

    //ftp的用户名称
    @Value("${ftp.userName}")
    private String userName;

    //ftp的密码
    @Value("${ftp.passWord}")
    private String passWord;

    //FTP存储路径
    @Value("${ftp.baseFile}")
    private String baseFile;

    //本地上传路径
    @Value("${ftp.localFile}")
    private String localFile;

    @Value("${ftp.mysqlBackup}")
    private String mysqlBackup;

    @Value("${ftp.minIoBackup}")
    private String minIoBackup;

    private String getNow(){
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }

    public String getMysqlBackup() {
        return mysqlBackup + this.getNow() + ".sql";
    }

    public String getMinIoBackup() {
        return minIoBackup + this.getNow() + ".tar.gz";
    }
}