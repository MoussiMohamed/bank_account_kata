package com.sg.bank;

import com.sg.bank.domain.model.Money;
import com.sg.bank.domain.service.AccountService;
import com.sg.bank.infrastructure.*;

import java.time.Clock;

/**
 * Simple CLI entry point: executes a few sample operations then prints the
 * statement to the console. Keeps the demo minimal—no Spring, no DB.
 */
public class Application {

    public static void main(String[] args) {

        // Build core service with in‑memory adapters
        var service = new AccountService(
                new InMemoryTransactionRepository(),
                new ConsoleStatementPrinter(),
                Clock.systemDefaultZone());

        // Demo scenario
        service.deposit(Money.of(1000));
        service.withdraw(Money.of(200));
        service.deposit(Money.of(500));

        // Display the statement
        service.printStatement();
    }
}
