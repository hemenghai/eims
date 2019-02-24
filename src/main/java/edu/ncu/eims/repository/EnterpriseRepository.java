package edu.ncu.eims.repository;

import edu.ncu.eims.entity.Enterprise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * @author hemenghai
 * @date 2019-02-23
 */
@Repository
public interface EnterpriseRepository extends JpaRepository<Enterprise, String>, JpaSpecificationExecutor<Enterprise> {
}
