package com.smartest.store.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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

import com.smartest.store.dao.InvoiceDao;
import com.smartest.store.model.Invoice;

/**
 * 
 * Controller responsible for manage information regarding invoices.  
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */

@Api(value="Invoice", tags = {"Operations to manage invoice data"})
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class InvoiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	
	
	@Autowired
	private InvoiceDao invoiceDao;
	
	
	/**
	   * This is method is responsible for creating new invoice
	   * 
	   * @param invoice an object that contains a valid form to create a new invoice.
	   * @return HttpEntity code.
	   * @exception Exception on general errors.
	   * @see NewInvoiceForm
	   */
	@ApiOperation(value = "Create a new Invoice")
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@PostMapping("/store/invoice")
	@Transactional
	public HttpEntity<?> novo(@ApiParam(value = "Invoice form") @Valid @RequestBody Invoice invoice) {
		
		logger.info("Start");
		
		Invoice invoiceSaved = invoiceDao.save(invoice);	
		logger.info("InvoiceSaved -> "+invoiceSaved);
		
		
		logger.info("End");
		return ResponseEntity.ok().build();
	}
	

	/**
	   * This is method is responsible for deleting invoices from the database
	   * 
	   * @param idInvoice an integer that represents the invoice id.
	   * @return HttpEntity code.
	   * @exception Exception on general errors.
	   */
	@DeleteMapping("/store/invoice/{idInvoice}")
	@ApiOperation(value = "Delete an Invoice")
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@Transactional
	public HttpEntity<?> remove(@PathVariable("idInvoice") Integer idInvoice) {
		logger.info("Start");
		
		invoiceDao.delete(idInvoice);
		
		logger.info("InvoiceDeleted?  -> "+invoiceDao.findOne(idInvoice) == null ? " SIM ": " NÃO ");
		logger.info("End");
		
		return ResponseEntity.ok().build();
	}
	
	
	/**
	   * This is  method obtain all invoices from the database
	   * 
	   * @return list with all invoices
	   * @exception Exception on general errors.
	   * @see Invoice
	   */
	@ApiOperation(value = "Return a list with all invoices of the database", response = Invoice.class)
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@GetMapping(value = "/store/invoice", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<Invoice> findAllInvoices() {
		
		logger.info("Start");
		
		Iterable<Invoice> findAll = invoiceDao.findAll();
		logger.info("All invoices?  -> "+findAll);
		
		logger.info("End");
		return (List<Invoice>) findAll;
	}
	
}
