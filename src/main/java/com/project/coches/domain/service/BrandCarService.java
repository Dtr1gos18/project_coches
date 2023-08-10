package com.project.coches.domain.service;

import com.project.coches.domain.pojo.BrandCarPojo;
import com.project.coches.domain.repository.IBrandCarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BrandCarService implements IBrandCarService {

    private final IBrandCarRepository iBrandCarRepository;
    @Override
    public List<BrandCarPojo> getAll() {
        return iBrandCarRepository.getAll();
    }

    @Override
    public Optional<BrandCarPojo> getBrandCar(Integer id) {
        return iBrandCarRepository.getBrandCar(id);
    }

    @Override
    public BrandCarPojo save(BrandCarPojo newBrandCar) {
        return iBrandCarRepository.save(newBrandCar);
    }

    @Override
    public Optional<BrandCarPojo> update(BrandCarPojo newBrandCar) {
        if (iBrandCarRepository.getBrandCar(newBrandCar.getId()).isEmpty()){
            return Optional.empty();
        }
        return Optional.of(iBrandCarRepository.save(newBrandCar));
    }

    @Override
    public boolean delete(Integer idBrandCar) {
        if (iBrandCarRepository.getBrandCar(idBrandCar).isEmpty()) {
            return false;
        }
        iBrandCarRepository.delete(idBrandCar);
        return true;
    }
}
