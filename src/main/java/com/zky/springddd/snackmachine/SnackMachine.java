package com.zky.springddd.snackmachine;

import java.util.Arrays;
import java.util.List;

import com.zky.springddd.common.Entity;

import lombok.Getter;

@Getter
public class SnackMachine extends Entity {

    private Money moneyInside;
    private Money moneyInTransaction;

    public SnackMachine() {
        this.moneyInside = Money.None;
        this.moneyInTransaction = Money.None;
    }

    public void insertMoney(Money money) {
        List<Money> validMoneys = Arrays.asList(Money.Cent, Money.TenCent, Money.Quarter, Money.Dollar, Money.FiveDollar, Money.TwentyDollar);
        if (!validMoneys.contains(money)) {
            throw new IllegalStateException();
        }
        this.moneyInTransaction = Money.add(moneyInTransaction, money);
    }

    public void returnMoney() {
        this.moneyInTransaction = Money.None;
    }

    public void buySnack() {
        this.moneyInside = Money.add(moneyInside, moneyInTransaction);
        this.moneyInTransaction = Money.None;
    }
}
