package com.harlaus.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchRecordVo {
    String id;
    String startTime;
    String endTime;
    Long currIndex;
    Long size;
}
