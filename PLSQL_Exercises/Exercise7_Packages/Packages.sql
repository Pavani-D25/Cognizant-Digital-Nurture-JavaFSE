-- ============================================
-- Exercise 7: Packages
-- ============================================

-- Scenario 1: CustomerManagement package
CREATE OR REPLACE PACKAGE CustomerManagement AS
    -- Procedure to add a new customer
    PROCEDURE AddCustomer(
        p_cust_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    );
    
    -- Procedure to update customer details
    PROCEDURE UpdateCustomer(
        p_cust_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    );
    
    -- Function to get customer balance
    FUNCTION GetCustomerBalance(
        p_cust_id NUMBER
    ) RETURN NUMBER;
END CustomerManagement;
/

CREATE OR REPLACE PACKAGE BODY CustomerManagement AS

    PROCEDURE AddCustomer(
        p_cust_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    ) AS
        v_exists NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_exists FROM Customers WHERE CustomerID = p_cust_id;
        
        IF v_exists > 0 THEN
            RAISE_APPLICATION_ERROR(-20100, 'Customer ' || p_cust_id || ' already exists');
        END IF;
        
        INSERT INTO Customers (CustomerID, Name, DOB, Balance, LastModified)
        VALUES (p_cust_id, p_name, p_dob, p_balance, SYSDATE);
        
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer "' || p_name || '" added successfully.');
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END AddCustomer;

    PROCEDURE UpdateCustomer(
        p_cust_id NUMBER,
        p_name VARCHAR2,
        p_dob DATE,
        p_balance NUMBER
    ) AS
        v_exists NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_exists FROM Customers WHERE CustomerID = p_cust_id;
        
        IF v_exists = 0 THEN
            RAISE_APPLICATION_ERROR(-20101, 'Customer ' || p_cust_id || ' not found');
        END IF;
        
        UPDATE Customers
        SET Name = NVL(p_name, Name),
            DOB = NVL(p_dob, DOB),
            Balance = NVL(p_balance, Balance),
            LastModified = SYSDATE
        WHERE CustomerID = p_cust_id;
        
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Customer ' || p_cust_id || ' updated successfully.');
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END UpdateCustomer;

    FUNCTION GetCustomerBalance(
        p_cust_id NUMBER
    ) RETURN NUMBER AS
        v_balance NUMBER;
    BEGIN
        SELECT Balance INTO v_balance FROM Customers WHERE CustomerID = p_cust_id;
        RETURN v_balance;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetCustomerBalance;

END CustomerManagement;
/

-- Scenario 2: EmployeeManagement package
CREATE OR REPLACE PACKAGE EmployeeManagement AS
    -- Procedure to hire a new employee
    PROCEDURE HireEmployee(
        p_emp_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2,
        p_hire_date DATE
    );
    
    -- Procedure to update employee details
    PROCEDURE UpdateEmployee(
        p_emp_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2
    );
    
    -- Function to calculate annual salary
    FUNCTION GetAnnualSalary(
        p_emp_id NUMBER
    ) RETURN NUMBER;
END EmployeeManagement;
/

CREATE OR REPLACE PACKAGE BODY EmployeeManagement AS

    PROCEDURE HireEmployee(
        p_emp_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2,
        p_hire_date DATE
    ) AS
        v_exists NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_exists FROM Employees WHERE EmployeeID = p_emp_id;
        
        IF v_exists > 0 THEN
            RAISE_APPLICATION_ERROR(-20110, 'Employee ' || p_emp_id || ' already exists');
        END IF;
        
        INSERT INTO Employees (EmployeeID, Name, Position, Salary, Department, HireDate)
        VALUES (p_emp_id, p_name, p_position, p_salary, p_department, p_hire_date);
        
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee "' || p_name || '" hired successfully.');
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END HireEmployee;

    PROCEDURE UpdateEmployee(
        p_emp_id NUMBER,
        p_name VARCHAR2,
        p_position VARCHAR2,
        p_salary NUMBER,
        p_department VARCHAR2
    ) AS
        v_exists NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_exists FROM Employees WHERE EmployeeID = p_emp_id;
        
        IF v_exists = 0 THEN
            RAISE_APPLICATION_ERROR(-20111, 'Employee ' || p_emp_id || ' not found');
        END IF;
        
        UPDATE Employees
        SET Name = NVL(p_name, Name),
            Position = NVL(p_position, Position),
            Salary = NVL(p_salary, Salary),
            Department = NVL(p_department, Department)
        WHERE EmployeeID = p_emp_id;
        
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Employee ' || p_emp_id || ' updated successfully.');
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END UpdateEmployee;

    FUNCTION GetAnnualSalary(
        p_emp_id NUMBER
    ) RETURN NUMBER AS
        v_salary NUMBER;
    BEGIN
        SELECT Salary INTO v_salary FROM Employees WHERE EmployeeID = p_emp_id;
        RETURN v_salary * 12;
    EXCEPTION
        WHEN NO_DATA_FOUND THEN
            RETURN NULL;
    END GetAnnualSalary;

END EmployeeManagement;
/

-- Scenario 3: AccountOperations package
CREATE OR REPLACE PACKAGE AccountOperations AS
    -- Procedure to open a new account
    PROCEDURE OpenAccount(
        p_account_id NUMBER,
        p_cust_id NUMBER,
        p_type VARCHAR2,
        p_initial_balance NUMBER
    );
    
    -- Procedure to close an account
    PROCEDURE CloseAccount(
        p_account_id NUMBER
    );
    
    -- Function to get total balance across all accounts
    FUNCTION GetTotalBalance(
        p_cust_id NUMBER
    ) RETURN NUMBER;
END AccountOperations;
/

CREATE OR REPLACE PACKAGE BODY AccountOperations AS

    PROCEDURE OpenAccount(
        p_account_id NUMBER,
        p_cust_id NUMBER,
        p_type VARCHAR2,
        p_initial_balance NUMBER
    ) AS
        v_cust_exists NUMBER;
        v_acct_exists NUMBER;
    BEGIN
        -- Check customer exists
        SELECT COUNT(*) INTO v_cust_exists FROM Customers WHERE CustomerID = p_cust_id;
        IF v_cust_exists = 0 THEN
            RAISE_APPLICATION_ERROR(-20120, 'Customer ' || p_cust_id || ' does not exist');
        END IF;
        
        -- Check account doesn't exist
        SELECT COUNT(*) INTO v_acct_exists FROM Accounts WHERE AccountID = p_account_id;
        IF v_acct_exists > 0 THEN
            RAISE_APPLICATION_ERROR(-20121, 'Account ' || p_account_id || ' already exists');
        END IF;
        
        -- Validate initial balance
        IF p_initial_balance < 0 THEN
            RAISE_APPLICATION_ERROR(-20122, 'Initial balance cannot be negative');
        END IF;
        
        INSERT INTO Accounts (AccountID, CustomerID, AccountType, Balance, LastModified)
        VALUES (p_account_id, p_cust_id, p_type, p_initial_balance, SYSDATE);
        
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account ' || p_account_id || ' (' || p_type || ') opened with $' || p_initial_balance);
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END OpenAccount;

    PROCEDURE CloseAccount(
        p_account_id NUMBER
    ) AS
        v_balance NUMBER;
        v_acct_exists NUMBER;
    BEGIN
        SELECT COUNT(*) INTO v_acct_exists FROM Accounts WHERE AccountID = p_account_id;
        IF v_acct_exists = 0 THEN
            RAISE_APPLICATION_ERROR(-20123, 'Account ' || p_account_id || ' does not exist');
        END IF;
        
        SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
        
        IF v_balance > 0 THEN
            DBMS_OUTPUT.PUT_LINE('Warning: Account has $' || v_balance || ' remaining. Please withdraw before closing.');
        END IF;
        
        DELETE FROM Accounts WHERE AccountID = p_account_id;
        
        COMMIT;
        DBMS_OUTPUT.PUT_LINE('Account ' || p_account_id || ' has been closed.');
    EXCEPTION
        WHEN OTHERS THEN
            ROLLBACK;
            RAISE;
    END CloseAccount;

    FUNCTION GetTotalBalance(
        p_cust_id NUMBER
    ) RETURN NUMBER AS
        v_total NUMBER;
    BEGIN
        SELECT NVL(SUM(Balance), 0) INTO v_total
        FROM Accounts
        WHERE CustomerID = p_cust_id;
        
        RETURN v_total;
    END GetTotalBalance;

END AccountOperations;
/

-- Test the packages
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Testing CustomerManagement Package ===');
    CustomerManagement.AddCustomer(10, 'Test Customer', TO_DATE('1995-03-15', 'YYYY-MM-DD'), 5000);
    DBMS_OUTPUT.PUT_LINE('Balance: $' || CustomerManagement.GetCustomerBalance(10));
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '=== Testing EmployeeManagement Package ===');
    EmployeeManagement.HireEmployee(10, 'Test Employee', 'Analyst', 65000, 'Finance', SYSDATE);
    DBMS_OUTPUT.PUT_LINE('Annual Salary: $' || EmployeeManagement.GetAnnualSalary(10));
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '=== Testing AccountOperations Package ===');
    AccountOperations.OpenAccount(10, 1, 'Savings', 2500);
    DBMS_OUTPUT.PUT_LINE('Total Balance for Customer 1: $' || AccountOperations.GetTotalBalance(1));
END;
/
