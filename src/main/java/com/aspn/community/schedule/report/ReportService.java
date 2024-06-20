package com.aspn.community.schedule.report;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Slf4j
public class ReportService {

    private final static Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Resource
    ReportServiceMapper reportServiceMapper;

//    @Scheduled(fixedRate = 10000)
    public void insertBatchMemberCount(){

        logger.info("##### insertBatchMemberCount start " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        reportServiceMapper.insertBatchMemberCount();
        logger.info("##### insertBatchMemberCount end " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }
}
