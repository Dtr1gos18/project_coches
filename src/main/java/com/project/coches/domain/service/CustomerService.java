package com.project.coches.domain.service;

import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.dto.ResponsePassCustomerDto;
import com.project.coches.domain.repository.ICustomerRepository;
import com.project.coches.exception.EmailValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CustomerService implements ICustomerService{

    private final ICustomerRepository iCustomerRepository;

    @Override
    public List<CustomerDto> getAll() {
        return iCustomerRepository.getAll();
    }

    @Override
    public Optional<CustomerDto> getCustomerByCardId(String cardId) {
        return iCustomerRepository.getCustomerByCardId(cardId);
    }

    @Override
    public Optional<CustomerDto> getCustomerByEmail(String email) {
        return iCustomerRepository.getCustomerByEmail(email);
    }

    @Override
    public ResponsePassCustomerDto save(CustomerDto newCustomer) {

        if (!newCustomer.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*" +
                "@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")){
            throw new EmailValidationException();
        }
        String passwordGenerate = generateRandomPassword(8);
        newCustomer.setPassword(passwordGenerate);
        newCustomer.setActive(1);
        iCustomerRepository.save(newCustomer);

        return new ResponsePassCustomerDto(passwordGenerate);
    }

    @Override
    public Optional<CustomerDto> update(CustomerDto customerUpdate) {
        if (!customerUpdate.getEmail().matches("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*" +
                "@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$")){
            throw new EmailValidationException();
        }
        if (iCustomerRepository.getCustomerByCardId(customerUpdate.getCardId()).isEmpty()){
            return Optional.empty();
        }
        return Optional.of(iCustomerRepository.save(customerUpdate));
    }

    @Override
    public boolean delete(String cardId) {
        if (iCustomerRepository.getCustomerByCardId(cardId).isEmpty()){
            return false;
        }
        iCustomerRepository.delete(cardId);
        return true;
    }

    //Metodo para generar una contrasenia alfanumerica aleatoria de una longitud especifica
    private String generateRandomPassword(int len){

        final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        SecureRandom random = new SecureRandom();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < len; i++){
            int randomIndex = random.nextInt(chars.length());
            sb.append(chars.charAt(randomIndex));
        }

        return sb.toString();
    }
}
