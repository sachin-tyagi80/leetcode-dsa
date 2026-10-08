# Write your MySQL query statement below
SELECT 
    m.employee_id,
    m.name,
    COUNT(e.employee_id) AS reports_count,
    ROUND(AVG(e.age)) AS average_age
FROM employees e
JOIN employees m
    ON e.reports_to = m.employee_id
GROUP BY m.employee_id, m.name
ORDER BY m.employee_id;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- Interview Explanation

-- If the interviewer asks "Explain your approach", say:

-- "I use a self join because the manager and the employees who report to them are stored in the same Employees table. I treat one copy as the manager and another copy as the report. I join them using manager.employee_id = report.reports_to. Then I group by the manager and use COUNT to find the number of direct reports and AVG to calculate their average age. Finally, I use ROUND to return the average age as the nearest integer and order the result by employee_id."

-- That's a strong interview answer.

-- 14. Interview Follow-up Questions
-- Q1. Why do you use a SELF JOIN?

-- Because manager and employee information are stored in the same table.

-- We need to compare rows within the same table.

-- Q2. What is a SELF JOIN?

-- A self join is when a table is joined with itself, usually using aliases to represent different roles.

-- Here:

-- Employees manager
-- Employees report
-- Q3. Why do you use aliases?

-- Without aliases, it would be difficult to distinguish between the two copies of Employees.

-- We use:

-- manager
-- report

-- to clearly identify their roles.

-- Q4. Explain this condition:
-- manager.employee_id = report.reports_to

-- manager.employee_id is the manager's ID.

-- report.reports_to contains the ID of the manager that the employee reports to.

-- If they are equal, that employee reports to that manager.

-- Q5. Why use COUNT(report.employee_id)?

-- Because we need the number of employees reporting to each manager.

-- For example:

-- Michael → Alice
-- Michael → Bob

-- So:

-- COUNT = 2
-- Q6. Why use AVG(report.age)?

-- Because the question asks for the average age of the employees reporting to each manager.

-- Q7. Why use ROUND()?

-- The problem requires the average age rounded to the nearest integer.

-- Example:

-- 38.5 → 39

-- So:

-- ROUND(AVG(report.age))
-- Q8. Why use INNER JOIN instead of LEFT JOIN?

-- We only need employees who are actually managers.

-- An INNER JOIN returns only managers who have at least one matching report.

-- A LEFT JOIN would also include employees who have no reports, so we'd need additional filtering.

-- Q9. Can you use LEFT JOIN?

-- Yes, but then you'd need to make sure employees with zero reports aren't included.

-- For example:

-- SELECT
--     manager.employee_id,
--     manager.name,
--     COUNT(report.employee_id) AS reports_count,
--     ROUND(AVG(report.age)) AS average_age
-- FROM Employees manager
-- LEFT JOIN Employees report
--     ON manager.employee_id = report.reports_to
-- GROUP BY manager.employee_id, manager.name
-- HAVING COUNT(report.employee_id) > 0
-- ORDER BY manager.employee_id;

-- This also works.

-- But for this problem, the INNER JOIN solution is simpler.

-- Q10. What is the difference between COUNT(*) and COUNT(report.employee_id) here?

-- With the INNER JOIN, they produce the same count because every joined row has a report employee.

-- But:

-- COUNT(report.employee_id)

-- is more explicit because we are specifically counting employees who report to the manager.

-- With a LEFT JOIN, they are not equivalent:

-- COUNT(*)

-- would count the manager's unmatched row, while:

-- COUNT(report.employee_id)

-- would return 0.

-- This is an important interview point.

-- 🧠 Pattern to Remember

-- This is a classic SELF JOIN + GROUP BY problem.

-- Remember:

-- Same table
--     ↓
-- SELF JOIN
--     ↓
-- Manager ID = Employee's reports_to
--     ↓
-- GROUP BY Manager
--     ↓
-- COUNT reports
--     ↓
-- AVG report age
--     ↓
-- ROUND
-- Final code
-- SELECT
--     manager.employee_id,
--     manager.name,
--     COUNT(report.employee_id) AS reports_count,
--     ROUND(AVG(report.age)) AS average_age
-- FROM Employees manager
-- JOIN Employees report
--     ON manager.employee_id = report.reports_to
-- GROUP BY
--     manager.employee_id,
--     manager.name
-- ORDER BY
--     manager.employee_id;

-- One-line interview memory:

-- "SELF JOIN to connect manager with their reports, GROUP BY manager, COUNT the reports, AVG their ages, and ROUND the average."
