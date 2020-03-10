package com.smartest.store.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Invoice {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	//@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt_BR", timezone = "America/Sao_Paulo")
	private Calendar competencyDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt_BR", timezone = "America/Sao_Paulo")
	private Calendar dueDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy", locale = "pt_BR", timezone = "America/Sao_Paulo")
    private Calendar payDate;

    @ManyToOne(optional=false, fetch=FetchType.EAGER)
    private Customer customer;
	
	
	/**
	 * @deprecated
	 */
	public Invoice() {

	}
	
	
	public Invoice(Calendar competencyDate, Customer customer) {
		this.competencyDate = competencyDate;
		this.customer = customer;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


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


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
