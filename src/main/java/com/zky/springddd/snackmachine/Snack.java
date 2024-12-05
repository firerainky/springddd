package com.zky.springddd.snackmachine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Snack extends AggregateRoot {

    private String name;

    public Snack() {}

    public Snack(long id, String name) {
        setId(id);
        this.name = name;
    }

    public Snack(String name) {
        this.name = name;
    }

    public SnackDto convertToSnackDto() {
        SnackDto snackDto = new SnackDto();
        snackDto.setId(getId());
        snackDto.setName(name);
        return snackDto;
    }
}
