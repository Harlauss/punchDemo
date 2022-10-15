package com.harlaus.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.harlaus.entity.CommonResult;
import com.harlaus.entity.vo.BackRecordVo;
import com.harlaus.entity.vo.SearchRecordVo;
import com.harlaus.service.PunchRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/record")
public class PunchRecordController {

    @Autowired
    private PunchRecordService punchRecordService;

    @GetMapping("/default/{id}")
    public CommonResult defaultPunchRecord(@PathVariable String id){
        return punchRecordService.doDefaultPunchRecord(id);
    }

    @PostMapping("/search")
    public CommonResult punchRecord(@RequestBody SearchRecordVo searchRecordVo){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date start = null;
        Date end = null;
        try {
            if(searchRecordVo.getStartTime() == null || searchRecordVo.getEndTime() == null){
                return new CommonResult(444,"日期输入错误",null);
            }
            start = simpleDateFormat.parse(searchRecordVo.getStartTime());
            end = simpleDateFormat.parse(searchRecordVo.getEndTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if(start == null || end == null){
            return new CommonResult(444,"日期输入错误",null);
        }
        return punchRecordService.doPunchRecord(searchRecordVo);
    }



}
