package com.sg.bank.domain.ports;

import com.sg.bank.domain.model.Transaction;

import java.util.List;

public interface StatementPrinter {
    void print(List<Transaction> transactions);
}
