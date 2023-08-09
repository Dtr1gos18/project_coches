package com.project.coches.controller;

import com.project.coches.domain.pojo.BrandCarPojo;
import com.project.coches.domain.service.IBrandCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador rest de marca coche
 */
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/marcas-coches")
public class BrandCarController {

    /**
     * servicio de marca coche
     */
    private final IBrandCarService iBrandCarService;

    @GetMapping
    public ResponseEntity<List<BrandCarPojo>> getAll(){
        return new ResponseEntity<>(iBrandCarService.getAll(), HttpStatus.OK);
        //ResponseEntity.status(HttpStatus.OK).body(iBrandCarService.getAll()); segunda opcion
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BrandCarPojo> getBrandCar(@PathVariable Integer id){
        return  ResponseEntity.of(iBrandCarService.getBrandCar(id));
    }

    @PostMapping
    public ResponseEntity<BrandCarPojo> save(@RequestBody BrandCarPojo newBrandCarPojo){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(iBrandCarService.save(newBrandCarPojo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null);
        }
    }
}
