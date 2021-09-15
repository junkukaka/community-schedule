package com.aspn.community.schedule.mail.service;

import com.aspn.community.schedule.mail.mapper.ScheduleMailMapper;
import com.aspn.community.schedule.pojo.ScheduleMail;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.List;

@Component
@Slf4j
public class MailService {

    @Resource
    private JavaMailSender javaMailSender;

    @Resource
    ScheduleMailMapper scheduleMailMapper;

    @Value("${spring.mail.username}")
    private String from;

    //10分钟一次执行
    @Scheduled(fixedRate = 1000*60*10)
    public void run(){
        this.sendMail();
    }

    /**
     * @Author nanguangjun
     * @Description // 메일 발송
     * @Date 10:59 2021/9/14
     * @Param []
     * @return void
     **/
    public void sendMail(){
        ScheduleMail scheduleMail;
        List<ScheduleMail> scheduleMails = scheduleMailMapper.selectList(new QueryWrapper<ScheduleMail>().isNull("success"));
        for (int i = 0; i < scheduleMails.size(); i++) {
            scheduleMail = scheduleMails.get(i);
            log.info("############ " + scheduleMail.getToMail());
            //发送邮件
            this.sendHtmlMail(scheduleMail);
        }
    }

    /**
     * @Author nanguangjun
     * @Description // 发送邮件
     * @Date 10:29 2021/9/14
     * @Param [to, subject, content]
     * @return void
     **/
    public void sendHtmlMail(ScheduleMail scheduleMail) {
        MimeMessage mimeMailMessage = javaMailSender.createMimeMessage();
        try {
            scheduleMail.setStartTime(new Date());
            scheduleMailMapper.updateById(scheduleMail);

            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMailMessage, true);
            mimeMessageHelper.setTo(scheduleMail.getToMail());
            mimeMessageHelper.setSubject(scheduleMail.getTitle());
            mimeMessageHelper.setText(scheduleMail.getContent(), true);
            mimeMessageHelper.setFrom(from);
            javaMailSender.send(mimeMailMessage);//发送邮件

            scheduleMail.setEndTime(new Date());
            scheduleMail.setSuccess(1);
            scheduleMailMapper.updateById(scheduleMail);
        } catch (Exception e) {
            log.info("######### getMessage" + e.getMessage());
            scheduleMail.setSuccess(0);
            if(e.getMessage().length() > 500){
                scheduleMail.setMsg(e.getMessage().substring(0,499));
            }else {
                scheduleMail.setMsg(e.getMessage());
            }
            scheduleMailMapper.updateById(scheduleMail);
            e.printStackTrace();
        }
    }


}
