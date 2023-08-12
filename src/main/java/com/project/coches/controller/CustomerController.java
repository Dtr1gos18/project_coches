package com.project.coches.controller;

import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.dto.ResponsePassCustomerDto;
import com.project.coches.domain.useCase.ICustomerUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final ICustomerUseCase iCustomerUseCase;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll(){
        return new ResponseEntity<>(iCustomerUseCase.getAll(), HttpStatus.OK);
        //ResponseEntity.status(HttpStatus.OK).body(iCustomerService.getAll()); segunda opcion
    }

    @GetMapping(path = "/{cardId}")
    public ResponseEntity<CustomerDto> getCustomerByCardId(@PathVariable String cardId){
        return  ResponseEntity.of(iCustomerUseCase.getCustomerByCardId(cardId));
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<CustomerDto> getCustomerByCardEmail(@PathVariable String email){
        return  ResponseEntity.of(iCustomerUseCase.getCustomerByEmail(email));
    }

    @PostMapping
    public ResponseEntity<ResponsePassCustomerDto> save(@RequestBody CustomerDto newCustomerDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCustomerUseCase.save(newCustomerDto));

    }

    @PatchMapping
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto updateCustomerDto){
        return ResponseEntity.of(iCustomerUseCase.update(updateCustomerDto));
    }

    @DeleteMapping(path = "/{cardId}")
    public ResponseEntity<Boolean> delete(@PathVariable String cardId){
        return new ResponseEntity<>(this.iCustomerUseCase.delete(cardId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
