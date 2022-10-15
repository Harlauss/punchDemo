package com.harlaus.service;

import com.harlaus.entity.CommonResult;
import com.harlaus.entity.PunchRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.harlaus.entity.vo.SearchRecordVo;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

/**
* @author 23548
* @description 针对表【punch_record】的数据库操作Service
* @createDate 2022-09-25 22:53:03
*/
public interface PunchRecordService extends IService<PunchRecord> {
    public PunchRecord punchResult(Map<String,String> cookieMap) throws IOException, ParseException;
    public CommonResult doDefaultPunchRecord(String id);
    public CommonResult doPunchRecord(SearchRecordVo searchRecordVo);
}
