package com.zky.springddd.common;

import lombok.Getter;
import lombok.Setter;

public abstract class Entity {
    @Getter
    @Setter
    protected long id;

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Entity other = (Entity) obj;
        if (this == other) {
            return true;
        }
        if (this.id == 0 || other.getId() == 0) {
            return false;
        }
        return other.getId() == this.getId();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }
}
