package com.harlaus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName punch_record
 */
@TableName(value ="punch_record")
@Data
@Accessors(chain = true)
public class PunchRecord implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 学号
     */
    private String studentId;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private String sex;

    /**
     * 打卡时间
     */

    private Date punchTime;

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

    /**
     * 具体地址
     */
    private String dwaddress;

    /**
     * 省份
     */
    private String wxprovince;

    /**
     * 城市
     */
    private String wxcity;

    /**
     * 打卡状态
     */
    private Boolean state;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PunchRecord other = (PunchRecord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getPunchTime() == null ? other.getPunchTime() == null : this.getPunchTime().equals(other.getPunchTime()))
            && (this.getTiwen() == null ? other.getTiwen() == null : this.getTiwen().equals(other.getTiwen()))
            && (this.getTiwen2() == null ? other.getTiwen2() == null : this.getTiwen2().equals(other.getTiwen2()))
            && (this.getTiwen3() == null ? other.getTiwen3() == null : this.getTiwen3().equals(other.getTiwen3()))
            && (this.getDwaddress() == null ? other.getDwaddress() == null : this.getDwaddress().equals(other.getDwaddress()))
            && (this.getWxprovince() == null ? other.getWxprovince() == null : this.getWxprovince().equals(other.getWxprovince()))
            && (this.getWxcity() == null ? other.getWxcity() == null : this.getWxcity().equals(other.getWxcity()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getPunchTime() == null) ? 0 : getPunchTime().hashCode());
        result = prime * result + ((getTiwen() == null) ? 0 : getTiwen().hashCode());
        result = prime * result + ((getTiwen2() == null) ? 0 : getTiwen2().hashCode());
        result = prime * result + ((getTiwen3() == null) ? 0 : getTiwen3().hashCode());
        result = prime * result + ((getDwaddress() == null) ? 0 : getDwaddress().hashCode());
        result = prime * result + ((getWxprovince() == null) ? 0 : getWxprovince().hashCode());
        result = prime * result + ((getWxcity() == null) ? 0 : getWxcity().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", studentId=").append(studentId);
        sb.append(", name=").append(name);
        sb.append(", sex=").append(sex);
        sb.append(", punchTime=").append(punchTime);
        sb.append(", tiwen=").append(tiwen);
        sb.append(", tiwen2=").append(tiwen2);
        sb.append(", tiwen3=").append(tiwen3);
        sb.append(", dwaddress=").append(dwaddress);
        sb.append(", wxprovince=").append(wxprovince);
        sb.append(", wxcity=").append(wxcity);
        sb.append(", state=").append(state);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}