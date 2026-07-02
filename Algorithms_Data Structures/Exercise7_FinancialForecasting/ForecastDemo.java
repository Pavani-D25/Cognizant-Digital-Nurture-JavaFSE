public class ForecastDemo {
    public static void main(String[] args) {
        System.out.println("=== Exercise 7: Financial Forecasting ===\n");

        FinancialForecast forecaster = new FinancialForecast();

        double presentValue = 10000.0;
        double annualGrowthRate = 0.08; // 8% growth
        int years = 10;

        System.out.println("--- Basic Recursive Forecast ---");
        System.out.println("Present Value: $" + String.format("%.2f", presentValue));
        System.out.println("Growth Rate:   " + (annualGrowthRate * 100) + "%");
        System.out.println("Forecast Period: " + years + " years\n");

        // Year-by-year recursive forecast
        for (int i = 0; i <= years; i++) {
            double value = forecaster.recursiveForecast(presentValue, annualGrowthRate, i);
            System.out.println("Year " + i + ": $" + String.format("%.2f", value));
        }

        // --- Memoized vs Naive Performance ---
        System.out.println("\n--- Memoized vs Naive Recursion (40 periods) ---");
        int largePeriod = 40;

        long startNaive = System.nanoTime();
        double naiveResult = forecaster.recursiveForecast(presentValue, annualGrowthRate, largePeriod);
        long endNaive = System.nanoTime();

        long startMemo = System.nanoTime();
        double memoResult = forecaster.memoizedForecast(presentValue, annualGrowthRate, largePeriod);
        long endMemo = System.nanoTime();

        System.out.println("Naive Recursion Result: $" + String.format("%.2f", naiveResult) +
                " [" + (endNaive - startNaive) + " ns]");
        System.out.println("Memoized Result:        $" + String.format("%.2f", memoResult) +
                " [" + (endMemo - startMemo) + " ns]");

        // --- Iterative Comparison ---
        System.out.println("\n--- Iterative vs Recursive (same result) ---");
        long startIter = System.nanoTime();
        double iterResult = forecaster.iterativeForecast(presentValue, annualGrowthRate, years);
        long endIter = System.nanoTime();

        long startRec = System.nanoTime();
        double recResult = forecaster.recursiveForecast(presentValue, annualGrowthRate, years);
        long endRec = System.nanoTime();

        System.out.println("Iterative: $" + String.format("%.2f", iterResult) +
                " [" + (endIter - startIter) + " ns]");
        System.out.println("Recursive: $" + String.format("%.2f", recResult) +
                " [" + (endRec - startRec) + " ns]");

        // --- Variable Growth Rates ---
        System.out.println("\n--- Variable Growth Rates (Recursive) ---");
        double[] growthRates = {0.05, 0.07, 0.03, 0.10, 0.06};
        System.out.println("Starting Value: $" + String.format("%.2f", presentValue));
        System.out.println("Growth Rates: 5%, 7%, 3%, 10%, 6%");

        double[] yearValues = new double[growthRates.length + 1];
        yearValues[0] = presentValue;
        for (int i = 0; i < growthRates.length; i++) {
            yearValues[i + 1] = yearValues[i] * (1 + growthRates[i]);
        }
        for (int i = 0; i <= growthRates.length; i++) {
            System.out.println("Year " + i + ": $" + String.format("%.2f", yearValues[i]));
        }

        // --- Compound Interest ---
        System.out.println("\n--- Compound Interest (Recursive) ---");
        double principal = 5000.0;
        double rate = 0.06;
        int compoundYears = 5;
        System.out.println("Principal: $" + String.format("%.2f", principal));
        System.out.println("Rate: " + (rate * 100) + "% per year");

        for (int i = 0; i <= compoundYears; i++) {
            double amount = forecaster.compoundInterestRecursive(principal, rate, i);
            System.out.println("Year " + i + ": $" + String.format("%.2f", amount));
        }

        // --- Complexity Analysis ---
        System.out.println("\n=== Time Complexity Analysis ===");
        System.out.println("Approach             | Time     | Space    | Notes");
        System.out.println("---------------------|----------|----------|-----------------------------");
        System.out.println("Naive Recursion      | O(n)     | O(n)     | Redundant calls possible");
        System.out.println("Memoized Recursion   | O(n)     | O(n)     | Caches results");
        System.out.println("Iterative            | O(n)     | O(1)     | Most efficient");
        System.out.println("Variable Growth Rec. | O(n)     | O(n)     | Handles varying rates");
        System.out.println();
        System.out.println("=== Optimization Strategies ===");
        System.out.println("1. Memoization: Store computed results to avoid redundant recursion");
        System.out.println("2. Iteration: Replace recursion with loops for O(1) space");
        System.out.println("3. Tail Recursion: Restructure so recursive call is last operation");
        System.out.println("   (Java does not optimize tail recursion, but languages like Scala do)");
        System.out.println("4. Divide and Conquer: Split problem into independent subproblems");
    }
}
