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

    public static ResponseData<Void> ok(){
        ResponseData<Void> response = new ResponseData<>();
        response.setCode(0);
        response.setMsg("success");
        return response;
    }

    public static <T> ResponseData<T> ok(T data){
        ResponseData<T> response = new ResponseData<>();
        response.setCode(0);
        response.setMsg("success");
        response.setData(data);
        return response;
    }

    public static <T extends Throwable> ResponseData<Void> error(T exception){
        ResponseData<Void> response = new ResponseData<>();
        response.setCode(1);
        response.setMsg(exception.getMessage());
        return response;
    }
}
