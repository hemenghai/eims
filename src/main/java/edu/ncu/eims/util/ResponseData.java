package edu.ncu.eims.util;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author hemenghai
 * @date 2019-02-24
 */
@Data
public class ResponseData<T> {

    private Integer code;
    private String msg;
    private Long count;

    private List<T> data;


    public static <T> ResponseData<T> of(Page<T> page){
        ResponseData<T> data = new ResponseData<>();

        data.setCode(0);
        data.setMsg("success");
        data.setCount(page.getTotalElements());
        data.setData(page.getContent());
        return data;
    }
}
