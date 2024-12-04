package com.zky.springddd.snackmachine;

import java.util.Arrays;
import java.util.List;

import com.zky.springddd.common.Entity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SnackMachine extends Entity {

    private Money moneyInside;
    private Money moneyInTransaction;

    public SnackMachine() {
        this.moneyInside = Money.None;
        this.moneyInTransaction = Money.None;
    }

    public void insertMoney(Money money) {
        List<Money> coinsAndNotes = Arrays.asList(Money.Cent, Money.TenCent, Money.Quarter, Money.Dollar, Money.FiveDollar, Money.TwentyDollar);
        if (!coinsAndNotes.contains(money)) {
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

    public SnackMachineDto convertToDto() {
        SnackMachineDto dto = new SnackMachineDto();
        dto.setId(id);
        dto.setMoneyInTransaction(moneyInTransaction.getAmount());
        dto.setOneCentCount(moneyInside.getOneCentCount());
        dto.setTenCentCount(moneyInside.getTenCentCount());
        dto.setQuarterCount(moneyInside.getQuarterCount());
        dto.setOneDollarCount(moneyInside.getOneDollarCount());
        dto.setFiveDollarCount(moneyInside.getFiveDollarCount());
        dto.setTwentyDollarCount(moneyInside.getTwentyDollarCount());
        return dto;
    }
}
