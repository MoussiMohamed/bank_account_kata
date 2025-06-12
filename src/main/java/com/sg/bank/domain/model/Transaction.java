package com.sg.bank.domain.model;

import java.time.LocalDateTime;

/**
 * One movement (credit or debit) and resulting balance.
 */
public record Transaction(LocalDateTime dateTime, Money amount, Money balance) {

    /**
     * Create a positive deposit entry.
     */
    public static Transaction deposit(LocalDateTime ts, Money amt, Money bal) {
        if (amt.isNegative()) throw new IllegalArgumentException("Deposit must be positive");
        return new Transaction(ts, amt, bal);
    }

    /**
     * Create a negative withdrawal entry.
     */
    public static Transaction withdrawal(LocalDateTime ts, Money amt, Money bal) {
        if (amt.isNegative()) throw new IllegalArgumentException("Withdrawal must be positive");
        return new Transaction(ts, amt.negate(), bal);
    }
}
