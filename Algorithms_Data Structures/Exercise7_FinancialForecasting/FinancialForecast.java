/**
 * FinancialForecast provides recursive and optimized methods
 * for predicting future values based on historical growth rates.
 * 
 * Recursion Concept:
 * - Recursion breaks a problem into smaller subproblems of the same type
 * - Base case: stops recursion when problem is simple enough
 * - Recursive case: calls itself with a smaller/different input
 * - Each recursive call adds a frame to the call stack
 * 
 * Time Complexity Analysis:
 * - Naive Recursion: O(n) time, O(n) space (call stack)
 * - Memoized Recursion: O(n) time, O(n) space (cache + stack)
 * - Iterative: O(n) time, O(1) space
 * 
 * Optimization Strategies:
 * 1. Memoization: Cache results to avoid redundant computation
 * 2. Iteration: Replace recursion with loops to eliminate stack overhead
 * 3. Tail recursion: Compiler optimization (not supported in Java)
 */
public class FinancialForecast {

    private double[] cache;

    /**
     * Naive recursive calculation of future value.
     * 
     * Formula: futureValue(present, rate, periods) = 
     *          futureValue(present, rate, periods - 1) * (1 + rate)
     * Base case: periods == 0, return present value
     * 
     * Time Complexity:  O(n) - one recursive call per period
     * Space Complexity: O(n) - call stack depth equals periods
     * 
     * @param presentValue the current value
     * @param growthRate   the growth rate per period (e.g., 0.05 for 5%)
     * @param periods      the number of periods to forecast
     * @return the predicted future value
     */
    public double recursiveForecast(double presentValue, double growthRate, int periods) {
        // Base case
        if (periods == 0) {
            return presentValue;
        }
        // Recursive case
        return recursiveForecast(presentValue, growthRate, periods - 1) * (1 + growthRate);
    }

    /**
     * Memoized recursive calculation - avoids redundant computation.
     * 
     * Time Complexity:  O(n) - each period computed exactly once
     * Space Complexity: O(n) - cache array + call stack
     * 
     * @param presentValue the current value
     * @param growthRate   the growth rate per period
     * @param periods      the number of periods to forecast
     * @return the predicted future value
     */
    public double memoizedForecast(double presentValue, double growthRate, int periods) {
        cache = new double[periods + 1];
        for (int i = 0; i <= periods; i++) {
            cache[i] = -1; // Initialize with -1 (unset)
        }
        return memoizedHelper(presentValue, growthRate, periods);
    }

    private double memoizedHelper(double presentValue, double growthRate, int periods) {
        // Base case
        if (periods == 0) {
            return presentValue;
        }
        // Check cache
        if (cache[periods] != -1) {
            return cache[periods];
        }
        // Compute and cache
        cache[periods] = memoizedHelper(presentValue, growthRate, periods - 1) * (1 + growthRate);
        return cache[periods];
    }

    /**
     * Iterative calculation - most efficient approach.
     * 
     * Time Complexity:  O(n) - loop runs n times
     * Space Complexity: O(1) - no extra data structures
     * 
     * @param presentValue the current value
     * @param growthRate   the growth rate per period
     * @param periods      the number of periods to forecast
     * @return the predicted future value
     */
    public double iterativeForecast(double presentValue, double growthRate, int periods) {
        double value = presentValue;
        for (int i = 0; i < periods; i++) {
            value *= (1 + growthRate);
        }
        return value;
    }

    /**
     * Recursive calculation using variable growth rates.
     * Useful when growth rates differ each period.
     * 
     * @param presentValue the current value
     * @param growthRates  array of growth rates per period
     * @param periodIndex  current period index
     * @return the predicted future value
     */
    public double recursiveVariableGrowth(double presentValue, double[] growthRates, int periodIndex) {
        // Base case: no more periods
        if (periodIndex >= growthRates.length) {
            return presentValue;
        }
        // Recursive case: apply current rate and continue
        double nextValue = presentValue * (1 + growthRates[periodIndex]);
        return recursiveVariableGrowth(nextValue, growthRates, periodIndex + 1);
    }

    /**
     * Recursively calculates compound growth over multiple years.
     * Shows how recursion simplifies compound interest problems.
     * 
     * @param principal  initial amount
     * @param rate       annual interest rate
     * @param years      number of years
     * @return the accumulated amount
     */
    public double compoundInterestRecursive(double principal, double rate, int years) {
        if (years == 0) {
            return principal;
        }
        double previousYear = compoundInterestRecursive(principal, rate, years - 1);
        return previousYear + (previousYear * rate);
    }
}
