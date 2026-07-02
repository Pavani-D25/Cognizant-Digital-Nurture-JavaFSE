-- ============================================
-- Exercise 1: Control Structures
-- ============================================

-- Scenario 1: Apply discount to loan interest rates for customers above 60
DECLARE
    v_age NUMBER;
    v_discount_rate NUMBER := 1.0;
BEGIN
    FOR loan_rec IN (SELECT l.LoanID, l.InterestRate, c.DOB
                     FROM Loans l
                     JOIN Customers c ON l.CustomerID = c.CustomerID) LOOP
        -- Calculate age from DOB
        v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, loan_rec.DOB) / 12);
        
        IF v_age > 60 THEN
            UPDATE Loans
            SET InterestRate = InterestRate - v_discount_rate
            WHERE LoanID = loan_rec.LoanID;
            
            DBMS_OUTPUT.PUT_LINE('Loan ID ' || loan_rec.LoanID || 
                                 ': Discount applied. Age=' || v_age || 
                                 ', New Rate=' || (loan_rec.InterestRate - v_discount_rate) || '%');
        ELSE
            DBMS_OUTPUT.PUT_LINE('Loan ID ' || loan_rec.LoanID || 
                                 ': No discount. Age=' || v_age);
        END IF;
    END LOOP;
    COMMIT;
END;
/

-- Scenario 2: Set IsVIP flag for customers with balance over 10000
DECLARE
    v_balance NUMBER;
    v_is_vip VARCHAR2(5);
BEGIN
    FOR cust_rec IN (SELECT CustomerID, Name, Balance FROM Customers) LOOP
        v_balance := cust_rec.Balance;
        
        IF v_balance > 10000 THEN
            v_is_vip := 'TRUE';
        ELSE
            v_is_vip := 'FALSE';
        END IF;
        
        DBMS_OUTPUT.PUT_LINE('Customer: ' || cust_rec.Name || 
                             ' | Balance: $' || v_balance || 
                             ' | IsVIP: ' || v_is_vip);
    END LOOP;
END;
/

-- Scenario 3: Send reminders for loans due within 30 days
DECLARE
    v_due_date DATE;
    v_days_remaining NUMBER;
BEGIN
    FOR loan_rec IN (SELECT l.LoanID, l.CustomerID, l.EndDate, c.Name
                     FROM Loans l
                     JOIN Customers c ON l.CustomerID = c.CustomerID) LOOP
        
        v_days_remaining := loan_rec.EndDate - SYSDATE;
        
        IF v_days_remaining <= 30 AND v_days_remaining >= 0 THEN
            DBMS_OUTPUT.PUT_LINE('*** REMINDER ***');
            DBMS_OUTPUT.PUT_LINE('Customer: ' || loan_rec.Name);
            DBMS_OUTPUT.PUT_LINE('Loan ID: ' || loan_rec.LoanID);
            DBMS_OUTPUT.PUT_LINE('Due Date: ' || TO_CHAR(loan_rec.EndDate, 'DD-MON-YYYY'));
            DBMS_OUTPUT.PUT_LINE('Days Remaining: ' || TRUNC(v_days_remaining));
            DBMS_OUTPUT.PUT_LINE('-----------------------------------');
        END IF;
    END LOOP;
END;
/
