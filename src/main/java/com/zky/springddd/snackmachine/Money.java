package com.zky.springddd.snackmachine;

import com.zky.springddd.common.ValueObject;

public class Money extends ValueObject<Money> {

    public static Money None = new Money(0, 0, 0, 0, 0, 0);
    public static Money Cent = new Money(1, 0, 0, 0, 0, 0);
    public static Money TenCent = new Money(0, 1, 0, 0, 0, 0);
    public static Money Quarter = new Money(0, 0, 1, 0, 0, 0);
    public static Money Dollar = new Money(0, 0, 0, 1, 0, 0);
    public static Money FiveDollar = new Money(0, 0, 0, 0, 1, 0);
    public static Money TwentyDollar = new Money(0, 0, 0, 0, 0, 1);

    private final int oneCentCount;
    private final int tenCentCount;
    private final int quarterCount;
    private final int oneDollarCount;
    private final int fiveDollarCount;
    private final int twentyDollarCount;
    private double amount;

    public double getAmount() {
        return amount;
    }

    public Money(int oneCentCount, int tenCentCount, int quarterCount, int oneDollarCount, int fiveDollarCount,
            int twentyDollarCount) {

        if (oneCentCount < 0) {
            throw new IllegalArgumentException();
        }
        if (tenCentCount < 0) {
            throw new IllegalArgumentException();
        }
        if (quarterCount < 0) {
            throw new IllegalArgumentException();
        }
        if (oneDollarCount < 0) {
            throw new IllegalArgumentException();
        }
        if (fiveDollarCount < 0) {
            throw new IllegalArgumentException();
        }
        if (twentyDollarCount < 0) {
            throw new IllegalArgumentException();
        }

        this.oneCentCount = oneCentCount;
        this.tenCentCount = tenCentCount;
        this.quarterCount = quarterCount;
        this.oneDollarCount = oneDollarCount;
        this.fiveDollarCount = fiveDollarCount;
        this.twentyDollarCount = twentyDollarCount;
        this.amount = oneCentCount * 0.01 + tenCentCount * 0.1 + quarterCount * 0.25 + oneDollarCount
                + fiveDollarCount * 5 + twentyDollarCount * 20;
    }

    public static Money add(Money money1, Money money2) {
        return new Money(
                money1.oneCentCount + money2.oneCentCount,
                money1.tenCentCount + money2.tenCentCount,
                money1.quarterCount + money2.quarterCount,
                money1.oneDollarCount + money2.oneDollarCount,
                money1.fiveDollarCount + money2.fiveDollarCount,
                money1.twentyDollarCount + money2.twentyDollarCount);
    }

    public Money substract(Money other) {
        return new Money(
                oneCentCount - other.oneCentCount,
                tenCentCount - other.tenCentCount,
                quarterCount - other.quarterCount,
                oneDollarCount - other.oneDollarCount,
                fiveDollarCount - other.fiveDollarCount,
                twentyDollarCount - other.twentyDollarCount);
    }

    @Override
    protected int hashCodeCore() {
        int hashCode = oneCentCount;
        hashCode = (hashCode * 397) ^ tenCentCount;
        hashCode = (hashCode * 397) ^ quarterCount;
        hashCode = (hashCode * 397) ^ oneDollarCount;
        hashCode = (hashCode * 397) ^ fiveDollarCount;
        hashCode = (hashCode * 397) ^ twentyDollarCount;
        return hashCode;
    }

    @Override
    protected boolean equalsCore(Money other) {
        return oneCentCount == other.oneCentCount &&
                tenCentCount == other.tenCentCount &&
                quarterCount == other.quarterCount &&
                oneDollarCount == other.oneDollarCount &&
                fiveDollarCount == other.fiveDollarCount &&
                twentyDollarCount == other.twentyDollarCount;
    }
}
