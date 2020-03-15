package com.smartest.store.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.smartest.store.controller.form.CustomerForm;
import com.smartest.store.model.Customer;
import com.smartest.store.repository.CustomerRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Controller responsible for manipulating customer information
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */

@Api(value="Customers", tags = {"Operations to manipulate customers data"})
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = "/customers")
public class CustomerController {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	/**
	   * This is  method returns all customers of the database
	   * 
	   * @return list with all customers
	   * @exception Exception on general errors.
	   * @see Customer
	   */
	@ApiOperation(value = "Return a list with all customers of the database", response = Customer.class)
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Customer> findAll() {
		
		logger.info("Start");
		
		Iterable<Customer> findAll = customerRepository.findAll();
		logger.info("All customers?  -> "+findAll);

		logger.info("End");	
		return (List<Customer>) findAll;
	}
	
	/**
	   * This is  method find a specific customer by id
	   * 
	   * @return customer 
	   * @exception Exception on general errors and not found.
	   * @see Customer
	   */
	@ApiOperation(value = "Find and return customer by id", response = Customer.class)
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 404, message = "Customer not found")})
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findByCustomerId(@PathVariable Integer id) {
		
		logger.info("Start");
		
		Optional<Customer> customer = customerRepository.findById(id);
		logger.info("invoices?  -> "+customer);
		
		ResponseEntity<Customer> response = null;
		if (customer.isPresent()) 
			response = ResponseEntity.ok(customer.get());
		else
			response = ResponseEntity.notFound().build();
		
		logger.info("End");	
		return response;
	}
	
	
	/**
	   * This is method is responsible for creating new customer
	   * 
	   * @param CustomerForm that contains a valid form to create a new customer.
	   * @return ResponseEntity with the resource created.
	   * @exception Exception on general errors.
	   * @see Customer
	   */
	@ApiOperation(value = "Create a new Customer")
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@PostMapping
	@Transactional 
	public ResponseEntity<?> save(@ApiParam(value = "customerForm") @Valid @RequestBody CustomerForm customerForm, UriComponentsBuilder uriBuilder) {
		
		logger.info("Start");
		
		Customer customerSaved = customerRepository.save(customerForm.convertToCustomer());	
		logger.info("CustomerSaved -> "+customerSaved);
	
		URI uri = uriBuilder.path("/customers/{id}").buildAndExpand(customerSaved.getId()).toUri();
	    ResponseEntity<?> body = ResponseEntity.created(uri).body(customerSaved);
		
		logger.info("End");
		return body;
	}
	
	/**
	   * This method is responsible for updating customer by id
	   * 
	   * @param id identification of the customer that needs to be updated.
	   * @param customerForm customer data that needs to be updated
	   * @return Customer updated if no exception is thrown.
	   * @exception Exception on general errors.
	   * @see Customer
	   */
	@ApiOperation(value = "Update customer by id")
	@ApiResponses({
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Customer not found")})
	@PutMapping("/{id}")
	@Transactional 
	public ResponseEntity<Customer> update(@PathVariable Integer id, @ApiParam(value = "customerForm") @Valid @RequestBody CustomerForm customerForm, UriComponentsBuilder uriBuilder) {
		
		logger.info("Start");
		
		Optional<Customer> optional = customerRepository.findById(id);
		ResponseEntity<Customer> response = null;
		if (optional.isPresent()) {
			Customer customerToUpdate = customerForm.update(id, customerRepository);
			response = ResponseEntity.ok(customerToUpdate);
			logger.info("CustomerUpdated -> " + customerToUpdate);
		} else {
			response = ResponseEntity.notFound().build();
			logger.info("Customer not found. CustomerID -> " + id);
		}
		
		logger.info("End");
		return response;
		
	}
	

	/**
	   * This is method is responsible for deleting customers from the database
	   * 
	   * @param idCustomer an integer that represents the customer id.
	   * @return ResponseEntity code.
	   * @exception Exception on general errors.
	   */
	@DeleteMapping("/{idCustomer}")
	@ApiOperation(value = "Delete customer by id")
	@ApiResponses({
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Customer not found")})
	@Transactional
	public ResponseEntity<?> remove(@PathVariable("idCustomer") Integer idCustomer) {
		logger.info("Start");
		
		Optional<Customer> optional = customerRepository.findById(idCustomer);
		ResponseEntity<?> response = null;
		
		if (optional.isPresent()) {
			customerRepository.deleteById(idCustomer);
			response = ResponseEntity.ok().build();
			logger.info("CustomerDeleted  -> "+optional);
		}else {
			response = ResponseEntity.notFound().build();
			logger.info("CustomerId "+idCustomer+" not found");
		}
		
		logger.info("End");
		return response;
	}
	
	
}
