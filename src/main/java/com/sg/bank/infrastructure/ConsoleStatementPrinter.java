package com.sg.bank.infrastructure;

import com.sg.bank.domain.model.Transaction;
import com.sg.bank.domain.ports.StatementPrinter;

import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Dump the statement to System.out (CLI demo).
 */
public class ConsoleStatementPrinter implements StatementPrinter {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public void print(List<Transaction> txs) {
        System.out.println("DATE & TIME         | AMOUNT   | BALANCE");
        txs.stream()
                .sorted(Comparator.comparing(Transaction::dateTime).reversed())
                .forEach(tx -> System.out.printf("%s | %8s | %s%n", FMT.format(tx.dateTime()), tx.amount(), tx.balance()));
    }
}
