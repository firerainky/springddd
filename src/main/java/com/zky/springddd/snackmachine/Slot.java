package com.zky.springddd.snackmachine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Slot {

    private SnackPile snackPile;
    private SnackMachine snackMachine;
    private int position;

    public Slot() {}
    public Slot (SnackMachine snackMachine, int position) {
        this.snackMachine = snackMachine;
        this.position = position;
        this.snackPile = new SnackPile(null, 0, 0);
    }
}
