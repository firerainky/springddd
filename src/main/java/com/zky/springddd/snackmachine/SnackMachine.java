package com.zky.springddd.snackmachine;

import com.zky.springddd.common.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnackMachine extends Entity {

    private Money moneyInside;
    private Money moneyInTransaction;

    public SnackMachine() {
        this.moneyInside = Money.None;
        this.moneyInTransaction = Money.None;
    }

    public void insertMoney(Money money) {
        this.moneyInTransaction = Money.add(moneyInTransaction, money);
    }

    public void returnMoney() {
        this.moneyInTransaction = Money.None;
    }

    public void buySnack() {
        this.moneyInside = Money.add(moneyInside, moneyInTransaction);
        // this.moneyInTransaction = new Money(0, 0, 0, 0, 0, 0);
    }
}
