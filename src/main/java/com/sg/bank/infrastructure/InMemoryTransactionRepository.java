package com.sg.bank.infrastructure;

import com.sg.bank.domain.model.Transaction;
import com.sg.bank.domain.ports.TransactionRepository;

import java.util.*;

/**
 * Simple in‑memory store, fast and framework‑free.
 */
public class InMemoryTransactionRepository implements TransactionRepository {
    private final List<Transaction> store = new ArrayList<>();

    @Override
    public void save(Transaction tx) {
        store.add(tx);
    }

    @Override
    public List<Transaction> findAll() {
        return List.copyOf(store);
    }
}
