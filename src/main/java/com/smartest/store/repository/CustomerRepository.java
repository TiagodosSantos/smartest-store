package com.smartest.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartest.store.model.Customer;


//@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

}
