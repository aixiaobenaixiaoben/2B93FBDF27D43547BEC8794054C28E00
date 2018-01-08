package com.snow.model.sy;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.snow.main.BaseModel;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Syusrinf extends BaseModel {
    /** 流水号 */
    private String suiseqcod;

    /** 用户名 */
    private String suiusrnam;

    /** MD5密码 */
    private String suipaswrd;

    /** 手机号码 */
    private String suimobile;

    /** 版本 */
    @JsonFormat(pattern = TIME_PATTERN, timezone = TIMEZONE)
    @DateTimeFormat(pattern = TIME_PATTERN)
    private Date suiverson;

    public String getSuiseqcod() {
        return suiseqcod;
    }

    public void setSuiseqcod(String suiseqcod) {
        this.suiseqcod = suiseqcod;
    }

    public String getSuiusrnam() {
        return suiusrnam;
    }

    public void setSuiusrnam(String suiusrnam) {
        this.suiusrnam = suiusrnam;
    }

    public String getSuipaswrd() {
        return suipaswrd;
    }

    public void setSuipaswrd(String suipaswrd) {
        this.suipaswrd = suipaswrd;
    }

    public String getSuimobile() {
        return suimobile;
    }

    public void setSuimobile(String suimobile) {
        this.suimobile = suimobile;
    }

    public Date getSuiverson() {
        return suiverson;
    }

    public void setSuiverson(Date suiverson) {
        this.suiverson = suiverson;
    }
}