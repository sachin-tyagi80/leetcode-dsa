CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    SET N = N-1;
  RETURN (
      # Write your MySQL query statement below.
      SELECT DISTINCT salary from Employee
      order by salary DESC
      LIMIT 1 OFFSET N

  );
END




-- 🎯 Interview Explanation

-- Interviewer pooche:

-- "Explain your approach."

-- Aap bol sakte ho:

-- "First, I remove duplicate salaries using DISTINCT because the question asks for the nth distinct highest salary. Then I sort the salaries in descending order. Since OFFSET uses zero-based indexing, I subtract one from N. Finally, I use LIMIT 1 with OFFSET N to return the required nth highest salary."

-- Hinglish mein:

-- "Pehle DISTINCT use karke duplicate salaries remove karta hoon. Phir salaries ko descending order mein sort karta hoon. Kyunki OFFSET zero se start hota hai, isliye N mein se 1 subtract karta hoon. Finally LIMIT 1 aur OFFSET use karke nth highest salary return karta hoon."

-- 🔥 Follow-up Questions
-- Q1. Why do we use DISTINCT?

-- Because question asks:

-- nth highest distinct salary

-- Example:

-- 500
-- 500
-- 400
-- 300

-- For second highest:

-- Without DISTINCT:

-- 500
-- 500
-- 400
-- 300

-- Second row = 500 ❌

-- With DISTINCT:

-- 500
-- 400
-- 300

-- Second highest = 400 ✅

-- Q2. Why do we subtract 1 from N?

-- Because OFFSET starts from zero.

-- N = 1 → OFFSET 0
-- N = 2 → OFFSET 1
-- N = 3 → OFFSET 2

-- Formula:

-- OFFSET = N - 1
-- Q3. What does LIMIT 1 do?
-- LIMIT 1

-- Sirf one row return karta hai.

-- Q4. What happens if Nth salary does not exist?

-- Example:

-- Salaries:
-- 300
-- 200

-- Aur:

-- N = 3

-- OFFSET 2 ke liye koi row nahi milegi.

-- Function automatically:

-- NULL

-- return karega. ✅

-- 🔥 Q5. Can we solve this using DENSE_RANK()?

-- Yes, aur ye interview mein bahut important follow-up hai.

-- CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
-- BEGIN
--     RETURN (
--         SELECT salary
--         FROM (
--             SELECT salary,
--                    DENSE_RANK() OVER (ORDER BY salary DESC) AS rnk
--             FROM Employee
--         ) AS ranked
--         WHERE rnk = N
--     );
-- END

-- Example:

-- Salary     Dense Rank
-- 500            1
-- 500            1
-- 400            2
-- 300            3

-- Agar:

-- N = 2

-- Answer:

-- 400
-- 🔥 Q6. RANK() aur DENSE_RANK() mein difference?

-- Example:

-- Salary
-- 500
-- 500
-- 400
-- 300
-- RANK()
-- 500 → 1
-- 500 → 1
-- 400 → 3
-- 300 → 4

-- Ranks skip hote hain.

-- DENSE_RANK()
-- 500 → 1
-- 500 → 1
-- 400 → 2
-- 300 → 3

-- Ranks skip nahi hote.

-- Is question ke liye:

-- DENSE_RANK() ✅

-- because hume distinct salaries chahiye.

-- ⭐ Interviewer ka tricky question
-- "Second highest ke liye tumhara solution kya hoga?"

-- Aap bol sakte ho:

-- SELECT (
--     SELECT DISTINCT salary
--     FROM Employee
--     ORDER BY salary DESC
--     LIMIT 1 OFFSET 1
-- ) AS SecondHighestSalary;
-- "Nth highest ke liye?"

-- Then:

-- OFFSET = N - 1

-- Aur function:

-- CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
-- BEGIN
--     SET N = N - 1;

--     RETURN (
--         SELECT DISTINCT salary
--         FROM Employee
--         ORDER BY salary DESC
--         LIMIT 1 OFFSET N
--     );
-- END
-- 🧠 Pattern yaad rakho
-- Nth Highest Distinct Value
--         ↓
-- DISTINCT
--         ↓
-- ORDER BY DESC
--         ↓
-- OFFSET N - 1
--         ↓
-- LIMIT 1
-- One-line interview answer:

-- "Remove duplicates, sort in descending order, skip N-1 rows, and return the next row."
