# Write your MySQL query statement below
SELECT m.name
FROM Employee e
JOIN Employee m
    ON e.managerId = m.id
GROUP BY m.id, m.name
HAVING COUNT(e.id) >= 5;


/////////////////////////////////////////////////////////////////
-- Interview Explanation

-- You can explain this solution in an interview like this:

-- “I used a self join because the employee and manager information is stored in the same table.

-- First, I joined the table with itself using e.managerId = m.id to connect each employee to their manager.

-- Then, I grouped the records by the manager's ID and name using GROUP BY.

-- Next, I used COUNT(e.id) to count how many direct reports each manager has. The HAVING clause filters the groups and keeps only managers who have at least five direct reports.

-- Finally, I selected m.name to return the names of those managers.”


-- Interview Follow-up Questions

-- Q1. Why do we use a self join in this problem?

-- Because the Employee table contains both employees and their managers. We join the table with itself to connect an employee's managerId to the manager's id.

-- Q2. What is the difference between WHERE and HAVING?

-- WHERE filters individual rows before grouping. HAVING filters groups after GROUP BY, so it is appropriate for conditions such as COUNT(e.id) >= 5.

-- Q3. Why do we use COUNT(e.id)?

-- Each joined row represents one employee reporting to a manager. Counting e.id gives the number of direct reports for each manager.

-- Q4. Why do we select m.name instead of e.name?

-- m represents the manager, while e represents the reporting employee. The question asks for manager names.

-- Q5. What is the purpose of GROUP BY m.id, m.name?

-- It groups all reporting employees under each manager so we can count the reports per manager.

-- Q6. What does an INNER JOIN do here?

-- It returns only matching employee-manager pairs. Employees without a matching manager are excluded.

-- Q7. What is the difference between direct and indirect reports?

-- Direct reports report immediately to a manager. Indirect reports are employees who report through another manager. This query counts only direct reports.

-- Q8. Can we solve this problem without a self join?

-- Yes. A subquery can group employees by managerId, filter groups with COUNT(*) >= 5, and use IN to retrieve the corresponding manager names.
