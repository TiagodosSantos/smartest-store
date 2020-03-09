package com.smartest.store.dao;

import java.util.Calendar;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smartest.store.model.Invoice;

@Repository
public interface InvoiceDao extends CrudRepository<Invoice, Integer> {

	List<Invoice> findByCustomerId(Integer customerName);
	List<Invoice> findByDueDate(Calendar dueDate);
}
