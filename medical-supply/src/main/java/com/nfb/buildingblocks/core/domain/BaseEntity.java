package com.nfb.buildingblocks.core.domain;

import jakarta.persistence.*;

import java.util.Objects;

import static jakarta.persistence.InheritanceType.TABLE_PER_CLASS;
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @SequenceGenerator(name = "mySeqGen", sequenceName = "mySeq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseEntity entity)) return false;
        return getId() == entity.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
