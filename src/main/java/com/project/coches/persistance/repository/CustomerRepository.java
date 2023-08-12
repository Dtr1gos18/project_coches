package com.project.coches.persistance.repository;

import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.repository.ICustomerRepository;
import com.project.coches.persistance.crud.ICustomerCrud;
import com.project.coches.persistance.mapper.ICustomerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CustomerRepository implements ICustomerRepository {

    private final ICustomerCrud iCustomerCrud;

    private final ICustomerMapper iCustomerMapper;


    @Override
    public List<CustomerDto> getAll() {
        return iCustomerMapper.toCustomersDto(iCustomerCrud.findAll());
    }

    @Override
    public Optional<CustomerDto> getCustomerByCardId(String cardId) {
        return iCustomerCrud.findById(cardId).map(iCustomerMapper::toCustomerDto);
    }

    @Override
    public Optional<CustomerDto> getCustomerByEmail(String email) {
        return iCustomerCrud.findByEmail(email).map(iCustomerMapper::toCustomerDto);
    }

    @Override
    public CustomerDto save(CustomerDto newCustomer) {
        return iCustomerMapper.toCustomerDto(iCustomerCrud.save(iCustomerMapper.toCustomerEntity(newCustomer)));
    }

    @Override
    public void delete(String cardId) {
        iCustomerCrud.deleteById(cardId);
    }
}
