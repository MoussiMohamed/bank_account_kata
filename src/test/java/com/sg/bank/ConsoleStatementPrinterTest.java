package com.sg.bank;

import com.sg.bank.domain.model.*;
import com.sg.bank.infrastructure.ConsoleStatementPrinter;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Verifies that the statement printer formats and orders entries correctly.
 */
class ConsoleStatementPrinterTest {

    @Test
    void prints_newest_transaction_first() {
        ConsoleStatementPrinter printer = new ConsoleStatementPrinter();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        // build a tiny in‑memory statement
        List<Transaction> txs = List.of(
                new Transaction(LocalDateTime.of(2025, 6, 12, 0, 0), Money.of(50), Money.of(50)),
                new Transaction(LocalDateTime.of(2025, 6, 10, 0, 0), Money.of(-20), Money.of(30))
        );
        printer.print(txs);

        String expected = """
                DATE & TIME         | AMOUNT   | BALANCE
                12/06/2025 00:00:00 |    50.00 | 50.00
                10/06/2025 00:00:00 |   -20.00 | 30.00
                """.trim();

        assertEquals(expected, buffer.toString().trim());
    }
}
