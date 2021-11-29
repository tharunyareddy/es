/**
 *
 */
package com.easyservice.service;

/**
 * @author TharunyaREDDY
 *
 */
import java.time.LocalDate;
import java.util.List;
import com.easyservice.exception.ContractNotFoundException;
import com.easyservice.exception.MaintenanceNotFoundException;
import com.easyservice.model.Contract;
import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;

public interface IContractorService {

	// **********************************Contractor*******************************************

	Contract addContractor(Contract contractor);

	String updateContractor(Contract contractor);

	String deleteContractor(int contractId) throws ContractNotFoundException;

	List<Contract> getAllContracts();

	Contract getByContractId(int contractId) throws ContractNotFoundException;

	// *************************************Maintenance*******************************************

	String assignMaintenance(int maintenanceId, int contractId);

	void unAssignMaintenance(Maintenance maintenance);

	Maintenance getByMaintenanceId(int maintenanceId) throws MaintenanceNotFoundException;

	List<Maintenance> getAllMaintenance();
	
	List<Maintenance> getMaintenanceByContractId(int contractId) throws MaintenanceNotFoundException;
	// ******************************************************************************************//

	Contract getByContractName(String contractName) throws ContractNotFoundException;
	
	List<Contract> getByContractStatus(Status cStatus) throws ContractNotFoundException;
	
	List<Contract> getByContractPriority(Priority cPriority) throws ContractNotFoundException;

	List<Maintenance> getByMaintenanceStatus(Status mStatus) throws MaintenanceNotFoundException;

	List<Maintenance> getByMaintenancePriority(Priority mPriority) throws MaintenanceNotFoundException;


}
