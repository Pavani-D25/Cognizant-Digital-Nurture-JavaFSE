package com.cognizant.account;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final List<Account> accounts = new ArrayList<>();

    public AccountController() {
        accounts.add(new Account(1001L, "Savings", 50000.00, 1L));
        accounts.add(new Account(1002L, "Checking", 12000.00, 1L));
        accounts.add(new Account(1003L, "Savings", 75000.00, 2L));
        accounts.add(new Account(1004L, "Fixed Deposit", 200000.00, 3L));
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accounts;
    }

    @GetMapping("/{accountId}")
    public Account getAccountById(@PathVariable Long accountId) {
        return accounts.stream()
            .filter(a -> a.getAccountId().equals(accountId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Account not found: " + accountId));
    }

    @GetMapping("/customer/{customerId}")
    public List<Account> getAccountsByCustomerId(@PathVariable Long customerId) {
        return accounts.stream()
            .filter(a -> a.getCustomerId().equals(customerId))
            .toList();
    }
}
