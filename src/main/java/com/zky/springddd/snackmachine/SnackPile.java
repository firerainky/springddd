package com.zky.springddd.snackmachine;

import com.zky.springddd.common.ValueObject;

import lombok.Getter;

@Getter
public class SnackPile extends ValueObject<SnackPile> {

    public static SnackPile Empty = new SnackPile(Snack.None, 0, 0);

    private Snack snack;
    private int quantity;
    private float price;

    public SnackPile() {}

    public SnackPile(Snack snack, int quantity, float price) {
        if (quantity < 0) {
            throw new IllegalStateException();
        }
        if (price < 0) {
            throw new IllegalStateException();
        }
        this.snack = snack;
        this.quantity = quantity;
        this.price = price;
    }

    public SnackPile subtractOne() {
        return new SnackPile(snack, quantity - 1, price);
    }

    @Override
    protected int hashCodeCore() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Float.floatToIntBits(price);
        result = prime * result + quantity;
        result = prime * result + ((snack == null) ? 0 : snack.hashCode());
        return result;
    }

    @Override
    protected boolean equalsCore(SnackPile other) {
        return price == other.price && quantity == other.quantity && snack.equals(other.snack);
    }

}
