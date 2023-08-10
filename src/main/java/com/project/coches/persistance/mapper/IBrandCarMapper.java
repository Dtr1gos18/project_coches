package com.project.coches.persistance.mapper;

import com.project.coches.domain.dto.BrandCarDto;
import com.project.coches.persistance.entity.BrandCarEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IBrandCarMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")
    BrandCarDto toMarcaCochePojo(BrandCarEntity marcaEntity);

    @InheritInverseConfiguration
    BrandCarEntity toMarcaCocheEntity(BrandCarDto marcaPojo);

    /**
     * Retorna una lista de marcas de coche transformada a pojo de una lista de entidades
     * @param marcasCocheEntity entidad a transformar
     * @return Lista Tranformada
     */
    List<BrandCarDto> toMarcasCochePojo(List<BrandCarEntity> marcasCocheEntity);
}
