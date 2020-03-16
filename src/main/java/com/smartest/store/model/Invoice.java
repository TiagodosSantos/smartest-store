package com.smartest.store.model;

import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.smartest.store.utils.DateUtils;

/**
 * 
 * Entity that represents an invoice in the database
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */

@Entity
public class Invoice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private Integer id;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy"/*, locale = "pt-BR", timezone = "America/Sao_Paulo"*/)
	private Calendar competencyDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy"/*, locale = "pt-BR", timezone = "America/Sao_Paulo"*/)
	private Calendar dueDate;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy"/*, locale = "pt-BR", timezone = "America/Sao_Paulo"*/)
    private Calendar payDate;

    @ManyToOne(optional=false, cascade = CascadeType.DETACH)
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

	public Invoice(Calendar competencyDate, Calendar dueDate, Calendar payDate, Customer customer) {
		this.competencyDate = competencyDate;
		this.dueDate = dueDate;
		this.payDate = payDate;
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
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Invoice other = (Invoice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		DateUtils dateUtils = DateUtils.getInstance();
		return "Invoice [id=" + id + ", competencyDate=" + dateUtils.dateToString(competencyDate, null) + ", dueDate=" + dateUtils.dateToString(dueDate, null) + ", payDate="
				+ dateUtils.dateToString(payDate, null) + ", customer=" + customer + "]";
	}
	
}
