package com.snow.model.sy;

import java.util.Date;

public class Syvrymbl {
    /** 流水号 */
    private String svmseqcod;

    /** 手机号 */
    private String svmmobile;

    /** 验证码 */
    private String svmvrycod;

    /** 已使用 */
    private String svmusebfr;

    /** 过期时间 */
    private Date svmexpire;

    public String getSvmseqcod() {
        return svmseqcod;
    }

    public void setSvmseqcod(String svmseqcod) {
        this.svmseqcod = svmseqcod;
    }

    public String getSvmmobile() {
        return svmmobile;
    }

    public void setSvmmobile(String svmmobile) {
        this.svmmobile = svmmobile;
    }

    public String getSvmvrycod() {
        return svmvrycod;
    }

    public void setSvmvrycod(String svmvrycod) {
        this.svmvrycod = svmvrycod;
    }

    public String getSvmusebfr() {
        return svmusebfr;
    }

    public void setSvmusebfr(String svmusebfr) {
        this.svmusebfr = svmusebfr;
    }

    public Date getSvmexpire() {
        return svmexpire;
    }

    public void setSvmexpire(Date svmexpire) {
        this.svmexpire = svmexpire;
    }
}