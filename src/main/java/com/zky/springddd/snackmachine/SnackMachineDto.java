package com.zky.springddd.snackmachine;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SnackMachineDto {

    @Id
    @GeneratedValue
    private long id;

    private int oneCentCount;
    private int tenCentCount;
    private int quarterCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;

    private double moneyInTransaction;

    public SnackMachine convertToSnackMachine() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.setId(id);
        snackMachine.setMoneyInTransaction(convertAmountToMoney());
        Money moneyInside = new Money(oneCentCount, tenCentCount, quarterCount, oneDollarCount, fiveDollarCount, twentyDollarCount);
        snackMachine.setMoneyInside(moneyInside);
        return snackMachine;
    }

    private Money convertAmountToMoney() {
        double amount = moneyInTransaction;

        int twentyDollarCount = (int) (amount / 20);
        amount = amount % 20;

        int fiveDollarCount = (int) (amount / 5);
        amount = amount % 5;

        int oneDollarCount = (int) amount;
        amount = amount % 1;

        int quarterCount = (int) (amount / 0.25);
        amount = amount % 0.25;

        int tenCentCount = (int) (amount / 0.1);
        amount = amount % 0.1;

        int oneCentCount = (int) (amount / 0.01);

        return new Money(oneCentCount, tenCentCount, quarterCount, oneDollarCount, fiveDollarCount, twentyDollarCount);
    }
}


