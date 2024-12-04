package com.SheetalCity.SheetalCity.entity;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "ledger_info")
public class LedgerDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotNull
	private String slipNo;
	
	private String paymentStatus;
	
	@NotNull
	private double maintenanceBill;
	
	@NotNull
	private double electricityBill;
	
	@NotNull
	private double dueMaintenance;
	
	@NotNull
	private double dueElectric;
	
	@NotNull
	private double advanceElectric;
	
	@NotNull
	private double advanceMaintenance;
	
	@NotNull
	private double totalElectricBill;
	
	@NotNull
	private double totalMaintenanceBill;
	
	@NotNull
	private double totalBill;
	
	private Date created_dt;
	private Date updated_dt;
	
	@ManyToOne
	@JoinColumn(name="user_id")
	private UserData user;
	
	@ManyToOne
	@JoinColumn(name="house_id")
	private HouseMapping housemapping;
}
