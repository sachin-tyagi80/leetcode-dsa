# Write your MySQL query statement below

SELECT product_id
FROM Products
WHERE low_fats = 'Y'
  AND recyclable = 'Y';


////////////////////////////////////////////////////////////////
-- 7. Interview Explanation
-- You can explain the solution like this:
-- “I used the WHERE clause to filter products where both low_fats and recyclable are equal to 'Y'. I used the AND operator because both conditions must be satisfied. Finally, I selected only the product_id column.”
-- 8. Interview Follow-up Questions with Answers
-- Q1. Why do we use AND instead of OR?
-- AND requires both conditions to be true. OR would include products satisfying either condition, which does not meet the problem requirements.
-- Q2. What is the difference between WHERE and HAVING?
-- - WHERE filters individual rows before grouping.
-- - HAVING filters groups after GROUP BY.
-- Here, we are filtering individual rows, so WHERE is appropriate.
-- Q3. Why are 'Y' and 'N' written inside quotes?
-- They are character values from the ENUM column, so we compare them as strings.
-- Q4. Do we need ORDER BY?
-- No. The question explicitly allows the result in any order.
-- Q5. Do we need DISTINCT?
-- No. product_id is the primary key, so each product ID is unique.
-- 9. Pattern to Remember
-- When a problem asks for rows satisfying multiple conditions, use WHERE with AND.
-- General pattern:
-- SELECT column_name
-- FROM table_name
-- WHERE condition1
--   AND condition2;


-- Remember:
-- - AND → all conditions must be true.
-- - OR → at least one condition must be true.
-- Key takeaway: This problem tests basic SELECT, FROM, WHERE, and AND — essential SQL concepts for interviews.
