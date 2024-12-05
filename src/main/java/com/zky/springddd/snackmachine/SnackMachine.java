package com.zky.springddd.snackmachine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

public class SnackMachine extends AggregateRoot {

    @Getter
    @Setter
    private Money moneyInside;

    @Getter
    @Setter
    private Money moneyInTransaction;

    private List<Slot> slots;

    public SnackMachine() {
        this.moneyInside = Money.None;
        this.moneyInTransaction = Money.None;
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
        this.moneyInTransaction = Money.add(moneyInTransaction, money);
    }

    public void returnMoney() {
        this.moneyInTransaction = Money.None;
    }

    public void buySnack(int position) {
        Slot slot = getSlot(position);
        double price = slot.getSnackPile().getPrice();
        if (moneyInTransaction.getAmount() < price) {
            throw new IllegalStateException();
        }
        slot.setSnackPile(slot.getSnackPile().subtractOne());
        this.moneyInside = Money.add(moneyInside, moneyInTransaction);
        this.moneyInTransaction = Money.None;
    }

    public void loadSnacks(int position, SnackPile snackPile) {
        Slot slot = slots.stream().filter(s -> s.getPosition() == position).findFirst().orElse(null);
        if (slot != null) {
            slot.setSnackPile(snackPile);
        }
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

    public SnackPile getSnackPile(int position) {
        return getSlot(position).getSnackPile();
    }

    public Slot getSlot(int position) {
        return slots.stream().filter(s -> s.getPosition() == position).findAny().orElse(null);
    }
}
