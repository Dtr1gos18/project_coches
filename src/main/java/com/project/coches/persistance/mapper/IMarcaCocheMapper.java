package com.project.coches.persistance.mapper;

import com.project.coches.domain.pojo.MarcaCochePojo;
import com.project.coches.persistance.entity.MarcaCocheEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMarcaCocheMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "description", target = "description")

    MarcaCochePojo toMarcaCochePojo(MarcaCocheEntity marcaEntity);

    @InheritInverseConfiguration
    MarcaCocheEntity toMarcaCocheEntity(MarcaCochePojo marcaPojo);

    /**
     * Retorna una lista de marcas de coche transformada a pojo de una lista de entidades
     * @param marcasCocheEntity entidad a transformar
     * @return Lista Tranformada
     */
    List<MarcaCochePojo> toMarcasCochePojo(List<MarcaCocheEntity> marcasCocheEntity);
}
