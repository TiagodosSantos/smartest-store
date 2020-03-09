package com.smartest.store.model;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotBlank
	private String name;
	//@NotNull
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", locale = "pt_BR", timezone = "America/Sao_Paulo")
	private Calendar birthDate;
	//@NotBlank
	private String gender;
	//@NotBlank
	private String telephoneNumber;
	//@NotBlank
	private String mobileNumber;
	
	
	@ManyToMany
	private Set<Customer> amigos = new HashSet<Customer>();
	
	/**
	 * @deprecated
	 */
	public Customer() {

	}
	
	
	public Customer(String login, String gender,String telephoneNumber) {
		this.name = login;
		this.gender = gender;
		this.telephoneNumber = telephoneNumber;
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


	public Set<Customer> getAmigos() {
		return amigos;
	}


	public void setAmigos(Set<Customer> amigos) {
		this.amigos = amigos;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	public void adicionaAmigo(Customer usuario) {
		amigos.add(usuario);
	}

}
