package com.smartest.store.test.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;

import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartest.store.controller.form.LoginForm;
import com.smartest.store.model.Customer;
import com.smartest.store.model.Invoice;
import com.smartest.store.repository.CustomerRepository;
import com.smartest.store.repository.InvoiceRepository;


/**
 * 
 * Test InvoiceController using spring boot test
 * 
 * @author Tiago Santos
 * @since 2020-03-10
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InvoiceControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceControllerTest.class);
	
	@Autowired
    private TestRestTemplate restTemplate;
	
	@MockBean
    private InvoiceRepository invoiceRepository;
	@MockBean
    private CustomerRepository customerRepository;

	@Test
	public void testFindAllSuccess() throws URISyntaxException {
		
		logger.info("START TEST:: testFindAllSuccess");
		
		Customer customer = new Customer("Customer", "Test", "+640270000000");
		Invoice invoice = new Invoice(Calendar.getInstance(), customer);
		Invoice invoice2 = new Invoice(Calendar.getInstance(), customer);
		
		// given
        given(invoiceRepository.findAll()).willReturn(Arrays.asList(invoice, invoice2));
		
        // when
        ResponseEntity<List> customersResponse = restTemplate.getForEntity("/invoices", List.class);
        logger.info("TEST RESPONSE:: "+ customersResponse);
		
        // then
		assertEquals(HttpStatus.OK.value(), customersResponse.getStatusCodeValue());
		
		logger.info("END TEST:: testFindAllSuccess");
	}

	@Test
	public void testFindByInvoiceIdSuccess() throws URISyntaxException {
		
		logger.info("START TEST:: testFindByInvoiceIdSuccess");
		
		Customer customer = new Customer("Customer", "Test", "+640270000000");
		Invoice invoice = new Invoice(Calendar.getInstance(), customer);
		
		// given
        given(invoiceRepository.findById(1)).willReturn(Optional.of(invoice));
		
        // when
        ResponseEntity<Invoice> invoiceResponse = restTemplate.getForEntity("/invoices/1", Invoice.class);
        logger.info("TEST RESPONSE:: "+ invoiceResponse);
		
        // then
		assertEquals(HttpStatus.OK.value(), invoiceResponse.getStatusCodeValue());
		
		logger.info("END TEST:: testFindByInvoiceIdSuccess");
	}
	
	
	//Comment this method if you are using DB
	//@Test
	public void testSaveSuccess() throws URISyntaxException, JSONException {
		
		logger.info("START TEST:: testSaveSuccess");
		
		Customer customer = new Customer(1000);
		Invoice invoice = new Invoice(Calendar.getInstance(), customer);
		
		// given
		given(customerRepository.save(customer)).willReturn(customer);
        given(invoiceRepository.save(invoice)).willReturn(invoice);
        
        
        ResponseEntity<String> tokenResponse = restTemplate.postForEntity("/auth", new LoginForm("admin@admin.com","123456"), String.class);
        
        assertEquals(HttpStatus.OK, tokenResponse.getStatusCode());

		// A JSON object
		JSONObject jsonObject = new JSONObject(tokenResponse.getBody());

		// create headers
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", jsonObject.get("type") + " " + jsonObject.get("token"));
		// build the request
		HttpEntity<Invoice> request = new HttpEntity<>(invoice, headers);
		
		
        
        // when
        ResponseEntity<Invoice> invoiceResponse = restTemplate.postForEntity("/invoices", request, Invoice.class);
        logger.info("TEST RESPONSE:: "+ invoiceResponse);
		
        // then
		assertEquals(HttpStatus.CREATED.value(), invoiceResponse.getStatusCodeValue());
		
		logger.info("END TEST:: testSaveSuccess");
	}
	
}