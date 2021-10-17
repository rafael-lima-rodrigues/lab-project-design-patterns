package com.labproject.patterns.service.impl;

import com.labproject.patterns.exceptions.CustomerNotFoundException;
import com.labproject.patterns.model.Address;
import com.labproject.patterns.model.Customer;
import com.labproject.patterns.repository.AddressRepository;
import com.labproject.patterns.repository.CustomerRepository;
import com.labproject.patterns.service.CustomerService;
import com.labproject.patterns.service.ViaCepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Rafael de Lima Rodrigues
 */

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ViaCepService viaCepService;

    @Override
    public Iterable<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    @Override
    public void create(Customer customer) {
        insertCustomer(customer);
    }


    @Override
    public void update(Long id, Customer customer) throws CustomerNotFoundException {
        verifyCustomer(id);
        insertCustomer(customer);
    }

    @Override
    public void delete(Long id) throws CustomerNotFoundException {
        verifyCustomer(id);
        customerRepository.deleteById(id);
    }

    private void verifyCustomer(Long id) throws CustomerNotFoundException {
        if(!customerRepository.existsById(id)){
            throw new CustomerNotFoundException(id);
        }
    }
    private void insertCustomer(Customer customer) {
        String cep = customer.getAddress().getCep();
        Address address = addressRepository.findById(cep).orElseGet(() -> {
            Address newAddress = viaCepService.searchCep(cep);
            addressRepository.save(newAddress);
            return newAddress;
        });
        customer.setAddress(address);
        customerRepository.save(customer);
    }
}
