package com.smartest.store.test.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Calendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.smartest.store.model.Customer;
import com.smartest.store.model.Invoice;
import com.smartest.store.repository.CustomerRepository;
import com.smartest.store.repository.InvoiceRepository;

/**
 * 
 * Test the ControllerTest using spring boot test
 * 
 * @author Tiago Santos
 * @since 2020-03-10
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ControllerTest {

	private static final Logger logger = LoggerFactory.getLogger(ControllerTest.class);

	
	@MockBean
    private CustomerRepository customerDao;
	@MockBean
    private InvoiceRepository invoiceDao;
	
	
	/**
	 * This  method will test finding a customer 
	 */
	@Test
    public void whenFindingCustomerById() {
		
		logger.info("START TEST:: whenFindingCustomerById");
		
		Customer test = new Customer("Tiago", "Tiago", "Tiago");
		customerDao.save(test);
        assertThat(test.equals(customerDao.findById(1)));
        
        logger.info("END TEST:: whenFindingCustomerById");
    }
     

	/**
	 * This  method will test finding a customer 
	 */
	@Test
    public void whenFindingInvoiceById() {
		
		logger.info("START TEST:: whenFindingInvoiceById");
		
		Customer customerTest = new Customer("Tiago", "Tiago", "Tiago");
		customerTest = customerDao.save(customerTest);
		
		Invoice invoiceTest = new Invoice(Calendar.getInstance(), customerTest);
		invoiceDao.save(invoiceTest);
        assertThat(invoiceTest.equals(invoiceDao.findById(1)));
        
        logger.info("END TEST:: whenFindingInvoiceById");
    }
	
}
