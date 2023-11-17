package com.nfb.buildingblocks.core.usecases.impl;

import com.nfb.buildingblocks.core.domain.BaseEntity;
import com.nfb.buildingblocks.core.usecases.GenericService;
import com.nfb.modules.stakeholders.core.domain.user.Patike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceGenericImpl<T extends BaseEntity> implements GenericService<T> {
    @Autowired
    protected JpaRepository<T,Long> genericRepository;
    @Override
    public List<T> findAll() throws Exception {
        try {
            return genericRepository.findAll();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public T save(T entity) throws Exception {
        try {
            return genericRepository.save(entity);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void delete(Long id) throws Exception {
        try {
            genericRepository.deleteById(id);
        } catch (Exception e) {
            throw e;
        }
    }
}


