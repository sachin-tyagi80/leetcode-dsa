# Write your MySQL query statement below
select e.name,b.bonus
FROM Employee e
LEFT JOIN Bonus b
ON e.empId = b.empId
WHERE b.bonus < 1000 or b.bonus IS NULL;


////////////////////////////////////////////////////////////////////////////////////

-- 10. Interview Explanation
-- English

-- "I use a LEFT JOIN because I need to include employees who don't have a bonus record. After joining Employee and Bonus using empId, I filter employees whose bonus is less than 1000 or whose bonus is NULL."

-- Hindi

-- "Main LEFT JOIN use karta hoon kyunki mujhe un employees ko bhi include karna hai jinko bonus nahi mila. LEFT JOIN ke baad bonus less than 1000 ya NULL hone wale employees ko WHERE condition se filter karta hoon."

-- 11. Interview Follow-up Questions
-- Q1. LEFT JOIN hi kyu use kiya?

-- Because all employees need to be considered, including those without bonus.

-- Hindi: Jinko bonus nahi mila unka record bhi chahiye, isliye LEFT JOIN.

-- Q2. b.bonus = NULL kyu nahi?

-- ❌ Wrong:

-- b.bonus = NULL

-- ✅ Correct:

-- b.bonus IS NULL

-- SQL me NULL ko = se compare nahi karte.

-- Q3. OR kyu use kiya?

-- Question me either/or hai:

-- bonus < 1000
-- OR
-- no bonus

-- Isliye:

-- WHERE b.bonus < 1000
--    OR b.bonus IS NULL
-- Q4. Agar AND use karein?
-- WHERE b.bonus < 1000
--   AND b.bonus IS NULL

-- Ye practically koi row return nahi karega.

-- Ek value same time:

-- < 1000
-- AND
-- NULL

-- nahi ho sakti.

-- Q5. LEFT JOIN ke baad missing bonus NULL kyu hota hai?

-- Because matching Bonus row nahi milti, so right-side columns become NULL.

-- Employee        Bonus
-- Brad            NULL
-- 🧠 Pattern Yaad Rakho

-- Ye pattern bahut important hai:

-- "Main table ke records chahiye, even if second table me match nahi hai"
-- FROM A
-- LEFT JOIN B
--     ON A.id = B.id
-- "Second table me match nahi mila"
-- WHERE B.id IS NULL
-- Is question ka complete pattern:
-- SELECT e.name, b.bonus
-- FROM Employee e
-- LEFT JOIN Bonus b
--     ON e.empId = b.empId
-- WHERE b.bonus < 1000
--    OR b.bonus IS NULL;

-- 🧠 Shortcut:

-- LEFT JOIN → Missing records bhi chahiye
-- IS NULL → Matching record nahi mila
-- OR → Multiple conditions me se koi ek true ho
