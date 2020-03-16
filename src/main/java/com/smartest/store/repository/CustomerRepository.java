package com.smartest.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartest.store.model.Customer;


/**
 * 
 * Repository responsible for managing customer data on database
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */
//@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
