package com.zky.springddd.snackmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnackMachine extends AggregateRoot {

    private Money moneyInside;    
    private double moneyInTransaction;
    private List<Slot> slots;

    public SnackMachine() {
        this.moneyInside = Money.None;
        this.moneyInTransaction = 0;
        slots = new ArrayList<>();
        slots.add(new Slot(this, 1));
        slots.add(new Slot(this, 2));
        slots.add(new Slot(this, 3));
    }

    public void insertMoney(Money money) {
        List<Money> coinsAndNotes = Arrays.asList(Money.Cent, Money.TenCent, Money.Quarter, Money.Dollar,
                Money.FiveDollar, Money.TwentyDollar);
        if (!coinsAndNotes.contains(money)) {
            throw new IllegalStateException();
        }
        this.moneyInside = Money.add(moneyInside, money);
        this.moneyInTransaction += money.getAmount();
    }

    public void returnMoney() {
        Money moneyToReturn = moneyInside.allocate(moneyInTransaction);
        this.moneyInside = moneyInside.substract(moneyToReturn);
        this.moneyInTransaction = 0;
    }

    public void loadMoney(Money money) {
        this.moneyInside = Money.add(moneyInside, money);
    }

    public void buySnack(int position) {
        Slot slot = getSlot(position);
        double price = slot.getSnackPile().getPrice();
        if (moneyInTransaction < price) {
            throw new IllegalStateException();
        }
        slot.setSnackPile(slot.getSnackPile().subtractOne());
        Money change = moneyInside.allocate(moneyInTransaction - price);
        moneyInside = moneyInside.substract(change);
        moneyInTransaction = 0;
    }

    public void loadSnacks(int position, SnackPile snackPile) {
        Slot slot = slots.stream().filter(s -> s.getPosition() == position).findFirst().orElse(null);
        if (slot != null) {
            slot.setSnackPile(snackPile);
        }
    }

    public SnackMachineDto convertToSnackMachineDto() {
        SnackMachineDto dto = new SnackMachineDto();
        dto.setId(id);
        dto.setMoneyInTransaction(moneyInTransaction);
        dto.setOneCentCount(moneyInside.getOneCentCount());
        dto.setTenCentCount(moneyInside.getTenCentCount());
        dto.setQuarterCount(moneyInside.getQuarterCount());
        dto.setOneDollarCount(moneyInside.getOneDollarCount());
        dto.setFiveDollarCount(moneyInside.getFiveDollarCount());
        dto.setTwentyDollarCount(moneyInside.getTwentyDollarCount());
        return dto;
    }

    public SnackPile getSnackPile(int position) {
        return getSlot(position).getSnackPile();
    }

    private Slot getSlot(int position) {
        return slots.stream().filter(s -> s.getPosition() == position).findAny().orElse(null);
    }
}
