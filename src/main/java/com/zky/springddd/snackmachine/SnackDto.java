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
        Snack snack = new Snack();
        snack.setId(id);
        snack.setName(name);
        return snack;
    }
}
