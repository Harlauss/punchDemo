package com.harlaus.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class AddressVo {
    private String adcode;
    private String name;
    private String center;
    private String level;
    private List<AddressVo> districts;
}
