-- ============================================
-- Exercise 2: Error Handling
-- ============================================

-- First, create an ErrorLog table for logging errors
CREATE TABLE ErrorLog (
    ErrorID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    ErrorDate DATE DEFAULT SYSDATE,
    ProcedureName VARCHAR2(100),
    ErrorMessage VARCHAR2(500),
    ErrorStack VARCHAR2(1000)
);

-- Scenario 1: SafeTransferFunds procedure
CREATE OR REPLACE PROCEDURE SafeTransferFunds(
    p_source_account NUMBER,
    p_dest_account NUMBER,
    p_amount NUMBER
) AS
    v_source_balance NUMBER;
    v_dest_balance NUMBER;
    v_source_exists NUMBER;
    v_dest_exists NUMBER;
BEGIN
    -- Validate accounts exist
    SELECT COUNT(*) INTO v_source_exists FROM Accounts WHERE AccountID = p_source_account;
    SELECT COUNT(*) INTO v_dest_exists FROM Accounts WHERE AccountID = p_dest_account;
    
    IF v_source_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20001, 'Source account ' || p_source_account || ' does not exist');
    END IF;
    
    IF v_dest_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20002, 'Destination account ' || p_dest_account || ' does not exist');
    END IF;
    
    -- Get source balance with locking
    SELECT Balance INTO v_source_balance
    FROM Accounts
    WHERE AccountID = p_source_account
    FOR UPDATE;
    
    -- Check sufficient funds
    IF v_source_balance < p_amount THEN
        RAISE_APPLICATION_ERROR(-20003, 'Insufficient funds. Available: $' || v_source_balance || ', Requested: $' || p_amount);
    END IF;
    
    -- Deduct from source
    UPDATE Accounts
    SET Balance = Balance - p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_source_account;
    
    -- Add to destination
    UPDATE Accounts
    SET Balance = Balance + p_amount,
        LastModified = SYSDATE
    WHERE AccountID = p_dest_account;
    
    -- Log the transfer
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES ((SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions), p_source_account, SYSDATE, p_amount, 'Transfer Out');
    
    INSERT INTO Transactions (TransactionID, AccountID, TransactionDate, Amount, TransactionType)
    VALUES ((SELECT NVL(MAX(TransactionID), 0) + 1 FROM Transactions), p_dest_account, SYSDATE, p_amount, 'Transfer In');
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Transfer of $' || p_amount || ' completed successfully.');
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        -- Log error to ErrorLog table
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage, ErrorStack)
        VALUES ('SafeTransferFunds', SQLERRM, DBMS_UTILITY.FORMAT_ERROR_BACKTRACE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END SafeTransferFunds;
/

-- Scenario 2: UpdateSalary procedure
CREATE OR REPLACE PROCEDURE UpdateSalary(
    p_emp_id NUMBER,
    p_increase_pct NUMBER
) AS
    v_current_salary NUMBER;
    v_emp_exists NUMBER;
    v_new_salary NUMBER;
BEGIN
    -- Check if employee exists
    SELECT COUNT(*) INTO v_emp_exists FROM Employees WHERE EmployeeID = p_emp_id;
    
    IF v_emp_exists = 0 THEN
        RAISE_APPLICATION_ERROR(-20010, 'Employee ID ' || p_emp_id || ' does not exist');
    END IF;
    
    -- Get current salary
    SELECT Salary INTO v_current_salary
    FROM Employees
    WHERE EmployeeID = p_emp_id;
    
    -- Calculate new salary
    v_new_salary := v_current_salary * (1 + p_increase_pct / 100);
    
    -- Update salary
    UPDATE Employees
    SET Salary = v_new_salary
    WHERE EmployeeID = p_emp_id;
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Employee ID ' || p_emp_id || ': Salary updated from $' || v_current_salary || ' to $' || v_new_salary);
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage, ErrorStack)
        VALUES ('UpdateSalary', SQLERRM, DBMS_UTILITY.FORMAT_ERROR_BACKTRACE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END UpdateSalary;
/

-- Scenario 3: AddNewCustomer procedure
CREATE OR REPLACE PROCEDURE AddNewCustomer(
    p_cust_id NUMBER,
    p_name VARCHAR2,
    p_dob DATE,
    p_balance NUMBER
) AS
    v_cust_exists NUMBER;
BEGIN
    -- Check if customer already exists
    SELECT COUNT(*) INTO v_cust_exists FROM Customers WHERE CustomerID = p_cust_id;
    
    IF v_cust_exists > 0 THEN
        RAISE_APPLICATION_ERROR(-20020, 'Customer ID ' || p_cust_id || ' already exists. Duplicate not allowed.');
    END IF;
    
    -- Insert new customer
    INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
    VALUES (p_cust_id, p_name, p_dob, p_balance, SYSDATE);
    
    COMMIT;
    DBMS_OUTPUT.PUT_LINE('Customer "' || p_name || '" added successfully with ID ' || p_cust_id);
    
EXCEPTION
    WHEN OTHERS THEN
        ROLLBACK;
        INSERT INTO ErrorLog (ProcedureName, ErrorMessage, ErrorStack)
        VALUES ('AddNewCustomer', SQLERRM, DBMS_UTILITY.FORMAT_ERROR_BACKTRACE);
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('ERROR: ' || SQLERRM);
END AddNewCustomer;
/

-- Test the procedures
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Testing SafeTransferFunds ===');
    SafeTransferFunds(1, 2, 100);
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '=== Testing UpdateSalary ===');
    UpdateSalary(1, 10);
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '=== Testing AddNewCustomer ===');
    AddNewCustomer(3, 'New Customer', TO_DATE('1995-01-01', 'YYYY-MM-DD'), 5000);
END;
/
