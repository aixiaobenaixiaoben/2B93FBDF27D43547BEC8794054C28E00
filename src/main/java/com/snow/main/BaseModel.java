package com.snow.main;

import java.util.Date;

public class BaseModel {

    protected static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    protected static final String TIMEZONE = "GMT+08:00";

    /** 旧版本 */
    private Date oldverson;

    public Date getOldverson() {
        return oldverson;
    }

    public void setOldverson(Date oldverson) {
        this.oldverson = oldverson;
    }
}