package com.zky.springddd.snackmachine;

import java.util.List;

import com.zky.springddd.sharedkernel.Money;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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

    private float moneyInTransaction;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "snack_machine_id")
    private List<SlotDto> slotDtoList;

    public SnackMachine convertToSnackMachine() {
        SnackMachine snackMachine = new SnackMachine();
        snackMachine.setId(id);
        snackMachine.setMoneyInTransaction(moneyInTransaction);
        snackMachine.setMoneyInside(new Money(oneCentCount, tenCentCount, quarterCount, oneDollarCount, fiveDollarCount,
                twentyDollarCount));
        snackMachine.setSlots(slotDtoList.stream().map(SlotDto::convertToSlot).toList());
        return snackMachine;
    }
}
