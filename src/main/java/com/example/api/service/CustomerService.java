package com.example.api.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import com.example.api.domain.Customer;
import com.example.api.repository.CustomerRepository;

@Service
public class CustomerService {

	private CustomerRepository repository;

	@Autowired
	public CustomerService(CustomerRepository repository) {
		this.repository = repository;
	}

	public List<Customer> findAll() {
		return repository.findAllByOrderByNameAsc();
	}

	public Optional<Customer> findById(Long id) {
		return repository.findById(id);
	}

	public Customer create(Customer customer) {
		if (!ObjectUtils.isEmpty(customer)) {
			Customer newCustomer = new Customer();
			newCustomer.setName(customer.getName());
			newCustomer.setEmail(customer.getEmail());
			newCustomer.setGender(customer.getGender());
			return repository.save(newCustomer);
		} else {
		   throw new EntityNotFoundException("Customer not null");
		}
	}

	public Customer update(Customer customer) {
		   Optional<Customer> hasCustomer = repository.findById(customer.getId());
	
		if (ObjectUtils.isEmpty(customer)) {
			Customer existingCustomer = hasCustomer.get();
			existingCustomer.setName(customer.getName());
			existingCustomer.setEmail(customer.getEmail());
			existingCustomer.setGender(customer.getGender());
			return repository.save(existingCustomer);
		} else {
		   throw new EntityNotFoundException("Customer id not found");
		}
	}
	public void deleteById(Long id) {
	  var existsById = repository.findById(id);
        if (!ObjectUtils.isEmpty(existsById)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Customer id not found");
        }
	}
	

}
