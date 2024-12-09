package com.zky.springddd.snackmachine;

import com.zky.springddd.common.AggregateRoot;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Snack extends AggregateRoot {

    public static Snack None = new Snack(0, "None");
    public static Snack Chocolate = new Snack(1, "Chocolate");
    public static Snack Soda = new Snack(2, "Soda");
    public static Snack Gum = new Snack(3, "Gum");

    private String name;

    private Snack(long id, String name) {
        setId(id);
        this.name = name;
    }

    public SnackDto convertToSnackDto() {
        SnackDto snackDto = new SnackDto();
        snackDto.setId(getId());
        snackDto.setName(name);
        return snackDto;
    }
}
