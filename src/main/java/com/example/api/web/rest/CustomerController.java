package com.example.api.web.rest;

import java.util.List;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.example.api.domain.Customer;
import com.example.api.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController implements  CustomerImpl {

	private CustomerService service;

	@Autowired
	public CustomerController(CustomerService service) {
		this.service = service;
	}

	@Override
	@GetMapping
	public List<Customer> findAll() {
		return service.findAll();
	}

	@Override
	@GetMapping("/{id}")
	public Customer findById(@PathVariable Long id) {
		return service.findById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));
	}

	@Override
	@PostMapping("/create")
	public ResponseEntity<Customer> create(@RequestBody Customer customer) {
    try {
        Customer newCustomer = this.service.create(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCustomer);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

	@Override
	@PostMapping("/update")
	public ResponseEntity<Customer> update(@RequestBody Customer customer) {
    try {
        Customer novoCustomer = this.service.update(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCustomer);
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}

	@Override
	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id){
	 try {
         this.service.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
      
    }
}

