package com.zky.springddd.snackmachine;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SnackMachineTest {

    @Test
    public void returnMoney_emptiesMoneyInTransaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.setMoneyInTransaction(Money.Dollar);

        snackMachine.returnMoney();

        assertEquals(Money.None, snackMachine.getMoneyInTransaction());
    }
}
