package com.zky.springddd.common;

public abstract class ValueObject<T> {

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        T valueObject = (T) obj;
        return equalsCore(valueObject);
    }

    // protected abstract boolean equalsCore(ValueObject<T> other);

    @Override
    public int hashCode() {
        return this.hashCodeCore();
    }

    protected abstract int hashCodeCore();

    protected abstract boolean equalsCore(T other);
}
