class Solution {

    public boolean checkDivisibility(int n) {

        // Store original number
        int original = n;

        // Sum of digits
        int sum = 0;

        // Product of digits
        int product = 1;

        // Extract every digit
        while (n > 0) {

            // Get last digit
            int digit = n % 10;

            // Add digit to sum
            sum += digit;

            // Multiply digit into product
            product *= digit;

            // Remove last digit
            n /= 10;
        }

        // Divisor = digit sum + digit product
        int divisor = sum + product;

        // Check divisibility
        return original % divisor == 0;
    }
}