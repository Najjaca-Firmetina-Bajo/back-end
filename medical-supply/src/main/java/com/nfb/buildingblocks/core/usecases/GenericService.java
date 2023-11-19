package com.nfb.buildingblocks.core.usecases;

import java.util.List;

import com.nfb.buildingblocks.core.domain.BaseEntity;

public interface GenericService<T extends BaseEntity>  {

    List<T> findAll() throws Exception;
    T save(T entity) throws Exception;
    void delete(Long id) throws Exception;

}
