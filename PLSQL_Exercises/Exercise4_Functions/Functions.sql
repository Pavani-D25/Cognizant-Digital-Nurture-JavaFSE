-- ============================================
-- Exercise 4: Functions
-- ============================================

-- Scenario 1: CalculateAge function
CREATE OR REPLACE FUNCTION CalculateAge(
    p_dob DATE
) RETURN NUMBER IS
    v_age NUMBER;
BEGIN
    -- Calculate age in complete years
    v_age := TRUNC(MONTHS_BETWEEN(SYSDATE, p_dob) / 12);
    RETURN v_age;
EXCEPTION
    WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Error calculating age: ' || SQLERRM);
        RETURN NULL;
END CalculateAge;
/

-- Scenario 2: CalculateMonthlyInstallment function
CREATE OR REPLACE FUNCTION CalculateMonthlyInstallment(
    p_loan_amount NUMBER,
    p_annual_rate NUMBER,
    p_years NUMBER
) RETURN NUMBER IS
    v_monthly_rate NUMBER;
    v_total_months NUMBER;
    v_installment NUMBER;
BEGIN
    -- Convert annual rate to monthly
    v_monthly_rate := p_annual_rate / 100 / 12;
    v_total_months := p_years * 12;
    
    -- Handle zero interest rate case
    IF v_monthly_rate = 0 THEN
        v_installment := p_loan_amount / v_total_months;
    ELSE
        -- Standard amortization formula: M = P * [r(1+r)^n] / [(1+r)^n - 1]
        v_installment := p_loan_amount * (v_monthly_rate * POWER(1 + v_monthly_rate, v_total_months)) /
                        (POWER(1 + v_monthly_rate, v_total_months) - 1);
    END IF;
    
    RETURN ROUND(v_installment, 2);
END CalculateMonthlyInstallment;
/

-- Scenario 3: HasSufficientBalance function
CREATE OR REPLACE FUNCTION HasSufficientBalance(
    p_account_id NUMBER,
    p_amount NUMBER
) RETURN BOOLEAN IS
    v_balance NUMBER;
    v_account_exists NUMBER;
BEGIN
    -- Check if account exists
    SELECT COUNT(*) INTO v_account_exists FROM Accounts WHERE AccountID = p_account_id;
    
    IF v_account_exists = 0 THEN
        RETURN FALSE;
    END IF;
    
    -- Get current balance
    SELECT Balance INTO v_balance FROM Accounts WHERE AccountID = p_account_id;
    
    -- Check if sufficient
    IF v_balance >= p_amount THEN
        RETURN TRUE;
    ELSE
        RETURN FALSE;
    END IF;
EXCEPTION
    WHEN OTHERS THEN
        RETURN FALSE;
END HasSufficientBalance;
/

-- Test the functions
DECLARE
    v_age NUMBER;
    v_installment NUMBER;
    v_has_funds BOOLEAN;
BEGIN
    DBMS_OUTPUT.PUT_LINE('=== Testing CalculateAge ===');
    v_age := CalculateAge(TO_DATE('1985-05-15', 'YYYY-MM-DD'));
    DBMS_OUTPUT.PUT_LINE('Age: ' || v_age || ' years');
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '=== Testing CalculateMonthlyInstallment ===');
    v_installment := CalculateMonthlyInstallment(50000, 5, 5);
    DBMS_OUTPUT.PUT_LINE('Loan: $50,000 | Rate: 5% | Duration: 5 years');
    DBMS_OUTPUT.PUT_LINE('Monthly Installment: $' || v_installment);
    
    DBMS_OUTPUT.PUT_LINE(CHR(10) || '=== Testing HasSufficientBalance ===');
    v_has_funds := HasSufficientBalance(1, 500);
    IF v_has_funds THEN
        DBMS_OUTPUT.PUT_LINE('Account 1 has sufficient balance for $500');
    ELSE
        DBMS_OUTPUT.PUT_LINE('Account 1 does NOT have sufficient balance for $500');
    END IF;
END;
/
