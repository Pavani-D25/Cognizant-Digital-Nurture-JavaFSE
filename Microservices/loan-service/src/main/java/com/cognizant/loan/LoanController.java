package com.cognizant.loan;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/loans")
public class LoanController {

    private final List<Loan> loans = new ArrayList<>();

    public LoanController() {
        loans.add(new Loan(5001L, "Home Loan", 2500000.00, 8.5, 1L));
        loans.add(new Loan(5002L, "Personal Loan", 500000.00, 12.0, 1L));
        loans.add(new Loan(5003L, "Car Loan", 800000.00, 9.0, 2L));
        loans.add(new Loan(5004L, "Education Loan", 1500000.00, 7.5, 3L));
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loans;
    }

    @GetMapping("/{loanId}")
    public Loan getLoanById(@PathVariable Long loanId) {
        return loans.stream()
            .filter(l -> l.getLoanId().equals(loanId))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Loan not found: " + loanId));
    }

    @GetMapping("/customer/{customerId}")
    public List<Loan> getLoansByCustomerId(@PathVariable Long customerId) {
        return loans.stream()
            .filter(l -> l.getCustomerId().equals(customerId))
            .toList();
    }
}
