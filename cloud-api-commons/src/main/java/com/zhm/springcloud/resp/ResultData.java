package com.zhm.springcloud.resp;

import lombok.Data;
import lombok.experimental.Accessors;

@Data  //内置了setter  getter
@Accessors(chain = true)
public class ResultData <T>{
    private String code;
    private String message;
    private T data;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }
    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<T>();
        resultData.setCode(ReturnCodeEnum.RC200.getCode());
        resultData.setMessage(ReturnCodeEnum.RC200.getMessage());
        resultData.setData(data);
        return resultData;
    }
    public static <T> ResultData<T> fail(String code,String message) {
        ResultData<T> resultData = new ResultData<T>();
        resultData.setCode(code);
        resultData.setMessage(message);
        resultData.setData(null);
        return resultData;
    }
}
