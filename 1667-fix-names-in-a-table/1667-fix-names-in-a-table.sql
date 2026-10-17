# Write your MySQL query statement below
SELECT 
    user_id,
    CONCAT(
        UPPER(SUBSTRING(name,1,1)),
        LOWER(SUBSTRING(name,2))
    ) AS name
FROM Users
ORDER BY user_id ASC;


///////////////////////////////////////////////////////////////////////////////////////
-- 7. Interview Explanation
-- You can explain this solution in an interview:
-- I use SUBSTRING to separate the first character from the remaining characters. I convert the first character to uppercase using UPPER and the remaining characters to lowercase using LOWER. Then I combine both parts using CONCAT and sort the result by user_id in ascending order.

-- 8. Interview Follow-up Questions with Answers
-- Q1. What does UPPER() do?
-- It converts a string to uppercase.
-- SELECT UPPER('sachin');
-- -- SACHIN


-- Q2. What does LOWER() do?
-- It converts a string to lowercase.
-- SELECT LOWER('SACHIN');
-- -- sachin


-- Q3. What does SUBSTRING() do?
-- It extracts part of a string.
-- SELECT SUBSTRING('Sachin', 1, 1);
-- -- S

-- SELECT SUBSTRING('Sachin', 2);
-- -- achin


-- Q4. What does CONCAT() do?
-- It joins two or more strings into one string.
-- SELECT CONCAT('Sa', 'chin');
-- -- Sachin


-- Q5. Why not use UPPER(name)?
-- That would make every character uppercase, producing ALICE instead of Alice.
-- Q6. Does this query permanently modify the table?
-- No. SELECT only returns the corrected names in the result. It does not update the stored names.
-- Q7. What is the purpose of ORDER BY user_id ASC?
-- It ensures the output is ordered by user ID in ascending order, as required by the problem.
-- 9. Pattern to Remember
-- For name-formatting problems, remember:
-- - SUBSTRING(name, 1, 1) → first character.
-- - UPPER() → uppercase.
-- - SUBSTRING(name, 2) → remaining characters.
-- - LOWER() → lowercase.
-- - CONCAT() → combine strings.
-- - ORDER BY → sort results.
-- One-line pattern:
-- CONCAT(UPPER(first_character), LOWER(remaining_characters))
-- This pattern is useful for capitalization and text-formatting questions in SQL interviews.
