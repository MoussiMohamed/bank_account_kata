package com.sg.bank.domain.ports;

import com.sg.bank.domain.model.Transaction;

import java.util.List;

public interface TransactionRepository {
    void save(Transaction tx);

    List<Transaction> findAll();
}
