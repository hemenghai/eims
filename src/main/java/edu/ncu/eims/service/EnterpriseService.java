package edu.ncu.eims.service;

import edu.ncu.eims.entity.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author hemenghai
 * @date 2019-02-23
 */
public interface EnterpriseService {

    /**
     * 多条件分页查询
     *
     * @param name
     * @param scale
     * @param category
     * @param chain
     * @param pageable 页面信息
     * @return 一页企业信息
     */
    Page<Enterprise> queryPage(String name, String scale, String category, String chain, Pageable pageable);

    /**
     * 获取所有
     * @return 所有企业信息
     */
    List<Enterprise> getAll();


    void add(Enterprise enterprise);

    void addList(List<Enterprise> enterprises);
}
