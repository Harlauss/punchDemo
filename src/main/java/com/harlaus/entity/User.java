package com.harlaus.entity;

import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName user
 */
@TableName(value ="user")
@Data
@Accessors(chain = true)
public class User implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 
     */
    private String phone;

    /**
     * 
     */
    private String password;

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

    @TableLogic(value = "0",delval = "1")
    private Boolean isDel;

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
        User other = (User) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getTiwen() == null ? other.getTiwen() == null : this.getTiwen().equals(other.getTiwen()))
            && (this.getTiwen2() == null ? other.getTiwen2() == null : this.getTiwen2().equals(other.getTiwen2()))
            && (this.getTiwen3() == null ? other.getTiwen3() == null : this.getTiwen3().equals(other.getTiwen3()))
            && (this.getDwaddress() == null ? other.getDwaddress() == null : this.getDwaddress().equals(other.getDwaddress()))
            && (this.getWxprovince() == null ? other.getWxprovince() == null : this.getWxprovince().equals(other.getWxprovince()))
            && (this.getWxcity() == null ? other.getWxcity() == null : this.getWxcity().equals(other.getWxcity()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getTiwen() == null) ? 0 : getTiwen().hashCode());
        result = prime * result + ((getTiwen2() == null) ? 0 : getTiwen2().hashCode());
        result = prime * result + ((getTiwen3() == null) ? 0 : getTiwen3().hashCode());
        result = prime * result + ((getDwaddress() == null) ? 0 : getDwaddress().hashCode());
        result = prime * result + ((getWxprovince() == null) ? 0 : getWxprovince().hashCode());
        result = prime * result + ((getWxcity() == null) ? 0 : getWxcity().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", password=").append(password);
        sb.append(", tiwen=").append(tiwen);
        sb.append(", tiwen2=").append(tiwen2);
        sb.append(", tiwen3=").append(tiwen3);
        sb.append(", dwaddress=").append(dwaddress);
        sb.append(", wxprovince=").append(wxprovince);
        sb.append(", wxcity=").append(wxcity);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}