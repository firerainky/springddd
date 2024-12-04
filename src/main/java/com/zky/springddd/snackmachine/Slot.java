package com.zky.springddd.snackmachine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Slot {

    private Snack snack;
    private int quantity;
    private double price;
    private SnackMachine snackMachine;
    private int position;
}
