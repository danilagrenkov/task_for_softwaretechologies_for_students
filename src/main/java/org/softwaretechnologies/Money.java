package org.softwaretechnologies;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Integer.MAX_VALUE;

public class Money {
    private final MoneyType type;
    private final BigDecimal amount;

    public Money(MoneyType type, BigDecimal amount) {
        this.type = type;
        this.amount = amount;
    }

    /**
     * Money равны, если одинаковый тип валют и одинаковое число денег до 4 знака после запятой.
     * Округление по правилу: если >= 5, то в большую сторону, иначе - в меньшую.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Money money = (Money) o;

        // Округляем сумму до 4 знаков после запятой
        BigDecimal thisAmount = this.amount != null ? this.amount.setScale(4, RoundingMode.HALF_UP) : null;
        BigDecimal otherAmount = money.amount != null ? money.amount.setScale(4, RoundingMode.HALF_UP) : null;

        // Сравниваем типы и округленные суммы
        return this.type == money.type && (thisAmount != null ? thisAmount.equals(otherAmount) : otherAmount == null);
    }

    /**
     * Формула:
     * (Если amount null 10000, иначе количество денег округленные до 4х знаков * 10000) + :
     * если USD , то 1
     * если EURO, то 2
     * если RUB, то 3
     * если KRONA, то 4
     * если null, то 5
     * Если amount округленный до 4х знаков * 10000 >= (Integer.MaxValue - 5), то хеш равен Integer.MaxValue
     */
    @Override
    public int hashCode() {
        long hashBase = (amount == null ? 10000 : amount.setScale(4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(10000)).longValue());
        int typeCode = (type != null) ? switch (type) {
            case USD -> 1;
            case EURO -> 2;
            case RUB -> 3;
            case KRONA -> 4;
            default -> 5;
        } : 5; // Если type равно null, возвращаем код 5
        long hashValue = hashBase + typeCode;

        return hashValue >= (MAX_VALUE - 5) ? MAX_VALUE : (int) hashValue;
    }

    @Override
    public String toString() {
        if (type == null && amount == null) {
            return "null: null";
        } else if (amount == null) {
            return type.toString() + ": null";
        } else if (type == null) {
            return "null: " + amount.setScale(4, RoundingMode.HALF_UP).toString();
        }
        return type.toString() + ": " + amount.setScale(4, RoundingMode.HALF_UP).toString();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public MoneyType getType() {
        return type;
    }

    public static void main(String[] args) {
        Money money = new Money(MoneyType.EURO, BigDecimal.valueOf(10.00012));
        Money money1 = new Money(MoneyType.USD, BigDecimal.valueOf(10.5000));
        System.out.println(money.toString());
        System.out.println(money1.hashCode());
        System.out.println(money.equals(money1));
    }
}

