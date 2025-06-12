package com.sg.bank;

import com.sg.bank.domain.model.*;
import com.sg.bank.domain.service.AccountService;
import com.sg.bank.infrastructure.InMemoryTransactionRepository;
import org.junit.jupiter.api.*;

import java.time.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for core business behaviour.
 */
class AccountServiceTest {

    private InMemoryTransactionRepository repo;
    private AccountService service;

    @BeforeEach
    void init() {
        repo = new InMemoryTransactionRepository();
        // use a fixed clock for deterministic timestamps in tests
        Clock fixedClock = Clock.fixed(
                LocalDateTime.of(2025, 6, 10, 0, 0).toInstant(ZoneOffset.UTC),
                ZoneOffset.UTC);
        service = new AccountService(repo, txs -> {
        }, fixedClock);
    }

    @Test
    void deposit_updates_balance() {
        service.deposit(Money.of(50));
        assertEquals("50.00", repo.findAll().get(0).balance().toString());
    }

    @Test
    void withdraw_updates_balance() {
        service.deposit(Money.of(100));
        service.withdraw(Money.of(40));
        var all = repo.findAll();
        // last transaction after withdrawal should show remaining balance 60
        assertEquals("60.00", all.get(all.size() - 1).balance().toString());
    }

    @Test
    void overdraft_is_blocked() {
        assertThrows(IllegalStateException.class, () -> service.withdraw(Money.of(10)));
    }
}
