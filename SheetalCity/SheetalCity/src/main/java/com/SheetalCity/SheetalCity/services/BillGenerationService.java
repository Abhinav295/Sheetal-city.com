package com.SheetalCity.SheetalCity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.dto.AdvancePaymentDTO;
import com.SheetalCity.SheetalCity.entity.PaymentDetails;
import com.SheetalCity.SheetalCity.entity.UserData;
import com.SheetalCity.SheetalCity.repositories.LedgerDetailsRepositories;
import com.SheetalCity.SheetalCity.repositories.PaymentDetailsRepository;
import com.SheetalCity.SheetalCity.repositories.UserDataRepository;

@Service
public class BillGenerationService {
	
	@Autowired
	LedgerDetailsRepositories ledgerDetailsRepositories;
	
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
	public void GenerateBillManuallyForSpecificUser() {
		
	}
	public String getSlipNo() {
		return "";
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
