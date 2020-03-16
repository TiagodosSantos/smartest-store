package com.smartest.store.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartest.store.model.Customer;
import com.smartest.store.repository.CustomerRepository;



/**
 * 
 * Test CustomerController using spring boot test
 * 
 * @author Tiago Santos
 * @since 2020-03-10
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(CustomerControllerTest.class);
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
    private CustomerRepository customerRepository;

	@Test
	public void testFindAllSuccess() throws URISyntaxException {
		
		logger.info("START TEST:: testFindAllSuccess");
		
		Customer customer = new Customer("Customer", "Test", "+640270000000");
		Customer customer2 = new Customer("Customer2", "Test2", "+640270000000");
		
		// given
        given(customerRepository.findAll()).willReturn(Arrays.asList(customer, customer2));
		
        // when
        ResponseEntity<List> customersResponse = restTemplate.getForEntity("/customers", List.class);
        logger.info("TEST RESPONSE:: "+ customersResponse);
		
        // then
		assertEquals(HttpStatus.OK.value(), customersResponse.getStatusCodeValue());
		
		logger.info("END TEST:: testFindAllSuccess");
	}

	@Test
	public void testFindByCustomerIdSuccess() throws URISyntaxException {
		
		logger.info("START TEST:: testFindByCustomerIdSuccess");
		
		Customer customer = new Customer("Customer", "Test", "+640270000000");
		
		// given
        given(customerRepository.findById(1)).willReturn(Optional.of(customer));
		
        // when
        ResponseEntity<Customer> customerResponse = restTemplate.getForEntity("/customers/1", Customer.class);
        logger.info("TEST RESPONSE:: "+ customerResponse);
		
        // then
		assertEquals(HttpStatus.OK.value(), customerResponse.getStatusCodeValue());
		
		logger.info("END TEST:: testFindByCustomerIdSuccess");
	}
	
	@Test
	public void testSaveSuccess() throws URISyntaxException {
		
		logger.info("START TEST:: testSaveSuccess");
		
		Customer customer = new Customer("Customer", "Test", "+640270000000");
		
		// given
        given(customerRepository.save(customer)).willReturn(customer);
        
        // when
        ResponseEntity<Customer> customerResponse = restTemplate.postForEntity("/customers", customer, Customer.class);
        logger.info("TEST RESPONSE:: "+ customerResponse);
		
        // then
		assertEquals(HttpStatus.CREATED.value(), customerResponse.getStatusCodeValue());
		
		logger.info("END TEST:: testSaveSuccess");
	}
	
}