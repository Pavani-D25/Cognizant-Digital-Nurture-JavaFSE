-- ============================================
-- Exercise 3: Stored Procedures
-- ============================================

-- Scenario 1: ProcessMonthlyInterest for savings accounts
CREATE OR REPLACE PROCEDURE ProcessMonthlyInterest AS
    v_interest_rate NUMBER := 1.0; -- 1% interest rate
    v_old_balance NUMBER;
    v_new_balance NUMBER;
    v_interest_amount NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Processing Monthly Interest ===');
    
    FOR acc_rec IN (SELECT AccountID, Balance 
                    FROM Accounts 
                    WHERE AccountType = 'Savings') LOOP
        
        v_old_balance := acc_rec.Balance;
        v_interest_amount := v_old_balance * (v_interest_rate / 100);
        v_new_balance := v_old_balance + v_interest_amount;
        
        UPDATE Accounts
        SET Balance = v_new_balance,
            LastModified = SYSDATE
        WHERE AccountID = acc_rec.AccountID;
        
        DBMS_OUTPUT.PUT_LINE('Account ' || acc_rec.AccountID || 
                             ': $' || v_old_balance || 
                             ' + $' || v_interest_amount || 
                             ' interest = $' || v_new_balance);
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Monthly interest processing complete.');
END ProcessMonthlyInterest;
/

-- Scenario 2: UpdateEmployeeBonus
CREATE OR REPLACE PROCEDURE UpdateEmployeeBonus(
    p_department VARCHAR2,
    p_bonus_pct NUMBER
) AS
    v_old_salary NUMBER;
    v_new_salary NUMBER;
    v_bonus_amount NUMBER;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Applying ' || p_bonus_pct || '% bonus to ' || p_department || ' Department ===');
    
    FOR emp_rec IN (SELECT EmployeeID, Name, Salary
                    FROM Employees
                    WHERE Department = p_department) LOOP
        
        v_old_salary := emp_rec.Salary;
        v_bonus_amount := v_old_salary * (p_bonus_pct / 100);
        v_new_salary := v_old_salary + v_bonus_amount;
        
        UPDATE Employees
        SET Salary = v_new_salary
        WHERE EmployeeID = emp_rec.EmployeeID;
        
        DBMS_OUTPUT.PUT_LINE('Employee: ' || emp_rec.Name || 
                             ' | Old Salary: $' || v_old_salary || 
                             ' | Bonus: $' || v_bonus_amount || 
                             ' | New Salary: $' || v_new_salary);
    END LOOP;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Bonus update complete for ' || p_department || ' department.');
END UpdateEmployeeBonus;
/

-- Scenario 3: TransferFunds
CREATE OR REPLACE PROCEDURE TransferFunds(
    p_source_acct NUMBER,
    p_dest_acct NUMBER,
    p_amount NUMBER
) AS
    v_source_balance NUMBER;
    v_dest_exists NUMBER;
BEGIN
    -- Validate destination account exists
    SELECT COUNT(*) INTO v_dest_exists FROM Accounts WHERE AccountID = p_dest_acct;
    
    IF v_dest_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20030, 'Destination account does not exist.');
    END IF;
    
    -- Get source balance
    SELECT Balance INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_source_acct;
    
    -- Check sufficient balance
    IF v_source_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20031, 
            'Insufficient balance. Available: $' || v_source_balance || 
            ', Requested: $' || p_amount);
    END IF;
    
    -- Deduct from source
    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_source_acct;
    
    -- Add to destination
    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_dest_acct;
    
    -- Record transactions
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES ((SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions), p_source_acct, SYSDATE, p_amount, 'Debit');
    
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES ((SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions), p_dest_acct, SYSDATE, p_amount, 'Credit');
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer of $' || p_amount || ' from Account ' || p_source_acct || 
                         ' to Account ' || p_dest_acct || ' completed.');
END TransferFunds;
/

-- Test the procedures
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Testing ProcessMonthlyInterest ===');
    ProcessMonthlyInterest;
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '=== Testing UpdateEmployeeBonus ===');
    UpdateEmployeeBonus('IT', 5);
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '=== Testing TransferFunds ===');
    TransferFunds(1, 2, 50);
END;
/
