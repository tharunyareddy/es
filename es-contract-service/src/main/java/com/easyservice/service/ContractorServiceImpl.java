/**
 *
 */
package com.easyservice.service;

/**
 * @author TharunyaREDDY
 *
 */
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.easyservice.exception.ContractNotFoundException;
import com.easyservice.exception.MaintenanceNotFoundException;
import com.easyservice.model.Contract;
import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;
import com.easyservice.repository.IContractorRepository;

@Service
public class ContractorServiceImpl implements IContractorService {

	RestTemplate restTemplate;

	@Autowired
	public void setRestTemplate(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	private static final String BASEURL = "http://localhost:8071/maintenance-service/maintenance";

	@Autowired
	IContractorRepository contractorRepository;

	// ********************************************Contractor**************************************

	// To Add the Contract
	@Override
	public Contract addContractor(Contract contractor) {
		return contractorRepository.save(contractor);
	}

	// To Update the Contract Details
	@Override
	public String updateContractor(Contract contractor) {
		contractorRepository.save(contractor);
		return "Contract Updated Successfully";
	}

	// To Delete the Contract By Contract Id
	@Override
	public String deleteContractor(int contractId) throws ContractNotFoundException {
		Contract contractor = contractorRepository.findById(contractId).get();
		if (contractor == null) {
			throw new ContractNotFoundException("Contract Id Not Found,Invalid ContractId");
		}
		contractorRepository.deleteById(contractId);
		return "Contract Deleted Succesfully";
	}

	// Retrieving Contract By Contract Id
	@Override
	public Contract getByContractId(int contractId) throws ContractNotFoundException {

		Contract contract = contractorRepository.findById(contractId).get();
		if (contract == null) {
			throw new ContractNotFoundException("Contract Not Found,Invalid ContractId");
		}
		return contract;
	}

	// Retrieving All Contracts
	@Override
	public List<Contract> getAllContracts() {
		List<Contract> allContracts = contractorRepository.findAll();
		return allContracts;

	}

	// Retrieving Contract By Contract Name
	@Override
	public Contract getByContractName(String contractName) throws ContractNotFoundException {

		Contract contractByName = contractorRepository.findByContractName(contractName);
		if (contractByName == null) {
			throw new ContractNotFoundException("Contract Name Not Found");
		}
		return contractByName;
	}

	// Retrieving Contracts By Maintenance
	@Override
	public List<Contract> getByContractStatus(Status cStatus) throws ContractNotFoundException {
		List<Contract> contractByStatus = contractorRepository.findByStatus(cStatus);
		if (contractByStatus.isEmpty()) {
			throw new ContractNotFoundException("Contract Not Found,Invalid Dates");
		}
		return contractByStatus;
	}

	@Override
	public List<Contract> getByContractPriority(Priority cPriority) throws ContractNotFoundException {
		List<Contract> contractByPriority = contractorRepository.findByPriority(cPriority);
		if (contractByPriority.isEmpty()) {
			throw new ContractNotFoundException("Contract Not Found,Invalid Dates");
		}
		return contractByPriority;
	}

	// ***************************************Maintenance***************************************

	@Override
	public Maintenance getByMaintenanceId(int maintenanceId) throws MaintenanceNotFoundException {
		String url = BASEURL + '/' + maintenanceId;
		Maintenance maintenance = restTemplate.getForEntity(url, Maintenance.class).getBody();
		if (maintenance == null) {
			throw new MaintenanceNotFoundException("Maintenance Not Found,Invalid Maintenance Id");
		}
		return maintenance;
	}

	// Assigning Maintenance to the Contract
	@Override
	public String assignMaintenance(int maintenanceId, int contractId) {
		Contract contractor = getByContractId(contractId);
		Maintenance maintenance = getByMaintenanceId(maintenanceId);
		System.out.println(contractor);
		System.out.println(maintenance);
		maintenance.setContractor(contractor);
		System.out.println(contractor);
		System.out.println(maintenance);
		return "assigned";
	}

	// Un-Assigning the maintenance to the contract
	@Override
	public void unAssignMaintenance(Maintenance maintenance) {

		String url = BASEURL;
		restTemplate.put(url, maintenance);
	}

	// Retrieving All Maintenances
	@Override
	public List<Maintenance> getAllMaintenance() {
		String url = BASEURL;
		ResponseEntity<List> maintenanceList = restTemplate.getForEntity(url, List.class);
		return maintenanceList.getBody();
	}

	// Retrieving Maintenance By Maintenance Status
	@Override
	public List<Maintenance> getByMaintenanceStatus(Status mStatus) throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenanceStatus/" + mStatus;
		ResponseEntity<List> maintenanceList = restTemplate.getForEntity(url, List.class);
		if (maintenanceList.getBody().isEmpty()) {
			throw new MaintenanceNotFoundException("Maintenance Not Found");
		}
		return maintenanceList.getBody();
	}

	// Retrieving Maintenance By Maintenance Priority
	@Override
	public List<Maintenance> getByMaintenancePriority(Priority mPriority) throws MaintenanceNotFoundException {
		String url = BASEURL + "/maintenancePriority/" + mPriority;
		ResponseEntity<List> maintenanceList = restTemplate.getForEntity(url, List.class);
		if (maintenanceList.getBody().isEmpty()) {
			throw new MaintenanceNotFoundException("Maintenance Not Found");
		}
		return maintenanceList.getBody();
	}

	@Override
	public List<Maintenance> getMaintenanceByContractId(int contractId) throws MaintenanceNotFoundException {
		return contractorRepository.findMaintenanceByContractId(contractId);

	}

}
