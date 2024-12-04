package com.SheetalCity.SheetalCity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.SheetalCity.SheetalCity.repositories.LedgerDetailsRepositories;

@Service

public class BillGenerationService {
	
	@Autowired
	LedgerDetailsRepositories ledgerDetailsRepositories;
	
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
}
