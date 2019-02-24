package edu.ncu.eims.service;

import edu.ncu.eims.entity.Enterprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author hemenghai
 * @date 2019-02-23
 */
public interface EnterpriseService {

    /**
     * 分页查询
     *
     * @param name
     * @param scale
     * @param category
     * @param chain
     * @param pageable 页面信息
     * @return 企业信息页面
     */
    Page<Enterprise> queryPage(String name, String scale, String category, String chain, Pageable pageable);
}
