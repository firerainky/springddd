package com.zky.springddd.snackmachine;

import com.zky.springddd.common.Entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Slot extends Entity {

    private SnackPile snackPile;
    private SnackMachine snackMachine;
    private int position;

    public Slot() {}
    public Slot (SnackMachine snackMachine, int position) {
        this.snackMachine = snackMachine;
        this.position = position;
        this.snackPile = SnackPile.Empty;
    }

    public SlotDto convertToSlotDto() {
        SlotDto slotDto = new SlotDto();
        slotDto.setId(getId());
        slotDto.setQuantity(snackPile.getQuantity());
        slotDto.setPrice(snackPile.getPrice());
        slotDto.setPosition(position);
        slotDto.setSnackDto(snackPile.getSnack().convertToSnackDto());
        return slotDto;
    }
}
