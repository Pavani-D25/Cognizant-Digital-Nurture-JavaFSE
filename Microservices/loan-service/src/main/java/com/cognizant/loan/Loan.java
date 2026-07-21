package com.cognizant.loan;

public class Loan {
    private Long loanId;
    private String loanType;
    private double amount;
    private double interestRate;
    private Long customerId;

    public Loan() {}

    public Loan(Long loanId, String loanType, double amount, double interestRate, Long customerId) {
        this.loanId = loanId;
        this.loanType = loanType;
        this.amount = amount;
        this.interestRate = interestRate;
        this.customerId = customerId;
    }

    public Long getLoanId() { return loanId; }
    public void setLoanId(Long loanId) { this.loanId = loanId; }

    public String getLoanType() { return loanType; }
    public void setLoanType(String loanType) { this.loanType = loanType; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public double getInterestRate() { return interestRate; }
    public void setInterestRate(double interestRate) { this.interestRate = interestRate; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }
}
