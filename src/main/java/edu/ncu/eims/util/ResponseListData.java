package edu.ncu.eims.util;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author hemenghai
 * @date 2019-02-24
 */
@Data
public class ResponseListData<T> {

    private Integer code;
    private String msg;
    private Long count;

    private List<T> data;


    public static <T> ResponseListData<T> of(Page<T> page){
        ResponseListData<T> response = new ResponseListData<>();

        response.setCode(0);
        response.setMsg("success");
        response.setCount(page.getTotalElements());
        response.setData(page.getContent());
        return response;
    }
}
