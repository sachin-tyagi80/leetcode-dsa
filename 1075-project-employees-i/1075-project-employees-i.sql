# Write your MySQL query statement below
SELECT 
    p.project_id,
    ROUND(AVG(experience_years),2) AS average_years
FROM Project p
JOIN Employee e
    ON p.employee_id = e.employee_id
GROUP BY p.project_id 


/////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- 🎤 Interview Explanation

-- You can say:

-- “First, I join the Project and Employee tables using employee_id because the Project table tells us which employees belong to each project, while the Employee table contains their experience years. Then I group the result by project_id and use AVG() to calculate the average experience for each project. Finally, I use ROUND() to keep the result up to two decimal places.”

-- That's a good 30-second interview answer.

-- 🔥 Interview Follow-up Questions
-- 1. Why do we need a JOIN?

-- Because project_id and employee_id are in Project, but experience_years is in Employee.

-- We need both tables to calculate the average.

-- 2. Why do we join using employee_id?

-- Because employee_id is the common/related column between the two tables.

-- p.employee_id = e.employee_id
-- 3. Why use AVG()?

-- Because the question asks for the average experience.

-- AVG(e.experience_years)
-- 4. Why use GROUP BY project_id?

-- Because we need one average for each project, not one average for the entire company.

-- 5. Why use ROUND()?

-- The question specifically asks for the answer rounded to 2 decimal places.

-- ROUND(..., 2)
-- 6. Can we use LEFT JOIN instead of JOIN?

-- Technically, you could, but based on the problem's data relationship, every Project.employee_id references an employee in Employee. Therefore, JOIN/INNER JOIN is sufficient.

-- 7. What happens if an employee works on multiple projects?

-- The employee will appear once for each project in the joined result.

-- For example:

-- Employee 1 → Project 1
-- Employee 1 → Project 2

-- Their experience contributes to the average of both projects.

-- 8. What is the difference between AVG() and SUM()/COUNT()?

-- These are equivalent for this problem:

-- AVG(e.experience_years)

-- and

-- SUM(e.experience_years) / COUNT(e.experience_years)

-- AVG() is simpler and clearer here.

-- 9. Why don't we use DISTINCT?

-- Because (project_id, employee_id) is already the primary key of Project.

-- That means the same employee cannot appear twice for the same project.

-- 🧠 Pattern to Remember

-- This problem is a very common JOIN + GROUP BY + AVG pattern:

-- Need data from another table
--         ↓
--       JOIN
--         ↓
-- Group by required entity
--         ↓
--    GROUP BY project_id
--         ↓
-- Calculate average
--         ↓
--         AVG()
--         ↓
--      ROUND()
-- One-line memory trick:

-- JOIN → GROUP BY → AVG → ROUND

-- This pattern is worth remembering for placement SQL interviews.
