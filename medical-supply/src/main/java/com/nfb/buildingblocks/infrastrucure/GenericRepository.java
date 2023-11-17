package com.nfb.buildingblocks.infrastrucure;
import com.nfb.buildingblocks.core.domain.BaseEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GenericRepository<T extends BaseEntity> extends JpaRepository<T, Long> {

}
