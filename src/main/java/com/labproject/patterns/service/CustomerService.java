package com.labproject.patterns.service;

import com.labproject.patterns.exceptions.CustomerNotFoundException;
import com.labproject.patterns.model.Customer;

/**
 * Interface which define <b>Strategy</b> pattern in Customer domain. Thus, whether necessary,
 * it is possible to implement multiple this interface.
 *
 * @author Rafael de Lima Rodrigues
 */

public interface CustomerService {

    Iterable<Customer> findAll();
    Customer findById(Long id) throws CustomerNotFoundException;
    void create(Customer customer);
    void update(Long id, Customer customer) throws CustomerNotFoundException;
    void delete(Long id) throws CustomerNotFoundException;
}
