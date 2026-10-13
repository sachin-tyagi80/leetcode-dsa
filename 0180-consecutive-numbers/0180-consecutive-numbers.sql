# Write your MySQL query statement below
SELECT DISTINCT
    l1.num AS ConsecutiveNums
FROM Logs l1
JOIN Logs l2
    ON l1.id = l2.id - 1
JOIN Logs l3
    ON l2.id = l3.id - 1
WHERE l1.num = l2.num
  AND l2.num = l3.num;


//////////////////////////////////////////////////////////////////////
-- 7. Interview Explanation
-- “I used a self join to compare three consecutive rows in the Logs table. I joined the table three times using separate aliases and matched consecutive IDs. Then I checked whether the numbers in all three rows were equal. Finally, I used DISTINCT to return each qualifying number only once.”
-- 8. Interview Follow-up Questions with Answers
-- Q1. What is a self join?
-- Answer: A self join joins a table with itself to compare or relate records within the same table.
-- Q2. Why do we use three aliases?
-- Answer: We need to compare three consecutive rows, so l1, l2, and l3 represent the first, second, and third rows.
-- Q3. Why do we use DISTINCT?
-- Answer: A number could appear in multiple qualifying sequences. DISTINCT ensures each number appears only once.
-- Q4. Why do we compare the IDs?
-- Answer: Comparing IDs ensures the rows are consecutive, rather than simply finding the same number in unrelated rows.
-- Q5. What happens if a number appears four or five times consecutively?
-- Answer: It qualifies because the requirement is at least three consecutive occurrences. DISTINCT prevents duplicate output.
-- Q6. Can we solve this problem using window functions?
-- Answer: Yes. We can use LAG() to access the previous rows and compare their numbers.
-- Q7. What is the difference between WHERE and HAVING?
-- Answer: WHERE filters individual rows before grouping, whereas HAVING filters groups after aggregation. This solution uses WHERE because it checks row values without grouping.
-- Q8. What is the time complexity?
-- Answer: The exact performance depends on the database execution plan and indexes. Conceptually, the query compares related rows, and the primary key on id can help the joins.
-- 9. Alternative Solution Using LAG()
-- WITH CTE AS (
--     SELECT
--         id,
--         num,
--         LAG(num, 1) OVER (ORDER BY id) AS prev_num,
--         LAG(num, 2) OVER (ORDER BY id) AS prev_prev_num
--     FROM Logs
-- )
-- SELECT DISTINCT
--     num AS ConsecutiveNums
-- FROM CTE
-- WHERE num = prev_num
--   AND num = prev_prev_num;


-- - LAG(num, 1) retrieves the previous row's number.
-- - LAG(num, 2) retrieves the number two rows earlier.
-- - If both match the current number, there are three consecutive occurrences.
-- This version requires MySQL 8.0 or later.
-- 10. Pattern to Remember
-- Self Join → Consecutive IDs → Compare Values → DISTINCT
-- Use this pattern when SQL problems ask you to identify consecutive records or repeated values across adjacent rows.
