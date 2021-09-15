package com.aspn.community.schedule.test.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;


@Component
@Slf4j
public class TestService {
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
        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
