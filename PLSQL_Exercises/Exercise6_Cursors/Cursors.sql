-- ============================================
-- Exercise 6: Cursors
-- ============================================

-- Scenario 1: GenerateMonthlyStatements using explicit cursor
DECLARE
    CURSOR cur_monthly_transactions IS
        SELECT c.CustomerID, c.Name, t.TransactionID, t.TransactionDate, 
               t.Amount, t.TransactionType
        FROM Transactions t
        JOIN Accounts a ON t.AccountID = a.AccountID
        JOIN Customers c ON a.CustomerID = c.CustomerID
        WHERE TRUNC(t.TransactionDate, 'MM') = TRUNC(SYSDATE, 'MM')
        ORDER BY c.CustomerID, t.TransactionDate;
    
    v_current_customer NUMBER := 0;
    v_total_deposits NUMBER := 0;
    v_total_withdrawals NUMBER := 0;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Monthly Statements for ' || TO_CHAR(SYSDATE, 'MONTH YYYY') || ' ===');
    DBMS_OUTPUT.PUT_LINE('');
    
    FOR txn_rec IN cur_monthly_transactions LOOP
        -- Print header when customer changes
        IF txn_rec.CustomerID != v_current_customer THEN
            -- Print summary for previous customer
            IF v_current_customer != 0 THEN
                DBMS_OUTPUT.PUT_LINE('  -----------------------------------');
                DBMS_OUTPUT.PUT_LINE('  Total Deposits:   $' || v_total_deposits);
                DBMS_OUTPUT.PUT_LINE('  Total Withdrawals: $' || v_total_withdrawals);
                DBMS_OUTPUT.PUT_LINE('');
            END IF;
            
            v_current_customer := txn_rec.CustomerID;
            v_total_deposits := 0;
            v_total_withdrawals := 0;
            
            DBMS_OUTPUT.PUT_LINE('Customer ID: ' || txn_rec.CustomerID || ' | Name: ' || txn_rec.Name);
            DBMS_OUTPUT.PUT_LINE('-----------------------------------');
        END IF;
        
        -- Print transaction detail
        DBMS_OUTPUT.PUT_LINE('  ' || TO_CHAR(txn_rec.TransactionDate, 'DD-MON-YYYY') || 
                             ' | ' || RPAD(txn_rec.TransactionType, 12) || 
                             ' | $' || txn_rec.Amount);
        
        -- Accumulate totals
        IF txn_rec.TransactionType = 'Deposit' THEN
            v_total_deposits := v_total_deposits + txn_rec.Amount;
        ELSE
            v_total_withdrawals := v_total_withdrawals + txn_rec.Amount;
        END IF;
    END LOOP;
    
    -- Print last customer summary
    IF v_current_customer != 0 THEN
        DBMS_OUTPUT.PUT_LINE('  -----------------------------------');
        DBMS_OUTPUT.PUT_LINE('  Total Deposits:   $' || v_total_deposits);
        DBMS_OUTPUT.PUT_LINE('  Total Withdrawals: $' || v_total_withdrawals);
    END IF;
END;
/

-- Scenario 2: ApplyAnnualFee using explicit cursor
DECLARE
    CURSOR cur_all_accounts IS
        SELECT AccountID, CustomerID, AccountType, Balance
        FROM Accounts
        FOR UPDATE OF Balance;
    
    v_annual_fee NUMBER := 25.00;
    v_new_balance NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Applying Annual Maintenance Fee ===');
    
    FOR acc_rec IN cur_all_accounts LOOP
        v_new_balance := acc_rec.Balance - v_annual_fee;
        
        UPDATE Accounts
        SET Balance = v_new_balance
        WHERE CURRENT OF cur_all_accounts;
        
        DBMS_OUTPUT.PUT_LINE('Account ' || acc_rec.AccountID || 
                             ' (' || acc_rec.AccountType || ')' ||
                             ': $' || acc_rec.Balance || 
                             ' - $' || v_annual_fee || 
                             ' fee = $' || v_new_balance);
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Annual fee applied to all accounts.');
END;
/

-- Scenario 3: UpdateLoanInterestRates using explicit cursor
DECLARE
    CURSOR cur_all_loans IS
        SELECT LoanID, CustomerID, LoanAmount, InterestRate, StartDate, EndDate
        FROM Loans;
    
    v_new_rate NUMBER;
    v_years_since_start NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Updating Loan Interest Rates Based on New Policy ===');
    
    FOR loan_rec IN cur_all_loans LOOP
        -- Calculate years since loan start
        v_years_since_start := TRUNC(MONTHS_BETWEEN(SYSDATE, loan_rec.StartDate) / 12);
        
        -- New policy: reduce rate by 0.5% for each year the loan has been active
        -- Minimum rate is 2%
        v_new_rate := loan_rec.InterestRate - (v_years_since_start * 0.5);
        
        IF v_new_rate < 2.0 THEN
            v_new_rate := 2.0;
        END IF;
        
        UPDATE Loans
        SET InterestRate = v_new_rate
        WHERE LoanID = loan_rec.LoanID;
        
        DBMS_OUTPUT.PUT_LINE('Loan ' || loan_rec.LoanID || 
                             ': Years Active=' || v_years_since_start ||
                             ' | Old Rate=' || loan_rec.InterestRate || '%' ||
                             ' | New Rate=' || v_new_rate || '%');
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Interest rate update complete based on new policy.');
END;
/
