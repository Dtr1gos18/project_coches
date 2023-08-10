package com.project.coches.controller;

import com.project.coches.domain.pojo.BrandCarPojo;
import com.project.coches.domain.service.IBrandCarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    /**
     * Devuelve lista de marca coches
     * @return HttpCode Ok con lista de marca coches
     */
    @GetMapping
    public ResponseEntity<List<BrandCarPojo>> getAll(){
        return new ResponseEntity<>(iBrandCarService.getAll(), HttpStatus.OK);
        //ResponseEntity.status(HttpStatus.OK).body(iBrandCarService.getAll()); segunda opcion
    }

    /**
     * Devuelve marca coche dado su id
     * @param id de la marca coche a buscar
     * @return HttpCode ok si la encuentra, HttpCode notFound de lo contrario
     */
    @GetMapping(path = "/{id}")
    public ResponseEntity<BrandCarPojo> getBrandCar(@PathVariable Integer id){
        return  ResponseEntity.of(iBrandCarService.getBrandCar(id));
    }

    /**
     * Crea una nueva marca coche
     * @param newBrandCarPojo nueva marca coche a crear
     * @return HttpCode Created si la guarda correctamente, HttpCode BadRequest de lo contrario
     */
    @PostMapping
    public ResponseEntity<BrandCarPojo> save(@RequestBody BrandCarPojo newBrandCarPojo){
        try{
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(iBrandCarService.save(newBrandCarPojo));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }

    /**
     * Actualiza una marca coche
     * @param updateBrandCarPojo marca coche actualizada
     * @return HttpCode Ok si la actualiza correctamente
     */
    @PatchMapping
    public ResponseEntity<BrandCarPojo> update(@RequestBody BrandCarPojo updateBrandCarPojo){
        return ResponseEntity.of(iBrandCarService.update(updateBrandCarPojo));
    }

    /**
     * Elimina una marca coche
     * @param id de la marca coche a eliminar
     * @return HttpCode Ok si la elimina, HttpCode notFound si no existe
     */
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        return new ResponseEntity<>(this.iBrandCarService.delete(id) ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
}
