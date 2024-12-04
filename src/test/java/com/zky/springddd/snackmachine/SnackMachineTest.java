package com.zky.springddd.snackmachine;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class SnackMachineTest {

    @Test
    void returnMoney_emptiesMoneyInTransaction() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(Money.Dollar);

        snackMachine.returnMoney();

        assertEquals(Money.None, snackMachine.getMoneyInTransaction());
    }

    @Test
    void insertMoney_moneyGoesToMoneyInTransaction() {
        SnackMachine snackMachine = new SnackMachine();

        snackMachine.insertMoney(Money.Dollar);
        snackMachine.insertMoney(Money.Cent);

        assertEquals(1.01, snackMachine.getMoneyInTransaction().getAmount());
    }

    @Test
    void insertTwoSameCoins_throwsException() {
        SnackMachine snackMachine = new SnackMachine();
        Money twoCents = Money.add(Money.Cent, Money.Cent);

        assertThrows(IllegalStateException.class, () -> {
            snackMachine.insertMoney(twoCents);
        });
    }

    @Test
    void buySnack_moneyInTransactionGoesToMoneyInside() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.insertMoney(Money.Dollar);
        snackMachine.insertMoney(Money.Dollar);
        snackMachine.buySnack();

        assertEquals(Money.None, snackMachine.getMoneyInTransaction());
        assertEquals(2.0, snackMachine.getMoneyInside().getAmount());
    }
}
