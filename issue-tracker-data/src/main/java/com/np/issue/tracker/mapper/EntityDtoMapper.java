package com.np.issue.tracker.mapper;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

@Slf4j
public abstract class EntityDtoMapper<E, D> {

    private final ModelMapper mapper;

    protected EntityDtoMapper() {
        mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public abstract Class<E> getEntityClass();

    public abstract Class<D> getDtoClass();

    public D toDto(E entity) {
        D dto = mapper.map(entity, getDtoClass());
        log.debug("Entity {} mapped to dto {}}", entity, dto);
        return dto;
    }

    public E toEntity(D dto) {
        E entity = mapper.map(dto, getEntityClass());
        log.debug("Dto {} mapped to entity {}", dto, entity);
        return entity;
    }
}
