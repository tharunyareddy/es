/**
 *
 */
package com.easyservice.controller;

/**
 * @author TharunyaREDDY
 *
 */
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easyservice.model.Contract;
import com.easyservice.model.Maintenance;
import com.easyservice.model.Priority;
import com.easyservice.model.Status;
import com.easyservice.service.IContractorService;

@RestController
@RequestMapping("/contractor-service")
//@CrossOrigin("http://localhost:4200")
public class ContractorController {

	@Autowired
	IContractorService contractorService;

	// *************************************Contractor*********************************************

	// http://localhost:8070/contractor-service/contracts
	@PostMapping("/contracts")
	public Contract addContractor(@RequestBody Contract contractor) {
		return contractorService.addContractor(contractor);
	}

	// http://localhost:8070/contractor-service/contracts
	@PutMapping("/contracts")
	public void updateContractor(@RequestBody Contract contractor) {
		contractorService.updateContractor(contractor);
	}

	// http://localhost:8070/contractor-service/contracts/contractId/17
	@DeleteMapping("/contracts/contractId/{contractId}")
	public void deleteContractor(@PathVariable("contractId") int contractId) {
		contractorService.deleteContractor(contractId);
	}

	// http://localhost:8070/contractor-service/contracts
	@GetMapping("/contracts")
	ResponseEntity<List<Contract>> getAll() {
		List<Contract> allContracts = contractorService.getAllContracts();
		return ResponseEntity.ok().body(allContracts);
	}

	// http://localhost:8070/contractor-service/contracts/contractById/17
	@GetMapping("/contracts/contractById/{contractId}")
	ResponseEntity<Contract> getByContractId(@PathVariable("contractId") int contractId) {
		Contract contractById = contractorService.getByContractId(contractId);
		return ResponseEntity.ok().body(contractById);
	}

	// http://localhost:8070/contractor-service/contracts/status/INPROGRESS
	@GetMapping("/contracts/status/{cStatus}")
	ResponseEntity<List<Contract>> getByContractStatus(@PathVariable("cStatus") Status cStatus) {
		List<Contract> contractsList = contractorService.getByContractStatus(cStatus);
		return ResponseEntity.ok().body(contractsList);
	}

	// http://localhost:8070/contractor-service/contracts/priority/LOW
	@GetMapping("/contracts/priority/{cPriority}")
	ResponseEntity<List<Contract>> getByContractPriority(@PathVariable("cPriority") Priority cPriority) {
		List<Contract> contractsList = contractorService.getByContractPriority(cPriority);
		return ResponseEntity.ok().body(contractsList);
	}

	// http://localhost:8070/contractor-service/contracts/IBIS
	@GetMapping("/contracts/{contractName}")
	ResponseEntity<Contract> getByContractName(@PathVariable("contractName") String contractName) {
		Contract contractByName = contractorService.getByContractName(contractName);
		return ResponseEntity.ok().body(contractByName);
	}

	// http://localhost:8070/contractor-service/contracts/maintenance/1
	@GetMapping("/contracts/{maintenanceId}/{contractId}")
	String assignMaintenance(@PathVariable("maintenanceId") int maintenanceId,@PathVariable("contractId") int contractId) {
		contractorService.assignMaintenance(maintenanceId, contractId);
		return "project assigned";
	}

	// http://localhost:8070/contractor-service/contracts/maintenance
	@PutMapping("/contracts/maintenance")
	ResponseEntity<String> unAssignMaintenance(@RequestBody Maintenance maintenance) {
		contractorService.unAssignMaintenance(maintenance);
		ResponseEntity<String> maintenanceResponse = new ResponseEntity<String>("Project Un-Assigned", HttpStatus.OK);
		return maintenanceResponse;
	}

	// http://localhost:8070/contractor-service/contracts/maintenance/maintenanceId/1
	@GetMapping("/contracts/maintenance/maintenanceId/{maintenanceId}")
	ResponseEntity<Maintenance> getByMaintenanceId(@PathVariable("maintenanceId") int maintenanceId) {
		Maintenance maintenanceById = contractorService.getByMaintenanceId(maintenanceId);
		return ResponseEntity.ok().body(maintenanceById);
	}

	// http://localhost:8070/contractor-service/contracts/maintenance/cId/1
	@GetMapping("/contracts/maintenance/cId/{contractId}")
	ResponseEntity<List<Maintenance>> getMaintenanceByContractId(@PathVariable("contractId") int contractId) {
		List<Maintenance> maintenanceById = contractorService.getMaintenanceByContractId(contractId);
		return ResponseEntity.ok().body(maintenanceById);
	}



	// http://localhost:8070/contractor-service/contracts/maintenanceStatus/ONHOLD
	@GetMapping("/contracts/maintenanceStatus/{mStatus}")
	ResponseEntity<List<Maintenance>> getByMaintenanceStatus(@PathVariable("mStatus") Status mStatus) {
		List<Maintenance> maintenanceList = contractorService.getByMaintenanceStatus(mStatus);
		return ResponseEntity.accepted().body(maintenanceList);
	}

	// http://localhost:8070/contractor-service/contracts/maintenancePriority/HIGH
	@GetMapping("/contracts/maintenancePriority/{mPriority}")
	ResponseEntity<List<Maintenance>> getByMaintenancePriority(@PathVariable("mPriority") Priority mPriority) {
		List<Maintenance> maintenanceList = contractorService.getByMaintenancePriority(mPriority);
		return ResponseEntity.accepted().body(maintenanceList);
	}


	// http://localhost:8070/contractor-service/contracts/maintenance
	@GetMapping("contracts/maintenance")
	ResponseEntity<List<Maintenance>> getAllMaintenance() {
		List<Maintenance> maintenanceList = contractorService.getAllMaintenance();
		return ResponseEntity.accepted().body(maintenanceList);
	}

}
