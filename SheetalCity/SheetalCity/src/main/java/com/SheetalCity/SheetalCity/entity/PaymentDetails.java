package com.SheetalCity.SheetalCity.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
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
@Table(name = "payment_details")
public class PaymentDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long receiptId;
	
	@NotNull
	private double electricPayment;
	@NotNull
	private double mainteancePaument;
	@NotNull
	private double totalPayment;
	
	private Date createdDate;
	private Date updatedDate;
	
	@JoinColumn(name="user_id")
	@ManyToOne
	private UserData user;
	
	@PrePersist
    public void prePersist() {
       if (updatedDate == null) {
    	   updatedDate = new Date();
       }
       if(createdDate == null) {
    	   createdDate = new Date();
       }
}
	
}
