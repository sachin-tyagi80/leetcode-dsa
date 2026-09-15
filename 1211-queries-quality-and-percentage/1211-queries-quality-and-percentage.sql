# Write your MySQL query statement below
SELECT
    query_name,
    ROUND(AVG(rating / position), 2) AS quality,
    ROUND(
        AVG(CASE WHEN rating < 3 THEN 1 ELSE 0 END) * 100,
        2
    ) AS poor_query_percentage
FROM Queries
GROUP BY query_name;


-- 🎯 Interview Explanation

-- Interviewer bole:

-- "Explain your query."

-- Aap bol sakte ho:

-- "I group the data by query_name. For quality, I calculate the average of rating divided by position. For poor query percentage, I use CASE WHEN to convert ratings below 3 into 1 and the others into 0. Taking the average gives the fraction of poor queries, so I multiply it by 100. Finally, I round both values to two decimal places."

-- Hindi/Hinglish:

-- "Main data ko query_name ke basis par group karta hoon. Quality ke liye rating divided by position ka average nikalta hoon. Poor query percentage ke liye CASE WHEN use karke rating less than 3 ko 1 aur baaki ko 0 karta hoon. Inka average poor queries ka fraction deta hai, jise 100 se multiply karke percentage milta hai. Finally dono values ko 2 decimal places tak round karta hoon."

-- 🔥 Interview Follow-up Questions
-- Q1. Why GROUP BY query_name?

-- Answer:

-- Because we need quality and poor percentage separately for each query.

-- Q2. Why use AVG(rating / position)?

-- Because problem defines quality as:

-- Average of rating / position
-- Q3. Why use CASE WHEN?

-- Answer:

-- To convert poor queries into 1 and non-poor queries into 0, which makes calculating the percentage easy using AVG().

-- Q4. Why rating < 3?

-- Because the problem defines:

-- rating < 3 = poor query

-- So:

-- 1, 2 → Poor
-- 3, 4, 5 → Not poor
-- Q5. Why multiply by 100?

-- Because:

-- AVG(0/1)

-- gives a fraction.

-- Example:

-- 1/3 = 0.3333

-- Percentage:

-- 0.3333 × 100 = 33.33%
-- Q6. Why use ROUND(..., 2)?

-- Because the question specifically asks for 2 decimal places.

-- Q7. Can we use SUM(CASE...) / COUNT(*) instead?

-- Yes. Another valid approach:

-- SELECT
--     query_name,
--     ROUND(AVG(rating / position), 2) AS quality,
--     ROUND(
--         SUM(CASE WHEN rating < 3 THEN 1 ELSE 0 END)
--         * 100.0 / COUNT(*),
--         2
--     ) AS poor_query_percentage
-- FROM Queries
-- GROUP BY query_name;

-- This directly follows:

-- Poor queries / Total queries × 100
-- 🧠 Most Important Pattern

-- Is question se ye pattern yaad rakho:

-- Conditional Percentage
-- AVG(
--     CASE
--         WHEN condition THEN 1
--         ELSE 0
--     END
-- ) * 100

-- For example, employees with salary > 50000 percentage:

-- AVG(
--     CASE
--         WHEN salary > 50000 THEN 1
--         ELSE 0
--     END
-- ) * 100

-- Ye CASE WHEN + AVG() percentage pattern SQL interviews mein bahut useful hai.
