package com.avalon.eva.vo;

public class Json {
    
    private boolean success;
    private String msg;
    private Object obj;
    
    public Json(){  
    }
    
    public Json(boolean success, String msg, Object obj){
        this.success=success;
        this.msg=msg;
        this.obj=obj;
    }
    
    public Json(boolean success, String msg){
        this.success=success;
        this.msg=msg;
    }
    
    public boolean isSuccess() {
        return success;
    }
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getObj() {
        return obj;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }
    
    
}
