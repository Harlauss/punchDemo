package com.harlaus.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harlaus.entity.PunchRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.harlaus.entity.vo.BackRecordVo;
import com.harlaus.entity.vo.SearchRecordVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author 23548
* @description 针对表【punch_record】的数据库操作Mapper
* @createDate 2022-09-25 22:53:03
* @Entity com.harlaus.entity.PunchRecord
*/
public interface PunchRecordMapper extends BaseMapper<PunchRecord> {
    IPage<BackRecordVo> queryPunchRecordByStuId(Page<BackRecordVo> page, @Param("searchRecordVo") SearchRecordVo searchRecordVo);
}




