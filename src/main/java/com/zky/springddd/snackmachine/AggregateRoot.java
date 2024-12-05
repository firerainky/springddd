package com.zky.springddd.snackmachine;

import com.zky.springddd.common.Entity;

import lombok.Getter;
import lombok.Setter;

public abstract class AggregateRoot extends Entity {

    @Getter
    @Setter
    private int version;

    // private List<DomainEvent> events = new ArrayList<>();
}
