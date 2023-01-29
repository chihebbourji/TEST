package com.pfe.backend.services.mapper;



import java.util.List;
import java.util.stream.Collectors;

public interface GenericMapper<E, D> {

    E mapToEntity(D d);
    D mapToDTO(E e);

     default List<E> mapToEntityList(List<D> ds) {
        return ds.stream().map(this::mapToEntity).collect(Collectors.toList());
    }

    default List<D> mapToDTOList(List<E> entities) {
        return entities.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

}
