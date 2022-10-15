package com.harlaus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName(value = "sub_user")
public class SubUser {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String QQNumber;

    private String phone;

    @TableLogic(delval = "1",value = "0")
    private Boolean isSub;

}
