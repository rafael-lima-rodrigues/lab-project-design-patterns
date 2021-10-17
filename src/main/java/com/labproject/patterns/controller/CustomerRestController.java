package com.labproject.patterns.controller;

import com.labproject.patterns.exceptions.CustomerNotFoundException;
import com.labproject.patterns.model.Customer;
import com.labproject.patterns.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

/**
 * This {@link RestController} represents <b>Facade</b>, because its abstract the whole integration complexity
 * (H2 Database and ViaCEP API) in an simple interface (API REST)
 *
 * @author Rafael de Lima Rodrigues
 */

@RestController
@RequestMapping("customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public ResponseEntity<Iterable<Customer>> findAll(){
        return ResponseEntity.ok(customerService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(customerService.findById(id));
        } catch (CustomerNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found", ex);
        }
    }

    @PostMapping
    public ResponseEntity<Customer> create(@RequestBody Customer customer){
        customerService.create(customer);
        return ResponseEntity.ok(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id,@RequestBody Customer customer) throws CustomerNotFoundException {
            customerService.update(id, customer);
        return ResponseEntity.ok(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            customerService.delete(id);
            return ResponseEntity.ok().build();
        } catch (CustomerNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor Not Found", ex);
        }

    }
}
