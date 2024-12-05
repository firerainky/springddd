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

        assertEquals(0, snackMachine.getMoneyInTransaction());
    }

    @Test
    void returnMoney_returnsMoneyWithHighestDenominationFirst() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadMoney(Money.Dollar);
        snackMachine.insertMoney(Money.Quarter);
        snackMachine.insertMoney(Money.Quarter);
        snackMachine.insertMoney(Money.Quarter);
        snackMachine.insertMoney(Money.Quarter);

        snackMachine.returnMoney();

        assertEquals(4, snackMachine.getMoneyInside().getQuarterCount());
        assertEquals(0, snackMachine.getMoneyInside().getOneDollarCount());
    }

    @Test
    void insertMoney_moneyGoesToMoneyInTransaction() {
        SnackMachine snackMachine = new SnackMachine();

        snackMachine.insertMoney(Money.Dollar);
        snackMachine.insertMoney(Money.Cent);

        assertEquals(1.01, snackMachine.getMoneyInTransaction());
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
    void buySnack_tradesInsertedMoneyForASnack() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(new Snack("Some snack"), 1, 1));

        snackMachine.insertMoney(Money.Dollar);
        snackMachine.buySnack(1);

        assertEquals(0, snackMachine.getMoneyInTransaction());
        assertEquals(1.0, snackMachine.getMoneyInside().getAmount());
        assertEquals(0, snackMachine.getSnackPile(1).getQuantity());
    }

    @Test
    void buySnack_canNotMakePurchase_ifSnackQuantityIsZero() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(new Snack("Some snack"), 0, 1));
        snackMachine.insertMoney(Money.Dollar);

        assertThrows(IllegalStateException.class, () -> {
            snackMachine.buySnack(1);
        });
    }

    @Test
    void buySnack_canNotMakePurchase_ifNotEnoughMoneyInserted() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(new Snack("Some snack"), 1, 2));
        snackMachine.insertMoney(Money.Dollar);

        assertThrows(IllegalStateException.class, () -> {
            snackMachine.buySnack(1);
        });
    }

    @Test
    void buySnack_changeShouldBeReturn_afterPurchase() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(new Snack("Some snack"), 1, 0.5));
        snackMachine.loadMoney(new Money(0, 10, 0, 0, 0, 0));
        
        snackMachine.insertMoney(Money.Dollar);
        snackMachine.buySnack(1);

        assertEquals(1.5, snackMachine.getMoneyInside().getAmount());
        assertEquals(0, snackMachine.getMoneyInTransaction());
    }

    @Test
    void buySnack_canNotPurchase_whenNotEnoughChange() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.loadSnacks(1, new SnackPile(new Snack("Some snack"), 1, 0.5));
        snackMachine.insertMoney(Money.Dollar);

        assertThrows(IllegalStateException.class, () -> {
            snackMachine.buySnack(1);
        });
    }

    @Test
    void loadSnacks_throwsIllegalStateException_whenPriceIsUnderZero() {
        SnackMachine snackMachine = new SnackMachine();

        assertThrows(IllegalStateException.class, () -> {
            snackMachine.loadSnacks(1, new SnackPile(new Snack("Some snack"), 1, -2));
        });
    }

    @Test
    void loadSnacks_throwsIllegalStateException_whenQuantityIsUnderZero() {
        SnackMachine snackMachine = new SnackMachine();

        assertThrows(IllegalStateException.class, () -> {
            snackMachine.loadSnacks(1, new SnackPile(new Snack("Some snack"), -1, 2));
        });
    }
}
