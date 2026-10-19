# Write your MySQL query statement below

SELECT
    DATE_FORMAT(trans_date, '%Y-%m') AS month,
    country,
    COUNT(*) AS trans_count,
    SUM(state = 'approved') AS approved_count,
    SUM(amount) AS trans_total_amount,
    SUM(CASE
            WHEN state = 'approved' THEN amount
            ELSE 0
        END) AS approved_total_amount
FROM Transactions
GROUP BY DATE_FORMAT(trans_date, '%Y-%m'), country;


////////////////////////////////////////////////////////////////////////////////////////////////////////////////

SELECT
    DATE_FORMAT(trans_date, '%Y-%m') AS month,
    country,
    COUNT(*) AS trans_count,
    SUM(IF(state = 'approved', 1, 0)) AS approved_count,
    SUM(amount) AS trans_total_amount,
    SUM(IF(state = 'approved', amount, 0)) AS approved_total_amount
FROM Transactions
GROUP BY DATE_FORMAT(trans_date, '%Y-%m'), country;

/////////////////////////////////////////////////////
-- Quick comparison
-- Function	       Syntax	                                Result
-- DATE_FORMAT()	  DATE_FORMAT(trans_date, '%Y-%m')	        2019-01
-- LEFT()	          LEFT(trans_date, 7)                     	2019-01
-- SUBSTR()	      SUBSTR(trans_date, 1, 7)	                2019-01


////////////////////////////////////////////////////////////////////////
-- 1. Problem explanation — English mein
-- “The problem gives us a Transactions table containing the transaction ID, country, state, amount, and transaction date.

-- We need to calculate the total number of transactions, the number of approved transactions, the total transaction amount, and the total amount of approved transactions for each month and country.

-- To solve this, I will extract the year and month from the transaction date, group the records by month and country, and use aggregate functions with conditional logic to calculate the required counts and amounts.”
-- 2. Approach explanation
-- “I will use DATE_FORMAT() to convert the transaction date into the YYYY-MM format. Then, I will use GROUP BY on month and country.

-- I will use COUNT(*) to count all transactions, conditional aggregation to count approved transactions, SUM(amount) to calculate the total amount, and SUM(CASE WHEN ...) to calculate the approved amount only.”

-- ////////////////////////////////////////////
-- 7. Interview explanation
-- Interview mein aise explain kar sakte ho:

-- “I first extract the year and month from trans_date using DATE_FORMAT(). Then I group the records by month and country. I use COUNT(*) to count all transactions and conditional aggregation to count approved transactions and calculate their total amount. Finally, SUM(amount) gives the total amount of all transactions, while the conditional SUM() includes only approved amounts.”
-- 8. Interview follow-up questions with answers
-- Q1. Why do we use GROUP BY month, country?

-- Because the output requires separate results for each month-country combination. Grouping only by month would combine transactions from different countries.

-- Q2. What is conditional aggregation?

-- Conditional aggregation means applying a condition inside an aggregate function such as SUM() or COUNT().

-- Example:
-- SUM(CASE WHEN state = 'approved' THEN amount ELSE 0 END)


-- Q3. What is the difference between COUNT(*) and COUNT(id)?
-- - COUNT(*) counts all rows.
-- - COUNT(id) counts rows where id is not NULL.
-- Here both return the same count because id is a primary key.

-- Q4. Why not use WHERE state = 'approved'?

-- That would remove declined transactions before aggregation. We need declined transactions to be included in the total transaction count and total amount.

-- Q5. What is the difference between CASE WHEN and IF()?

-- Both can implement conditional logic in MySQL. IF() is concise and MySQL-specific; CASE WHEN is standard SQL syntax and is more portable across database systems.

-- Q6. What is the difference between DATE_FORMAT() and SUBSTR()?

-- DATE_FORMAT() formats a date according to specified date tokens. SUBSTR() extracts characters from a string. Both can produce YYYY-MM from a standard date representation.

-- Q7. Why do we use ELSE 0 inside SUM(CASE ...)?

-- It makes declined transactions contribute zero to the approved amount. Without ELSE 0, the CASE returns NULL for declined rows; SUM() ignores NULL, so the result is still correct for this problem.

-- Q8. Can we use YEAR(trans_date) and MONTH(trans_date) instead?

-- Yes, but grouping by both values requires selecting and combining them into the required YYYY-MM output format. DATE_FORMAT() does both formatting and month extraction in one expression.

-- Q9. Is GROUP BY month valid when month is a SELECT alias?

-- Yes, MySQL allows a select-list alias in GROUP BY. For portability across SQL systems, repeating the expression or using a CTE can be safer.



-- Q10. What happens if a group has no approved transactions?

-- SUM(CASE WHEN state = 'approved' THEN amount ELSE 0 END) returns 0 because every row contributes zero to the approved total.

