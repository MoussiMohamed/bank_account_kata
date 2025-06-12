package com.sg.bank.domain.service;

import com.sg.bank.domain.model.*;
import com.sg.bank.domain.ports.*;
import lombok.extern.slf4j.Slf4j;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * Core use‑case: manage a current account (deposit, withdraw, print).
 */
@Slf4j
public class AccountService {
    private final Account account = new Account();
    private final TransactionRepository repo;
    private final StatementPrinter printer;
    private final Clock clock;

    public AccountService(TransactionRepository repo, StatementPrinter printer, Clock clock) {
        this.repo = repo;
        this.printer = printer;
        this.clock = clock;
    }

    /**
     * Credit the account.
     */
    public void deposit(Money amt) {
        requirePositive(amt);
        record(amt);
    }

    /**
     * Debit the account.
     */
    public void withdraw(Money amt) {
        requirePositive(amt);
        record(amt.negate());
    }

    /**
     * Print a formatted statement via the given printer.
     */
    public void printStatement() {
        printer.print(repo.findAll());
    }

    /**
     * Persist the signed amount using the system clock (seconds precision).
     */
    private void record(Money signed) {
        Money newBal = account.balance().plus(signed);
        if (newBal.isNegative()) throw new IllegalStateException("Overdraft not allowed");

        LocalDateTime ts = LocalDateTime.now(clock).truncatedTo(ChronoUnit.SECONDS);
        Transaction tx = new Transaction(ts, signed, newBal);
        account.record(tx);
        repo.save(tx);
        log.debug("Tx {}", tx);
    }

    private static void requirePositive(Money m) {
        if (m.isNegative()) throw new IllegalArgumentException("Negative amount");
    }
}
