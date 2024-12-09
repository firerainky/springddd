package com.zky.springddd.snackmachine;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SlotDto {

    @Id
    @GeneratedValue
    private long id;

    private int quantity;
    private float price;
    private int position;

    @OneToOne(cascade = CascadeType.ALL)
    private SnackDto snackDto;

    public Slot convertToSlot() {
        Slot slot = new Slot();
        slot.setId(id);
        slot.setPosition(position);
        slot.setSnackPile(new SnackPile(snackDto.convertToSnack(), quantity, price));
        return slot;
    }
}
