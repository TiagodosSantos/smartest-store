package com.smartest.store.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Iterables;
import com.smartest.store.dao.CustomerDao;
import com.smartest.store.dao.InvoiceDao;
import com.smartest.store.model.Customer;

/**
 * 
 * Controller responsible for manage information regarding customers.  
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */

@Api(value="User", tags = {"Operations to manage customers data"})
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	
	/**
	   * This is method is responsible for creating new customer
	   * 
	   * @param NewCustomerForm an object that contains a valid form to create a new customer.
	   * @return HttpEntity code.
	   * @exception Exception on general errors.
	   * @see NewCustomerForm
	   */
	@ApiOperation(value = "Create a new Customer")
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@PostMapping("/store/customer")
	@Transactional
	public HttpEntity<?> novo(@ApiParam(value = "Customer") @Valid @RequestBody Customer customer) {
		
		logger.info("Start");
		
		Customer customerSaved = customerDao.save(customer);	
		logger.info("CustomerSaved -> "+customerSaved);
		
		
		logger.info("End");
		return ResponseEntity.ok().build();
	}
	

	/**
	   * This is method is responsible for deleting customers from the database
	   * 
	   * @param idCustomer an integer that represents the customer id.
	   * @return HttpEntity code.
	   * @exception Exception on general errors.
	   */
	@DeleteMapping("/store/customer/{idCustomer}")
	@ApiOperation(value = "Delete a Customer")
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Transactional
	public HttpEntity<?> remove(@PathVariable("idCustomer") Integer idCustomer) {
		logger.info("Start");
		
		customerDao.delete(idCustomer);
		
		logger.info("CustomerDeleted?  -> "+customerDao.findOne(idCustomer) == null ? " SIM ": " NÃO ");
		logger.info("End");
		
		return ResponseEntity.ok().build();
	}
	
	/**
	   * This is  method obtain all customers of the database
	   * 
	   * @return list with all customers
	   * @exception Exception on general errors.
	   * @see Customer
	   */
	@ApiOperation(value = "Return a list with all customers of the database", response = Customer.class)
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@GetMapping(value = "/store/usuario", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Customer> findAll() {
		
		logger.info("Start");
		
		
		Iterable<Customer> findAll = customerDao.findAll();
		logger.info("All customers?  -> "+findAll);
		if(Iterables.isEmpty(findAll)){
			customerDao.save(new Customer("Tiago", "123", "Blablabla"));
			findAll = customerDao.findAll();
		}
		
        List<Customer> usuarios = new ArrayList<Customer>(); 
        Iterator<Customer> iterator = findAll.iterator(); 
        while (iterator.hasNext())  
        	usuarios.add(iterator.next()); 
  
        logger.info("End");	
		return usuarios;
	}
	
}
