package com.SheetalCity.SheetalCity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.SheetalCity.SheetalCity.entity.LedgerDetails;

@Repository
public interface LedgerDetailsRepositories extends JpaRepository<LedgerDetails,Integer> {
	List<LedgerDetails> findByPaymentStatus(String paymentStatus);
}
