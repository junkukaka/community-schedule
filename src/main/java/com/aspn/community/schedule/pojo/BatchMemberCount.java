package com.aspn.community.schedule.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("batch_member_count")
public class BatchMemberCount {
    private String BaseDay;
    private Integer memberId;
    private Integer wikiCnt;
    private Integer communityCnt;
    private Integer commentCnt;
    private Integer likeCnt;
    private Integer beLikeCnt;
    private Date updateTime;
}
