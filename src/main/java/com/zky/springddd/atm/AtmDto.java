package com.zky.springddd.atm;

import com.zky.springddd.sharedkernel.Money;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class AtmDto {

    @Id
    @GeneratedValue
    private long id;

    private float moneyCharged;
    private int oneCentCount;
    private int tenCentCount;
    private int quarterCount;
    private int oneDollarCount;
    private int fiveDollarCount;
    private int twentyDollarCount;

    @Transient
    private float amount;

    @PostLoad
    public void setAmount() {
        amount = oneCentCount * 0.01f + tenCentCount * 0.10f + quarterCount * 0.25f + oneDollarCount * 1f
                + fiveDollarCount * 5f + twentyDollarCount * 20f;
    }

    public Atm convertToAtm() {
        Atm atm = new Atm();
        atm.setId(id);
        atm.setMoneyCharged(moneyCharged);
        atm.setMoneyInside(new Money(oneCentCount, tenCentCount, quarterCount, oneDollarCount, fiveDollarCount,
                twentyDollarCount));
        return atm;
    }
}
