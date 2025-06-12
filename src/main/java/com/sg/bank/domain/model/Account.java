package com.sg.bank.domain.model;

import lombok.Getter;

import java.util.*;

/**
 * Root entity holding the full transaction history.
 */
public class Account {
    @Getter
    private final List<Transaction> history = new ArrayList<>();

    /**
     * Current balance (ZERO if no operations yet).
     */
    public Money balance() {
        return history.isEmpty() ? Money.ZERO : history.get(history.size() - 1).balance();
    }

    /**
     * Internal helper used by AccountService.
     */
    public void record(Transaction tx) {
        history.add(tx);
    }

    /**
     * Read‑only view of the history.
     */
    public List<Transaction> snapshot() {
        return Collections.unmodifiableList(history);
    }
}
