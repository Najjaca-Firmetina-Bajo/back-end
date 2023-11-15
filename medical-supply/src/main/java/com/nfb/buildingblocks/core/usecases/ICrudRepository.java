package com.nfb.buildingblocks.core.usecases;
import com.nfb.buildingblocks.core.domain.Entity;

import java.util.List;

public interface ICrudRepository<TEntity extends Entity> {
    List<TEntity> getAll();
    TEntity get(long id);
    TEntity create(TEntity entity);
    TEntity update(TEntity entity);
    void delete(long id);
}
