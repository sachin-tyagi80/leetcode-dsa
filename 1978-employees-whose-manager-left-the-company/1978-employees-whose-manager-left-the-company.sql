# Write your MySQL query statement below
SELECT employee_id
FROM Employees
WHERE salary < 30000
  AND manager_id NOT IN (
      SELECT employee_id
      FROM Employees
  )
ORDER BY employee_id;


-- 🎯 Interview mein kaise explain karna hai?

-- Aap interviewer ko bol sakte ho:

-- "First, I filter employees whose salary is less than 30,000. Then I make sure the employee has a manager. After that, I check whether the manager_id is not present in the Employees table. If the manager ID does not exist, it means the manager has left the company. Finally, I sort the employee IDs in ascending order."

-- Hinglish:

-- "Pehle salary 30,000 se kam wale employees filter karenge. Phir manager_id IS NOT NULL se ensure karenge ki employee ka manager hai. Uske baad check karenge ki uska manager_id Employees table mein exist nahi karta. Agar manager ka record nahi hai, iska matlab manager company chhod chuka hai."


-- ⚠️ Important Interview Follow-up: NOT IN vs NOT EXISTS

-- Interviewer pooch sakta hai:

-- Q1. Why did you use NOT IN?

-- Answer:

-- NOT IN checks that the manager_id is not present in the list of employee IDs returned by the subquery.

-- Q2. Is NOT EXISTS better?

-- Aap bol sakte ho:

-- NOT EXISTS is often safer when NULL values can be involved because NOT IN can behave unexpectedly if the subquery returns NULL.

-- Example:

-- SELECT e.employee_id
-- FROM Employees e
-- WHERE e.salary < 30000
--   AND e.manager_id IS NOT NULL
--   AND NOT EXISTS (
--       SELECT 1
--       FROM Employees m
--       WHERE m.employee_id = e.manager_id
--   );

-- Interview mein NOT EXISTS solution bhi aana chahiye.

-- 🔥 Q3. Why manager_id IS NOT NULL?

-- Answer:

-- Because a NULL manager_id means the employee has no manager. It does not mean that the manager has left the company.

-- Very important question.

-- 🔥 Q4. Can you solve this using JOIN?

-- Yes.

-- SELECT e.employee_id
-- FROM Employees e
-- LEFT JOIN Employees m
--     ON e.manager_id = m.employee_id
-- WHERE e.salary < 30000
--   AND e.manager_id IS NOT NULL
--   AND m.employee_id IS NULL
-- ORDER BY e.employee_id;

-- Here we are doing a self LEFT JOIN.

-- e = Employee
-- m = Manager
-- Same Employees table is used twice.
-- 🔥 Q5. What is a Self Join?

-- A self join means joining a table with itself.

-- Here:

-- Employees e
-- LEFT JOIN Employees m

-- because manager is also an employee.

-- Relationship:

-- Employee.manager_id
--         ↓
-- Manager.employee_id
-- 🔥 Q6. Why LEFT JOIN?

-- Because we want to keep employees even when their manager record doesn't exist.

-- Then:

-- m.employee_id IS NULL

-- means no matching manager was found.

-- 🔥 Q7. What does this mean?
-- SELECT employee_id
-- FROM Employees

-- inside brackets?

-- It's a subquery.

-- A query inside another query is called a subquery.

-- The inner query executes conceptually to produce the list that NOT IN checks against.

-- 🔥 Q8. Why ORDER BY employee_id?

-- Because the question asks the result in ascending order.

-- ORDER BY employee_id ASC;

-- and

-- ORDER BY employee_id;

-- are effectively the same because ASC is default.

-- 🔥 Q9. What if salary is exactly 30000?

-- It will not be selected.

-- Because:

-- salary < 30000

-- means strictly less than 30,000.

-- If question said 30,000 or less:

-- salary <= 30000
-- 🔥 Q10. Why not use manager_id != employee_id?

-- Because that checks whether the manager ID is different from the employee ID.

-- It doesn't tell us whether the manager exists.

-- We need to check:

-- Does manager_id exist in Employees table?

-- That's why we use NOT IN, NOT EXISTS, or LEFT JOIN.

-- 🧠 Interview Pattern to Remember

-- When you see:

-- "Find employees whose manager/parent/customer/reference record does not exist."

-- Think:

-- Referenced ID
--      ↓
-- Does it exist?
--      ↓
-- NO
--      ↓
-- NOT EXISTS / NOT IN / LEFT JOIN + IS NULL
-- Most important 3 patterns:
-- -- 1. NOT IN
-- manager_id NOT IN (SELECT employee_id FROM Employees)
-- -- 2. NOT EXISTS
-- NOT EXISTS (
--     SELECT 1
--     FROM Employees m
--     WHERE m.employee_id = e.manager_id
-- )
-- -- 3. LEFT JOIN
-- LEFT JOIN Employees m
-- ON e.manager_id = m.employee_id
-- WHERE m.employee_id IS NULL
