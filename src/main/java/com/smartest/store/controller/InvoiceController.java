package com.smartest.store.controller;

import java.net.URI;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.smartest.store.controller.form.InvoiceForm;
import com.smartest.store.model.Invoice;
import com.smartest.store.repository.InvoiceRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 
 * Controller responsible for manipulating invoice information
 * 
 * @author Tiago Santos
 * @since   2020-03-10
 * 
 */

@Api(value="Invoices", tags = {"Operations to manipulate invoice data"})
@RestController
@RequestMapping(path="/invoices")
public class InvoiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	
	
	@Autowired
	private InvoiceRepository invoiceRepository;
	
	
	/**
	   * This is  method returns all invoices of the database
	   * 
	   * @return list with all invoices
	   * @exception Exception on general errors.
	   * @see Invoice
	   */
	@ApiOperation(value = "Return a list with all invoices of the database", response = Invoice.class)
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<Invoice> findAllInvoices() {
		
		logger.info("Start");
		
		Iterable<Invoice> findAll = invoiceRepository.findAll();
		logger.info("All invoices?  -> "+findAll);
		
		logger.info("End");
		return (List<Invoice>) findAll;
	}
	
	/**
	   * This is  method returns all Invoices of a customer by name
	   * 
	   * @return list invoices by customer name
	   * @exception Exception on general errors.
	   * @see Invoice
	   */
	@ApiOperation(value = "Return a list with all Invoices of a customer by customer name", response = Invoice.class)
	@ApiResponses({
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Invoice not found")})
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path="/customerName")
	public ResponseEntity<?> findByCustomerName(@RequestParam("customerName") String customerName) {
		
		logger.info("Start");

		Iterable<Invoice> invoices = invoiceRepository.findByCustomerNameContainingIgnoreCase(customerName);
		logger.info("invoices?  -> " + invoices);

		ResponseEntity<?> response = null;
		if (invoices.iterator().hasNext())
			response = ResponseEntity.ok((List<Invoice>) invoices);
		else
			response = ResponseEntity.notFound().build();

		logger.info("End");
		return response;
	}
	
	/**
	   * This is  method returns invoices by date
	   * 
	   * @return list with invoices by date
	   * @exception Exception on general errors.
	   * @see Invoice
	   */
	@ApiOperation(value = "Return a list with all Invoices by dueDate", response = Invoice.class)
	@ApiResponses({
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Invoice not found")})
	@GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE, path="/dueDate")
	public ResponseEntity<?> findByInvoiceDueDate(@RequestParam("dueDate") @DateTimeFormat(pattern = "dd-MM-yyyy") Calendar dueDate) {
		
		logger.info("Start");
		
		
		Iterable<Invoice> invoices = invoiceRepository.findByDueDate(dueDate);
		logger.info("invoices?  -> "+invoices);
		
		ResponseEntity<?> response = null;
		if (invoices.iterator().hasNext())
			response = ResponseEntity.ok((List<Invoice>) invoices);
		else
			response = ResponseEntity.notFound().build();

		logger.info("End");
		return response;
	}
	
	/**
	   * This is  method returns invoices by id
	   * 
	   * @return Invoice 
	   * @exception Exception on general errors.
	   * @see Invoice
	   */
	@ApiOperation(value = "Return invoice by its id", response = Invoice.class)
	@ApiResponses({
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Invoice not found")})
	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
		
		logger.info("Start");
		
		
		Optional<Invoice> invoice = invoiceRepository.findById(id);
		logger.info("invoices?  -> "+invoice);
		
		ResponseEntity<?> response = null;
		if (invoice.isPresent())
			response = ResponseEntity.ok(invoice.get());
		else
			response = ResponseEntity.notFound().build();

		logger.info("End");
		return response;
	}
	
	
	/**
	   * This is method is responsible for creating new invoice
	   * 
	   * @param InvoiceForm that contains a valid form to create a new invoice.
	   * @return ResponseEntity
	   * @exception Exception on general errors.
	   * @see Invoice
	   */
	@ApiOperation(value = "Create a new Invoice")
	@ApiResponses({
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@ApiParam(value = "InvoiceForm") @Valid @RequestBody InvoiceForm invoiceForm, UriComponentsBuilder uriBuilder) {
		
		logger.info("Start");
		
		Invoice invoiceSaved = invoiceRepository.save(invoiceForm.convertToInvoice());	
		logger.info("InvoiceSaved -> "+invoiceSaved);
		
		URI uri = uriBuilder.path("/invoices/{id}").buildAndExpand(invoiceSaved.getId()).toUri();
	    ResponseEntity<?> body = ResponseEntity.created(uri).body(invoiceSaved);
		
		logger.info("End");
		return body;
	}
	
	/**
	   * This method is responsible for updating invoices by id
	   * 
	   * @param id identification of the invoice that needs to be updated.
	   * @param invoiceForm invoice data that needs to be updated
	   * @return Invoice updated if no exception is thrown.
	   * @exception Exception on general errors.
	   * @see Invoice
	   */
	@ApiOperation(value = "Update invoice by id")
	@ApiResponses({
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Invoice not found")})
	@PutMapping("/{id}")
	@Transactional 
	public ResponseEntity<Invoice> update(@PathVariable Integer id, @ApiParam(value = "invoiceForm") @Valid @RequestBody InvoiceForm invoiceForm, UriComponentsBuilder uriBuilder) {
		
		logger.info("Start");
		
		Optional<Invoice> optional = invoiceRepository.findById(id);
		ResponseEntity<Invoice> response = null;
		if (optional.isPresent()) {
			Invoice invoiceToUpdate = invoiceForm.update(id, invoiceRepository);
			response = ResponseEntity.ok(invoiceToUpdate);
			logger.info("InvoiceUpdated -> " + invoiceToUpdate);
		} else {
			response = ResponseEntity.notFound().build();
			logger.info("Invoice not found. InvoiceId -> " + id);
		}
		
		logger.info("End");
		return response;
		
	}
	

	/**
	   * This is method is responsible for deleting invoices
	   * 
	   * @param idInvoice an integer that represents the invoice id.
	   * @return ResponseEntity code.
	   * @exception Exception on general errors.
	   */
	@DeleteMapping("/{idInvoice}")
	@ApiOperation(value = "Delete invoice by id")
	@ApiResponses({
		@ApiResponse(code = 500, message = "Internal Server Error"),
		@ApiResponse(code = 404, message = "Customer not found")})
	@Transactional
	public ResponseEntity<?> remove(@PathVariable("idInvoice") Integer idInvoice) {
		logger.info("Start");
		
		Optional<Invoice> optional = invoiceRepository.findById(idInvoice);
		ResponseEntity<?> response = null;
		
		if (optional.isPresent()) {
			invoiceRepository.deleteById(idInvoice);
			response = ResponseEntity.ok().build();
			logger.info("InvoiceDeleted  -> "+optional);
		}else {
			response = ResponseEntity.notFound().build();
			logger.info("CustomerId "+optional+" not found");
		}
		
		logger.info("End");
		return response;
	}
	
	
}
