package com.xunc.common.returnbody;

import com.alibaba.fastjson.JSONObject;

/**
 * 数据返回实体
 * @author 魏子超
 */
public class ResultInfo {

    private String message;

    private Integer code;

    private Object result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public ResultInfo(String message, Integer code, Object result) {
        this.message = message;
        this.code = code;
        this.result = result;
    }

    public ResultInfo(){
    }


    public JSONObject jsonResult(){
        return JSONObject.parseObject(JSONObject.toJSONString(this.result));
    }

    public static ResultInfo parse(String resultInfo){
        return JSONObject.parseObject(resultInfo, ResultInfo.class);
    }

}
