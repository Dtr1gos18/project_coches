package com.project.coches.persistance.repository;

import com.project.coches.domain.pojo.BrandCarPojo;
import com.project.coches.domain.repository.IBrandCarRepository;
import com.project.coches.persistance.entity.BrandCarEntity;
import com.project.coches.persistance.mapper.IBrandCarMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class BrandCarRepository implements IBrandCarRepository {

    /**
     * crud de marca coche
     */
    private final IBrandCarCrudRepository iBrandCarCrudRepository;
    /**
     * mapper de marca coche
     */
    private final IBrandCarMapper iBrandCarMapper;

    /**
     * Devuelve una lista con todas las marcas de coches
     * @return lista con marca de coches
     */
    @Override
    public List<BrandCarPojo> getAll() {
        return iBrandCarMapper.toMarcasCochePojo(iBrandCarCrudRepository.findAll()); //se hace el llamado del mapper porque los metodos del crudRepository me devuelven entidades y se necesitan son pojos(DTO)
    }

    /**
     * Devuelve una marca de coche dado su id
     * @param id de marca coche
     * @return Optional de la marca del coche encontrado
     */
    @Override
    public Optional<BrandCarPojo> getBrandCar(Integer id) {
        return iBrandCarCrudRepository.findById(id)//esto me devuelve un optionalEntity pero yo necesito es un optionalPojo y para eso se hace el map
                .map(iBrandCarMapper::toMarcaCochePojo);
        //(brandCarEntity -> iBrandCarMapper.toMarcaCochePojo(brandCarEntity))-forma normal lambda
        //(iBrandCarMapper::toMarcaCochePojo)-aqui se tranforma el lambda a un metodo por referencia
    }

    @Override
    public BrandCarPojo save(BrandCarPojo newBrandCar) {//aqui como se recibe primero un pojo y yo necesito una entidad se debe castear
        BrandCarEntity brandCarEntity = iBrandCarMapper.toMarcaCocheEntity(newBrandCar);//como debo retornar nuevamente un pojo debo mappear la salida
        return iBrandCarMapper.toMarcaCochePojo(iBrandCarCrudRepository.save(brandCarEntity));
    }

    @Override
    public void delete(Integer idBrandCar) {//como no se debe devolver nada no es necesario el uso de mapper
        iBrandCarCrudRepository.deleteById(idBrandCar);
    }
}
