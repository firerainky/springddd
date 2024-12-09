package com.zky.springddd.atm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.zky.springddd.sharedkernel.Money;

public class AtmTest {

    @Test
    public void takeMoney_exchangesMoneyWithCommission() {
        Atm atm = new Atm();
        atm.loadMoney(Money.Dollar);
        atm.takeMoney(1);
        assertEquals(0, atm.getMoneyInside().getAmount());
        assertEquals(1.01, atm.getMoneyCharged(), 0.001);
    }

    @Test
    public void takeMoney_commissionAtLeastOneCent() {
        Atm atm = new Atm();
        atm.loadMoney(Money.Cent);
        atm.takeMoney(0.01f);
        assertEquals(atm.getMoneyCharged(), 0.02, 0.001);
    }

    @Test
    public void takeMoney_commissionIsRoundUpToTheNextCent() {
        Atm atm = new Atm();
        atm.loadMoney(Money.Dollar.add(Money.TenCent));
        atm.takeMoney(1.1f);
        assertEquals(atm.getMoneyCharged(), 1.12, 0.01);
    }
}
