# Write your MySQL query statement below
select employee_id,department_id
from(
    select
         employee_id,
         department_id,
         primary_flag,
         COUNT(*) OVER (PARTITION BY employee_id) AS dept_count
    FROM Employee
) t
WHERE primary_flag = 'Y' OR dept_count = 1;


-- 8. Interview Explanation
-- English

-- “There are two cases. If an employee has multiple departments, we select the row where primary_flag is Y. If an employee belongs to only one department, we select that department regardless of the flag. I use a window function with PARTITION BY employee_id to count the number of departments for each employee.”

-- Hinglish

-- “Is question me two cases hain. Agar employee multiple departments me hai, to primary_flag = 'Y' wali department select karenge. Agar employee sirf ek department me hai, to wahi department select karenge, chahe flag N ho. Employee ke departments count karne ke liye COUNT(*) OVER(PARTITION BY employee_id) use kiya.”

-- 9. Interview Follow-up Questions
-- Q1. PARTITION BY employee_id kya karta hai?

-- Har employee ke records ko separately count karta hai.

-- Employee 2 → 2 departments
-- Employee 4 → 3 departments
-- Q2. GROUP BY ki jagah window function kyu?

-- GROUP BY rows ko collapse kar deta hai.

-- Window function count calculate karta hai but original rows ko preserve rakhta hai.

-- Q3. Employee ke only one department hone par primary_flag kya hoga?

-- Question ke according:

-- primary_flag = 'N'

-- Lekin phir bhi wahi uska department report karna hai.

-- Q4. OR kyu use kiya?

-- Because there are two valid conditions:

-- primary_flag = 'Y'
--         OR
-- dept_count = 1
-- 10. 🧠 Pattern Yaad Rakho

-- Is question ka core pattern:

-- Employee
--    ↓
-- Count departments per employee
--    ↓
--  ┌────────────────────────┐
--  │ primary_flag = 'Y'     │
--  │          OR            │
--  │ department count = 1   │
--  └────────────────────────┘
--    ↓
-- Primary department
-- 🔥 Most important SQL pattern
-- COUNT(*) OVER (PARTITION BY employee_id)

-- Meaning:

-- Har employee ke records count karo, but rows ko remove mat karo.

-- Aur final condition:

-- WHERE primary_flag = 'Y'
--    OR dept_count = 1

-- Is question ko yaad rakhne ka shortcut:
-- 👉 Y wala select karo OR single-department employee select karo.



SELECT employee_id, department_id
FROM Employee
WHERE primary_flag = 'Y'
   OR employee_id IN (
       SELECT employee_id
       FROM Employee
       GROUP BY employee_id
       HAVING COUNT(department_id) = 1
   );

-- 9. Interview Explanation
-- English

-- “I handle two cases. First, I select employees whose primary_flag is Y. Second, I find employees having exactly one department using GROUP BY and HAVING COUNT(department_id) = 1. I then use IN to include those employees as well.”

-- Hinglish

-- “Main two cases handle kar raha hoon. Pehle primary_flag = 'Y' wali departments select karta hoon. Phir subquery se GROUP BY employee_id aur HAVING COUNT(department_id) = 1 use karke single-department employees find karta hoon. IN ke through un employees ko bhi result me include karta hoon.”

