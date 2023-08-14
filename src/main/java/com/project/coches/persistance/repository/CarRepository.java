package com.project.coches.persistance.repository;

import com.project.coches.domain.dto.CarDto;
import com.project.coches.domain.repository.ICarRepository;
import com.project.coches.persistance.crud.ICarCrud;
import com.project.coches.persistance.mapper.ICarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CarRepository implements ICarRepository {

    private  final ICarMapper iCarMapper;

    private final ICarCrud iCarCrud;

    @Override
    public List<CarDto> getAll() {
        return iCarMapper.toCarsDto(iCarCrud.findAll());
    }

    @Override
    public List<CarDto> getByIdBrandCar(Integer idBrandCar) {
        return iCarMapper.toCarsDto(iCarCrud.findAllByBrandCarId(idBrandCar));
    }

    @Override
    public List<CarDto> getCarByPriceLessThan(Double price) {
        return iCarMapper.toCarsDto(iCarCrud.findAllByPriceLessThanEqualOrderByPriceAsc(price));
    }

    @Override
    public Optional<CarDto> getCar(Integer idCar) {
        return iCarCrud.findById(idCar).map(iCarMapper::toCarDto);
    }

    @Override
    public CarDto save(CarDto newCar) {
        return iCarMapper.toCarDto(iCarCrud.save(iCarMapper.toCarEntity(newCar)));
    }

    @Override
    public void delete(Integer idCar) {
        iCarCrud.deleteById(idCar);
    }
}
