package com.smartest.store.repository;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartest.store.model.Invoice;

//@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {

	List<Invoice> findByCustomerId(Integer customerName);
	List<Invoice> findByDueDate(Calendar dueDate);
	List<Invoice> findByCustomerNameContainingIgnoreCase(String customerName);
}
