package com.aspn.community.schedule.report;

import com.aspn.community.schedule.pojo.BatchMemberCount;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportServiceMapper extends BaseMapper<BatchMemberCount> {
    void insertBatchMemberCount();
}
