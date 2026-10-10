# Write your MySQL query statement below
SELECT
    x,
    y,
    z,
    CASE
        WHEN x + y > z
         AND x + z > y
         AND y + z > x
        THEN 'Yes'
        ELSE 'No'
    END AS triangle
FROM Triangle;

/////////////////////////////////////////////////////////////////////
-- 7. Interview Explanation
-- “I used a CASE expression to check the triangle inequality rule. For three sides to form a triangle, the sum of every pair of sides must be greater than the remaining side. I checked all three conditions using the AND operator. If all conditions are satisfied, I return Yes; otherwise, I return No.”
-- 8. Interview Follow-up Questions with Answers
-- Q1. What is the triangle inequality rule?
-- Answer: The sum of any two sides of a triangle must be strictly greater than the third side.
-- Q2. Why do we use AND instead of OR?
-- Answer: All three inequalities must be true. Using OR would incorrectly return Yes even when only one condition is satisfied.
-- Q3. What is the purpose of CASE WHEN in SQL?
-- Answer: CASE WHEN performs conditional logic and returns different values depending on whether conditions are satisfied.
-- Q4. What happens if x + y = z?
-- Answer: The result is No because the sum must be strictly greater than the third side, not equal to it.
-- Q5. Can we solve this problem using IF() in MySQL?
-- Answer: Yes. MySQL supports IF() for conditional expressions.
-- SELECT
--     x, y, z,
--     IF(
--         x + y > z AND x + z > y AND y + z > x,
--         'Yes',
--         'No'
--     ) AS triangle
-- FROM Triangle;


-- Q6. What is the difference between CASE WHEN and IF()?
-- Answer: Both can handle conditional logic in MySQL. IF() is convenient for a simple condition, while CASE is more flexible for multiple conditions and branches.
-- Q7. What is the time complexity of this solution?
-- Answer: \(O(n)\), where \(n\) is the number of rows, because each row requires a fixed number of comparisons.
-- 9. Pattern to Remember
-- CASE WHEN + multiple conditions + AND
-- Use this pattern when a SQL problem asks you to classify rows based on several conditions and return labels such as Yes/No, Valid/Invalid, or True/False.
