package com.smartest.store.controller.form;

import java.util.Calendar;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartest.store.model.Customer;
import com.smartest.store.model.Invoice;
import com.smartest.store.repository.InvoiceRepository;

/**
 * 
 * Form that contains invoice data to create/update a new invoice in the database
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */
public class InvoiceForm {
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy"/*, locale = "pt-BR", timezone = "America/Sao_Paulo"*/)
	private Calendar competencyDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy"/*, locale = "pt-BR", timezone = "America/Sao_Paulo"*/)
	private Calendar dueDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy"/*, locale = "pt-BR", timezone = "America/Sao_Paulo"*/)
    private Calendar payDate;
	private Integer customerId;
	
	public Calendar getCompetencyDate() {
		return competencyDate;
	}

	public void setCompetencyDate(Calendar competencyDate) {
		this.competencyDate = competencyDate;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public Calendar getPayDate() {
		return payDate;
	}

	public void setPayDate(Calendar payDate) {
		this.payDate = payDate;
	}

	public Integer getCustomerId() {
		return customerId;
	}
	
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public Invoice update(Integer id, InvoiceRepository invoiceRepository) {
		Invoice invoice = invoiceRepository.getOne(id);
		
		invoice.setCompetencyDate(competencyDate);
		invoice.setDueDate(dueDate);
		invoice.setPayDate(payDate);
		
		return invoice;
	}
	
	public Invoice convertToInvoice() {
		return new Invoice(this.competencyDate, this.dueDate, 
					this.payDate, new Customer(this.customerId));
	}

	@Override
	public String toString() {
		return "InvoiceForm [competencyDate=" + competencyDate + ", dueDate=" + dueDate + ", payDate=" + payDate
				+ ", customerId=" + customerId + "]";
	}
	
}
