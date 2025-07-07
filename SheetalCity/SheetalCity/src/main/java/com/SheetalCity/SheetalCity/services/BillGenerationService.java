package com.SheetalCity.SheetalCity.services;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.dto.AdvancePaymentDTO;
import com.SheetalCity.SheetalCity.entity.HouseMapping;
import com.SheetalCity.SheetalCity.entity.LedgerDetails;
import com.SheetalCity.SheetalCity.entity.PaymentDetails;
import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.repositories.HouseMappingRepository;
import com.SheetalCity.SheetalCity.repositories.LedgerDetailsRepositories;
import com.SheetalCity.SheetalCity.repositories.PaymentDetailsRepository;
import com.SheetalCity.SheetalCity.repositories.UserDataRepository;

@Service
public class BillGenerationService {
	
	@Autowired
	LedgerDetailsRepositories ledgerDetailsRepositories;
	
	@Autowired
	HouseMappingRepository houseMappingRepository;
	
	@Autowired
	UserDataRepository userDataRepository;
	
	@Autowired
	UserDataService userDataService;
	
	@Autowired
	PaymentDetailsRepository paymentDetailsRepository;
	
	public void GenerateBillAutomatic() {
		
	}
	public void GenerateBillManually() {
		/*
		 * List all of the Cities 
		 * make a for loop for the cities 
		 * Take out the list of registered users for those cities 
		 * Make a loop of those users 
		 * take out the Monthy Maintenance 
		 * take out the monthey rs/Unit 
		 * multilpy maint by number of months (need to imporve this function but for now can take 1)
		 * multipy electric by 100 units per months (needs to improve by adding the details);
		 * with help of user take out the advance 
		 * with help of house take out the due 
		 * do the calculation advance-due-(calculated amunt)
		 * if it is positive mark bill status as paid
		 * and add remaining amount in advance update the advance 
		 * if it is negative then mark bill status as due
		 * share the calculation to the User
		 */
	}
	
	@Scheduled(cron = "0 34 01 * * ?")
	public void doSettelment() {
		System.out.println("Started Doing the Settelment");
		List<LedgerDetails> dueLedger = ledgerDetailsRepositories.findByPaymentStatus("DUE");
		Map<HouseMapping,List<LedgerDetails>> dueMap = dueLedger.stream().collect(Collectors.groupingBy(LedgerDetails::getHousemapping));
		dueMap.forEach((houseMapping,ledgerDetails)->{			
			ledgerDetails.forEach(ledger -> {
				if((houseMapping.getUserDetails().getAdvanceElectric() >= (double)ledger.getElectricityBill())&&
						(houseMapping.getUserDetails().getAdvanceMaintenance() >= (double)ledger.getMaintenanceBill())) {
					houseMapping.setDueElectric(houseMapping.getDueElectric() - (double)ledger.getElectricityBill());
					houseMapping.getUserDetails().setAdvanceElectric(houseMapping.getUserDetails().getAdvanceElectric()-(double)ledger.getElectricityBill());
					houseMapping.setDueMaintenance(houseMapping.getDueMaintenance() - (double)ledger.getMaintenanceBill());
					houseMapping.getUserDetails().setAdvanceMaintenance(houseMapping.getUserDetails().getAdvanceMaintenance()-(double)ledger.getMaintenanceBill());
				}
				long currentTimeMillies = System.currentTimeMillis();
				ledger.setSlipNo(ledger.getId()+"-"+currentTimeMillies+"-"+ledger.getHousemapping().getHouseNo());
				ledger.setPaymentStatus("PAID");
				ledger.setHousemapping(houseMapping);
				ledger.setUpdated_dt(new Date());
			});
		});
		List<LedgerDetails> alllLedgerListToSave = dueMap.values().stream().flatMap(List::stream).collect(Collectors.toList());
		List<HouseMapping> allHouseMappingstoSave = dueMap.values().stream().flatMap(List::stream).map(LedgerDetails::getHousemapping).collect(Collectors.toList());
		List<UserData> allUsersToSave = dueMap.values().stream().flatMap(List::stream).map(LedgerDetails::getUser).collect(Collectors.toList());
		ledgerDetailsRepositories.saveAll(alllLedgerListToSave);
		houseMappingRepository.saveAll(allHouseMappingstoSave);
		userDataRepository.saveAll(allUsersToSave);
		System.out.println("Settelment is Completed");
	}
	public void generatePaymentBill(AdvancePaymentDTO advanePayment) {
		PaymentDetails payment = new PaymentDetails();
		payment.setElectricPayment(advanePayment.getAdvanceElectricPayment());
		payment.setMainteancePaument(advanePayment.getAdvanceMaintenancePayment());
		payment.setTotalPayment(advanePayment.getAdvanceElectricPayment()+advanePayment.getAdvanceMaintenancePayment());
		payment.setUser(userDataService.updateAdvancePayment(advanePayment));
		paymentDetailsRepository.save(payment);
	}
}
