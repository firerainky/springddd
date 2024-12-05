package com.zky.springddd.snackmachine;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class SnackDto {

    @Id
    private long id;
    private String name;

    public Snack convertToSnack() {
        if (id == 0) {
            return Snack.None;
        } else if (id == 1) {
            return Snack.Chocolate;
        } else if (id == 2) {
            return Snack.Soda;
        } else if (id == 3) {
            return Snack.Gum;
        } else {
            throw new IllegalStateException();
        }
    }
}
