package edu.ncu.eims.service.impl;

import edu.ncu.eims.entity.Enterprise;
import edu.ncu.eims.repository.EnterpriseRepository;
import edu.ncu.eims.service.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author hemenghai
 * @date 2019-02-23
 */
@Service
public class EnterpriseServiceImpl implements EnterpriseService {

    @Autowired
    private EnterpriseRepository repository;

    @Override
    public Page<Enterprise> queryPage(Pageable pageable) {
        return repository.findAll(pageable);
    }
}
