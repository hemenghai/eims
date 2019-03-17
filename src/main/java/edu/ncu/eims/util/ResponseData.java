package edu.ncu.eims.util;

import lombok.Data;

/**
 * @author hemenghai
 * @date 2019-03-17
 */
@Data
public class ResponseData<T> {

    private Integer code;
    private String msg;

    private T data;

    public static <T> ResponseData<T> ok(T data){
        ResponseData response = new ResponseData();
        response.setCode(0);
        response.setMsg("success");
        response.setData(data);
        return response;
    }
}
