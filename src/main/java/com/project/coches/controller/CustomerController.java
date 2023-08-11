package com.project.coches.controller;

import com.project.coches.domain.dto.BrandCarDto;
import com.project.coches.domain.dto.CustomerDto;
import com.project.coches.domain.dto.ResponsePassCustomerDto;
import com.project.coches.domain.service.ICustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {

    private final ICustomerService iCustomerService;

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAll(){
        return new ResponseEntity<>(iCustomerService.getAll(), HttpStatus.OK);
        //ResponseEntity.status(HttpStatus.OK).body(iCustomerService.getAll()); segunda opcion
    }

    @GetMapping(path = "/{cardId}")
    public ResponseEntity<CustomerDto> getCustomerByCardId(@PathVariable String cardId){
        return  ResponseEntity.of(iCustomerService.getCustomerByCardId(cardId));
    }

    @GetMapping(path = "/email/{email}")
    public ResponseEntity<CustomerDto> getCustomerByCardEmail(@PathVariable String email){
        return  ResponseEntity.of(iCustomerService.getCustomerByEmail(email));
    }

    @PostMapping
    public ResponseEntity<ResponsePassCustomerDto> save(@RequestBody CustomerDto newCustomerDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCustomerService.save(newCustomerDto));

    }

    @PatchMapping
    public ResponseEntity<CustomerDto> update(@RequestBody CustomerDto updateCustomerDto){
        return ResponseEntity.of(iCustomerService.update(updateCustomerDto));
    }

    @DeleteMapping(path = "/{cardId}")
    public ResponseEntity<Boolean> delete(@PathVariable String cardId){
        return new ResponseEntity<>(this.iCustomerService.delete(cardId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
