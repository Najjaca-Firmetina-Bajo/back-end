package com.nfb.buildingblocks.core.domain;

import java.util.Objects;

public abstract class Entity {
    private long id;

    public long getId() {
        return id;
    }
    protected void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Entity entity)) return false;
        return getId() == entity.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
