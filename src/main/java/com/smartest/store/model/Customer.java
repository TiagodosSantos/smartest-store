package com.smartest.store.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smartest.store.utils.DateUtils;

/**
 * 
 * Entity that represents a customer in the database
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	//@JsonIgnore
	private Integer id;
	@NotEmpty @Length(min = 2)
	private String name;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Calendar birthDate;
	private String gender;
	private String telephoneNumber;
	private String mobileNumber;
	
	
	
	/**
	 * @deprecated
	 */
	public Customer() {

	}
	
	public Customer(Integer id) {
		this.id = id;
	}
	
	public Customer(String login, String gender,String telephoneNumber) {
		this.name = login;
		this.gender = gender;
		this.telephoneNumber = telephoneNumber;
	}
	
	public Customer(String name, Calendar birthDate, String gender, String telephoneNumber,
			String mobileNumber) {
		this.name = name;
		this.birthDate = birthDate;
		this.gender = gender;
		this.telephoneNumber = telephoneNumber;
		this.mobileNumber = mobileNumber;
	}

	public Integer getId() {
		return id;
	}
	
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

	public void setId(Integer id) {
		this.id = id;
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
		Customer other = (Customer) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", birthDate=" + DateUtils.getInstance().dateToString(birthDate, null) + ", gender=" + gender
				+ ", telephoneNumber=" + telephoneNumber + ", mobileNumber=" + mobileNumber + "]";
	}

}
