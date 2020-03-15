package com.smartest.store.controller.form;

import java.util.Calendar;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartest.store.model.Customer;
import com.smartest.store.repository.CustomerRepository;

public class CustomerForm {
	
	
	@NotEmpty @Length(min = 2)
	private String name;
	//@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy"/*, locale = "pt-BR", timezone = "America/Sao_Paulo"*/)
	private Calendar birthDate;
	//@NotBlank
	private String gender;
	//@NotBlank
	private String telephoneNumber;
	//@NotBlank
	private String mobileNumber;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelephoneNumber() {
		return telephoneNumber;
	}

	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Customer update(Integer id, CustomerRepository customerRepository) {
		Customer customer = customerRepository.getOne(id);
		
		customer.setName(name);
		customer.setBirthDate(birthDate);
		customer.setGender(gender);
		customer.setTelephoneNumber(telephoneNumber);
		customer.setMobileNumber(mobileNumber);
		
		return customer;
	}
	
	public Customer convertToCustomer() {
		return new Customer(name,birthDate,gender,telephoneNumber,mobileNumber);
	}

	@Override
	public String toString() {
		return "CustomerForm [name=" + name + ", birthDate=" + birthDate + ", gender=" + gender + ", telephoneNumber="
				+ telephoneNumber + ", mobileNumber=" + mobileNumber + "]";
	}

}
