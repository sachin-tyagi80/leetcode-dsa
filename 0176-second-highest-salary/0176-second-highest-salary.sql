# Write your MySQL query statement below
SELECT MAX(salary) AS SecondHighestSalary
FROM Employee
WHERE salary < (
    SELECT MAX(salary)
    FROM Employee
);


-- 🎯 Interview Explanation

-- Agar interviewer bole "Explain your approach."

-- Aap bolo:

-- "First, I find the highest salary using MAX(). Then I filter out that highest salary using a subquery. Finally, I find the maximum salary from the remaining salaries, which gives the second highest distinct salary."

-- Short version:

-- "I find the maximum salary, exclude it, and then find the maximum among the remaining salaries."

-- 🔥 Follow-up Questions
-- Q1. Why do we use MAX() twice?

-- First:

-- MAX(salary)

-- → highest salary.

-- Second:

-- MAX(salary)

-- → highest salary after removing the highest one, which is second highest.

-- Q2. Why not use ORDER BY salary DESC LIMIT 2?

-- Aise:

-- SELECT salary
-- FROM Employee
-- ORDER BY salary DESC
-- LIMIT 2;

-- Isse 2 rows milengi, but hume second distinct salary chahiye.

-- Duplicates ki problem aa sakti hai.

-- Q3. What is a subquery?

-- Ye part:

-- (
--     SELECT MAX(salary)
--     FROM Employee
-- )

-- ek subquery hai.

-- Query ke andar ek aur query.

-- Outer query uska result use kar rahi hai.

-- Q4. What if salaries are 300, 300, 200?

-- Inner:

-- MAX(salary) = 300

-- Filter:

-- salary < 300

-- Remaining:

-- 200

-- Final:

-- 200

-- So duplicates automatically handle ho jaate hain.

-- Q5. Can we use DISTINCT?

-- Haan, alternative:

-- SELECT MAX(salary) AS SecondHighestSalary
-- FROM (
--     SELECT DISTINCT salary
--     FROM Employee
-- ) AS e
-- WHERE salary < (
--     SELECT MAX(salary)
--     FROM Employee
-- );

-- But ye unnecessarily complex hai. First solution better hai.




-- SELECT (
--     SELECT DISTINCT salary
--     FROM Employee
--     ORDER BY salary DESC
--     LIMIT 1 OFFSET 1
-- ) AS SecondHighestSalary;

-- 🎯 Interview Explanation

-- Interviewer bole:

-- "Explain your query."

-- Aap bolo:

-- "First, I select distinct salaries to remove duplicate salary values. Then I sort the salaries in descending order, so the highest salary comes first. Using OFFSET 1, I skip the highest salary, and LIMIT 1 returns the next salary, which is the second highest distinct salary."

-- Ye answer interview mein enough hai. ✅

-- 🔥 Follow-up Questions
-- Q1. Why DISTINCT?

-- Suppose:

-- 300
-- 300
-- 200
-- 100

-- Without DISTINCT:

-- 300
-- 300
-- 200
-- 100

-- OFFSET 1 karega:

-- 300 ← skip
-- 300 ← return ❌

-- Answer 300 aa jayega, jo wrong hai.

-- With DISTINCT:

-- 300
-- 200
-- 100

-- Then:

-- 300 ← skip
-- 200 ← return ✅
-- Q2. What does OFFSET 1 mean?

-- It skips the first row of the result.

-- For descending salary:

-- OFFSET 0 → first/highest
-- OFFSET 1 → second
-- OFFSET 2 → third
-- Q3. What does LIMIT 1 mean?

-- Return at most one row.

-- LIMIT 1

-- means maximum 1 row.

-- Q4. What if there is no second highest salary?

-- Example:

-- 100
-- 100
-- 100

-- After DISTINCT:

-- 100

-- After OFFSET 1:

-- nothing

-- So the subquery returns no row, and the outer query produces:

-- SecondHighestSalary
-- NULL

-- That's exactly what LeetCode asks.

-- Q5. What is a subquery?

-- This part:

-- (
--     SELECT DISTINCT salary
--     FROM Employee
--     ORDER BY salary DESC
--     LIMIT 1 OFFSET 1
-- )

-- is called a subquery.

-- Simple definition:

-- A query written inside another query is called a subquery.

-- Q6. Can we find third highest salary using this approach?

-- Yes.

-- SELECT (
--     SELECT DISTINCT salary
--     FROM Employee
--     ORDER BY salary DESC
--     LIMIT 1 OFFSET 2
-- ) AS ThirdHighestSalary;

-- Because:

-- OFFSET 0 → 1st
-- OFFSET 1 → 2nd
-- OFFSET 2 → 3rd
-- Q7. How would you find 5th highest salary?
-- SELECT (
--     SELECT DISTINCT salary
--     FROM Employee
--     ORDER BY salary DESC
--     LIMIT 1 OFFSET 4
-- ) AS FifthHighestSalary;
-- Formula ⭐
-- Nth highest → OFFSET N - 1

-- So:

-- 2nd → OFFSET 1
-- 3rd → OFFSET 2
-- 4th → OFFSET 3
-- 5th → OFFSET 4
-- Nth → OFFSET N-1
-- Q8. Why DESC and not ASC?

-- Because we want highest salary first.

-- ORDER BY salary DESC

-- gives:

-- 300
-- 200
-- 100

-- If we use ASC:

-- 100
-- 200
-- 300

-- Then OFFSET 1 would give 200, but that would represent the second lowest, not second highest.

-- Q9. Can we use LIMIT 1, 1?

-- Yes, MySQL allows:

-- LIMIT 1, 1

-- It means:

-- LIMIT offset, number_of_rows

-- So:

-- LIMIT 1, 1

-- means:

-- Skip 1 row and return 1 row.

-- Equivalent to:

-- LIMIT 1 OFFSET 1

-- For interviews, LIMIT 1 OFFSET 1 is easier to explain.

-- Q10. LIMIT and OFFSET standard SQL hain?

-- LIMIT is widely supported but not universal SQL syntax. MySQL uses it.

-- Other databases may use things like:

-- FETCH FIRST

-- or

-- OFFSET ... FETCH

-- So interview mein bol sakte ho:

-- "LIMIT/OFFSET is commonly used in MySQL for pagination and selecting rows by position."
