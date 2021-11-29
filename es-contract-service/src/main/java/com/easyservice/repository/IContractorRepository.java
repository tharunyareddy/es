/**
 *
 */
package com.easyservice.repository;

/**
 * @author TharunyaREDDY
 *
 */
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.easyservice.exception.MaintenanceNotFoundException;
import com.easyservice.model.Contract;
import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;

@Repository
public interface IContractorRepository extends JpaRepository<Contract, Integer> {

	// contractor


	Contract findByContractName(String contractName);

	@Query("from Contract c where c.cStatus=?1")
	List<Contract> findByStatus(Status cStatus);

	@Query("from Contract c where c.cPriority=?1")
	List<Contract> findByPriority(Priority cPriority);

	@Query("from Maintenance m inner join m.contractor c where c.contractId=?1")
	List<Maintenance> findMaintenanceByContractId(int contractId) throws MaintenanceNotFoundException;
}
