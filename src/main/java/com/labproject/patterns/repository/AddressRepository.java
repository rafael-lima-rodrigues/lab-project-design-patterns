package com.labproject.patterns.repository;

import com.labproject.patterns.model.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rafael de Lima Rodrigues
 */

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
}
