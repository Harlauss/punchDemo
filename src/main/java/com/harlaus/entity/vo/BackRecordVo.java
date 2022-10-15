package com.harlaus.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BackRecordVo {

    private String name;

    /**
     * 打卡时间
     */
    private String punchTime;

    /**
     * 具体地址
     */
    private String dwaddress;

    /**
     * 打卡状态
     */
    private String state;

    /**
     * 体温
     */
    private String tiwen;

    /**
     * 体温
     */
    private String tiwen2;

    /**
     * 体温
     */
    private String tiwen3;
}
