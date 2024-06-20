package com.aspn.community.schedule.test.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@Slf4j
public class TestService {

    private final static Logger logger = LoggerFactory.getLogger(TestService.class);
    /**
     * @Author nanguangjun
     * @Description // 每一秒执行一次
     * @Date 14:47 2021/9/9
     * @Param []
     * @return void
     **/
//    @Scheduled(fixedRate = 1000)
    public void testRun(){
        log.debug("info");
        log.info(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        logger.debug("=====测试日志debug级别打印====");
        logger.info("======测试日志info级别打印=====");
        logger.error("=====测试日志error级别打印====");
        logger.warn("======测试日志warn级别打印=====");
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
