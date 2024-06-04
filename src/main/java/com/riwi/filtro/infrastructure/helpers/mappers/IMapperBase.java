package com.riwi.filtro.infrastructure.helpers.mappers;

public interface IMapperBase<E, RQ, RS> {
    public E requestToEntity(RQ request);
    public RS entityToResponse(E entity);
}
