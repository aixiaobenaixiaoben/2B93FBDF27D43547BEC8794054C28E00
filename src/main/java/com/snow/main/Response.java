package com.snow.main;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Description: TODO.
 * User: kevin
 * Date: 2018/1/7
 * Time: 下午3:04
 */
public class Response {

    private static final String SUC = "SUC";
    private static final String ERR = "ERR";

    public static final String NOT_FOUND_EXCEPTION_CODE = "404";
    public static final String NOT_FOUND_EXCEPTION_MSG = "NOT FOUND";
    public static final String UNAUTHORIZED_EXCEPTION_CODE = "401";
    public static final String UNAUTHORIZED_EXCEPTION_MSG = "LOGIN NECESSARY";
    public static final String DUPLICATE_OPERATION_EXCEPTION_CODE = "442";
    public static final String DUPLICATE_OPERATION_EXCEPTION_MSG = "RECORD CHANGED BEFORE";
    public static final String SYSTEM_EXCEPTION_CODE = "443";
    public static final String SYSTEM_EXCEPTION_MSG = "SYSTEM EXCEPTION";
    public static final String UNKNOWN_EXCEPTION_CODE = "444";
    public static final String UNKNOWN_EXCEPTION_MSG = "UNKNOWN EXCEPTION";

    private static Response response = new Response();

    /** 结果码 */
    @JsonProperty
    private String RTNCOD;

    /** 成功数据 */
    @JsonProperty
    private Object RTNDTA;

    /** 错误码 */
    @JsonProperty
    private String ERRCOD;

    /** 错误信息 */
    @JsonProperty
    private String ERRMSG;

    public static Response SUCCESS() {
        response.RTNCOD = SUC;
        response.RTNDTA = null;
        response.ERRCOD = null;
        response.ERRMSG = null;
        return response;
    }

    public static Response SUCCESS(Object data) {
        response.RTNCOD = SUC;
        response.RTNDTA = data;
        response.ERRCOD = null;
        response.ERRMSG = null;
        return response;
    }

    public static Response ERROR() {
        response.RTNCOD = ERR;
        response.RTNDTA = null;
        response.ERRCOD = null;
        response.ERRMSG = null;
        return response;
    }

    public static Response ERROR(String message) {
        response.RTNCOD = ERR;
        response.RTNDTA = null;
        response.ERRCOD = null;
        response.ERRMSG = message;
        return response;
    }

    public static Response ERROR(String code, String message) {
        response.RTNCOD = ERR;
        response.RTNDTA = null;
        response.ERRCOD = code;
        response.ERRMSG = message;
        return response;
    }



    @JsonIgnore
    public String getRTNCOD() {
        return RTNCOD;
    }

    public void setRTNCOD(String RTNCOD) {
        this.RTNCOD = RTNCOD;
    }

    @JsonIgnore
    public Object getRTNDTA() {
        return RTNDTA;
    }

    public void setRTNDTA(Object RTNDTA) {
        this.RTNDTA = RTNDTA;
    }

    @JsonIgnore
    public String getERRCOD() {
        return ERRCOD;
    }

    public void setERRCOD(String ERRCOD) {
        this.ERRCOD = ERRCOD;
    }

    @JsonIgnore
    public String getERRMSG() {
        return ERRMSG;
    }

    public void setERRMSG(String ERRMSG) {
        this.ERRMSG = ERRMSG;
    }
}
