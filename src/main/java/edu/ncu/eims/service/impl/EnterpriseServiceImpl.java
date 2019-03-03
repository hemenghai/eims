package edu.ncu.eims.service.impl;

import edu.ncu.eims.entity.Enterprise;
import edu.ncu.eims.repository.EnterpriseRepository;
import edu.ncu.eims.service.EnterpriseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.util.LinkedList;
import java.util.List;

/**
 * @author hemenghai
 * @date 2019-02-23
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseRepository repository;

    @Override
    public Page<Enterprise> queryPage(String name, String scale, String category, String chain, Pageable pageable) {
        Specification<Enterprise> specification = (Specification<Enterprise>) (root, cq, cb) -> {
            List<Predicate> predicateList = new LinkedList<>();
            if(StringUtils.isNotEmpty(name)){
                predicateList.add(cb.like(root.get(Enterprise.ENTERPRISE_NAME), "%"+name+"%"));
            }
            if(StringUtils.isNotEmpty(scale)){
                predicateList.add(cb.like(root.get(Enterprise.ENTERPRISE_SCALE), "%"+scale+"%"));
            }
            if(StringUtils.isNotEmpty(category)){
                predicateList.add(cb.like(root.get(Enterprise.INDUSTRY_CATEGORY), "%"+category+"%"));
            }
            if(StringUtils.isNotEmpty(chain)){
                predicateList.add(cb.like(root.get(Enterprise.INDUSTRY_CHAIN), "%"+chain+"%"));
            }
            return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
        };
        return repository.findAll(specification, pageable);
    }

    @Override
    public List<Enterprise> getAll() {
        return repository.findAll();
    }
}
