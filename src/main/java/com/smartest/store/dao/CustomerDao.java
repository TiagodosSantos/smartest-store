package com.smartest.store.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.smartest.store.model.Customer;


@Repository
public interface CustomerDao extends CrudRepository<Customer, Integer> {

}
