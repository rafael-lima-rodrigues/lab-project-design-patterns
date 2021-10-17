package com.labproject.patterns.service;

import com.labproject.patterns.model.Address;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Client HTTP, created by <b>OpenFeign</b>, to consume <b>ViaCEP</b> API.
 *
 * @author Rafael de Lima Rodrigues
 */

@FeignClient( name = "viacep", url = "https://viacep.com.br/ws")
public interface ViaCepService {

    @GetMapping(value = "/{cep}/json/")
    Address searchCep(@PathVariable("cep") String cep);
}
