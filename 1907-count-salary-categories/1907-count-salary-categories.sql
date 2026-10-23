# Write your MySQL query statement below
-- ///Approach 1: UNION ALL + COUNT(*) (Recommended)
SELECT 'Low Salary' AS category,
       COUNT(*) AS accounts_count
FROM Accounts
WHERE income < 20000

UNION ALL

SELECT 'Average Salary' AS category,
       COUNT(*) AS accounts_count
FROM Accounts
WHERE income BETWEEN 20000 AND 50000

UNION ALL

SELECT 'High Salary' AS category,
       COUNT(*) AS accounts_count
FROM Accounts
WHERE income > 50000;


////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- Approach 2: Conditional aggregation with SUM(CASE WHEN ...)
       
-- SELECT 'Low Salary' AS category,
--        SUM(CASE WHEN income < 20000 THEN 1 ELSE 0 END)
--            AS accounts_count
-- FROM Accounts

-- UNION ALL

-- SELECT 'Average Salary',
--        SUM(CASE
--                WHEN income BETWEEN 20000 AND 50000 THEN 1
--                ELSE 0
--            END)
-- FROM Accounts

-- UNION ALL

-- SELECT 'High Salary',
--        SUM(CASE WHEN income > 50000 THEN 1 ELSE 0 END)
-- FROM Accounts;



//////////////////////////////////////////////////////////////////////////////////////////////////////
--        SELECT 
--     'High Salary' AS category,
--     COUNT(CASE WHEN income > 50000 THEN 1 END) AS accounts_count
-- FROM Accounts
-- UNION ALL
-- SELECT 
--     'Low Salary' AS category,
--     COUNT(CASE WHEN income < 20000 THEN 1 END) AS accounts_count
-- FROM Accounts
-- UNION ALL
-- SELECT 
--     'Average Salary' AS category,
--     COUNT(CASE WHEN income >= 20000 AND income <= 50000 THEN 1 END) AS accounts_count
-- FROM Accounts;


-- /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- -- . Interview Explanation (English)
-- “I use three separate aggregate queries to count accounts in the low, average, and high salary categories. Each query applies the appropriate WHERE condition and uses COUNT() to calculate the number of accounts. I combine the results using UNION ALL. Since each query uses COUNT() without GROUP BY, it returns zero when no accounts match, ensuring all three categories are included in the output.”

-- 5. Follow-up Questions
-- Why use UNION ALL instead of UNION?UNION ALL combines the results without removing duplicate rows, avoiding unnecessary duplicate elimination.
-- Does BETWEEN include both endpoints?Yes. BETWEEN 20000 AND 50000 includes both 20000 and 50000.
-- Why does COUNT(*) return zero for an empty category?An aggregate query with COUNT(*) and no GROUP BY returns one row containing zero when no rows match.
-- Can we solve this using CASE WHEN?Yes. We can generate the three category labels separately and use conditional counts, ensuring empty categories are also returned.
-- What if income is exactly 20000?It belongs to Average Salary, not Low Salary.
-- Pattern to remember: Fixed categories + zero-count requirement → COUNT(*) + UNION ALL.
