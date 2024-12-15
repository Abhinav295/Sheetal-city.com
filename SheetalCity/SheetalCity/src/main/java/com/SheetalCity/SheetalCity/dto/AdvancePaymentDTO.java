package com.SheetalCity.SheetalCity.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class AdvancePaymentDTO {
	int advanceElectricPayment;
	int advanceMaintenancePayment;
	int userId;
}
