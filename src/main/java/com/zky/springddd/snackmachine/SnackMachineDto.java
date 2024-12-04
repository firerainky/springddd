package com.zky.springddd.snackmachine;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnackMachineDto {

    private Money moneyInside;
    private Money moneyInTransaction;
    private long id;
}
