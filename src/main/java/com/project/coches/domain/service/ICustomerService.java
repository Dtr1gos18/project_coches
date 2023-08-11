package com.project.coches.domain.service;

import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.dto.ResponsePassCustomerDto;

import java.util.List;
import java.util.Optional;

public interface ICustomerService {

    List<CustomerDto> getAll();

    Optional<CustomerDto> getCustomerByCardId(String cardId);

    Optional<CustomerDto> getCustomerByEmail(String email);

    ResponsePassCustomerDto save(CustomerDto newCustomer);

    Optional<CustomerDto> update(CustomerDto customerUpdate);

    boolean delete(String cardId);
}
