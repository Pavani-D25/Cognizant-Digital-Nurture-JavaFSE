-- ============================================
-- Exercise 5: Triggers
-- ============================================

-- Scenario 1: UpdateCustomerLastModified trigger
CREATE OR REPLACE TRIGGER UpdateCustomerLastModified
BEFORE UPDATE ON Customers
FOR EACH ROW
BEGIN
    -- Automatically set LastModified to current date on any update
    :NEW.LastModified := SYSDATE;
END UpdateCustomerLastModified;
/

-- Scenario 2: LogTransaction trigger
-- First, create the AuditLog table
CREATE TABLE AuditLog (
    AuditID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TransactionID NUMBER,
    AccountID NUMBER,
    Amount NUMBER,
    TransactionType VARCHAR2(10),
    AuditTimestamp DATE DEFAULT SYSDATE,
    Action VARCHAR2(20)
);

CREATE OR REPLACE TRIGGER LogTransaction
AFTER INSERT ON Transactions
FOR EACH ROW
BEGIN
    INSERT INTO AuditLog (TransactionID, AccountID, Amount, TransactionType, AuditTimestamp, Action)
    VALUES (:NEW.TransactionID, :NEW.AccountID, :NEW.Amount, :NEW.TransactionType, SYSDATE, 'INSERT');
END LogTransaction;
/

-- Scenario 3: CheckTransactionRules trigger
CREATE OR REPLACE TRIGGER CheckTransactionRules
BEFORE INSERT ON Transactions
FOR EACH ROW
DECLARE
    v_current_balance NUMBER;
BEGIN
    -- Validate deposit is positive
    IF :NEW.TransactionType = 'Deposit' AND :NEW.Amount <= 0 THEN
        RAISE_APPLICATION_ERROR(-20040, 'Deposit amount must be positive. Received: ' || :NEW.Amount);
    END IF;
    
    -- Validate withdrawal does not exceed balance
    IF :NEW.TransactionType = 'Withdrawal' THEN
        SELECT Balance INTO v_current_balance
        FROM Accounts
        WHERE AccountID = :NEW.AccountID;
        
        IF :NEW.Amount > v_current_balance THEN
            RAISE_APPLICATION_ERROR(-20041, 
                'Withdrawal amount $' || :NEW.Amount || 
                ' exceeds available balance $' || v_current_balance);
        END IF;
        
        IF :NEW.Amount <= 0 THEN
            RAISE_APPLICATION_ERROR(-20042, 'Withdrawal amount must be positive. Received: ' || :NEW.Amount);
        END IF;
    END IF;
END CheckTransactionRules;
/

-- Test the triggers
BEGIN
    -- Test UpdateCustomerLastModified
    DBMS_OUTPUT.PUT_LINE('=== Testing UpdateCustomerLastModified ===');
    UPDATE Customers SET Balance = 2000 WHERE CustomerID = 1;
    DBMS_OUTPUT.PUT_LINE('Customer 1 balance updated. LastModified auto-set to SYSDATE.');
    
    -- Test LogTransaction
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '=== Testing LogTransaction ===');
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES ((SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions), 1, SYSDATE, 150, 'Deposit');
    DBMS_OUTPUT.PUT_LINE('Transaction inserted. AuditLog entry created automatically.');
    
    -- Test CheckTransactionRules (valid withdrawal)
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '=== Testing CheckTransactionRules (valid) ===');
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES ((SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions), 1, SYSDATE, 100, 'Withdrawal');
    DBMS_OUTPUT.PUT_LINE('Valid withdrawal of $100 processed.');
END;
/
