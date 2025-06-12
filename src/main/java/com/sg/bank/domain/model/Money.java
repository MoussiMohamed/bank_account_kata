package com.sg.bank.domain.model;

import java.math.*;
import java.util.Objects;

/**
 * Immutable monetary value with 2‑decimal precision (bankers rounding).
 */
public record Money(BigDecimal value) {
    public static final Money ZERO = new Money(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_EVEN));

    public Money {
        Objects.requireNonNull(value);
    }

    /**
     * Factory helper: Money.of(42.25)
     */
    public static Money of(double v) {
        return new Money(BigDecimal.valueOf(v).setScale(2, RoundingMode.HALF_EVEN));
    }

    /**
     * Add two monetary amounts.
     */
    public Money plus(Money o) {
        return new Money(value.add(o.value));
    }

    /**
     * Subtract the given amount.
     */
    public Money minus(Money o) {
        return new Money(value.subtract(o.value));
    }

    /**
     * Same amount but opposite sign.
     */
    public Money negate() {
        return new Money(value.negate());
    }

    /**
     * Convenient check for negative values.
     */
    public boolean isNegative() {
        return value.signum() < 0;
    }

    @Override
    public String toString() {
        return value.toPlainString();
    }
}
