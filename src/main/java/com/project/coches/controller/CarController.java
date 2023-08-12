package com.project.coches.controller;

import com.project.coches.domain.dto.CarDto;
import com.project.coches.domain.useCase.ICarUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/cars")
public class CarController {

    private final ICarUseCase iCarUseCase;

    @GetMapping
    public ResponseEntity<List<CarDto>> getAll(){
        return ResponseEntity.ok(iCarUseCase.getAll());
        //ResponseEntity.status(HttpStatus.OK).body(iCustomerService.getAll()); segunda opcion
    }

    @GetMapping(path = "/brand-car/{idBrandCar}")
    public ResponseEntity<List<CarDto>> getByIdBrandCar(@PathVariable Integer idBrandCar){
        return new ResponseEntity<>(iCarUseCase.getByIdBrandCar(idBrandCar), HttpStatus.OK);
        //ResponseEntity.status(HttpStatus.OK).body(iCustomerService.getAll()); segunda opcion
    }

    @GetMapping(path = "/price/{priceCar}")
    public ResponseEntity<List<CarDto>> getCarByPriceLessThan(@PathVariable Double priceCar){
        return ResponseEntity.ok(iCarUseCase.getCarByPriceLessThan(priceCar));
        //ResponseEntity.status(HttpStatus.OK).body(iCarService.getAll()); segunda opcion
    }

    @GetMapping(path = "/{idCar}")
    public ResponseEntity<CarDto> getCar(@PathVariable Integer idCar){
        return  ResponseEntity.of(iCarUseCase.getCar(idCar));
    }

    @PostMapping
    public ResponseEntity<CarDto> save(@RequestBody CarDto newCarDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(iCarUseCase.save(newCarDto));

    }

    @DeleteMapping(path = "/{carId}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer carId){
        return new ResponseEntity<>(this.iCarUseCase.delete(carId) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

}
