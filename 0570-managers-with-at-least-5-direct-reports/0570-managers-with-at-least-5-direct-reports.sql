# Write your MySQL query statement below
SELECT m.name
FROM Employee e
JOIN Employee m
    ON e.managerId = m.id
GROUP BY m.id, m.name
HAVING COUNT(e.id) >= 5;


/////////////////////////////////////////////////////////////////
-- 8. Interview Explanation

-- Interview mein English mein aise explain karna:

-- “I used a self join because the employee and manager information is stored in the same table. I joined the table using the employee's manager ID and the manager's employee ID. Then, I grouped the records by manager and used COUNT() to calculate the number of direct reports. Finally, I used the HAVING clause to return only managers who have at least five direct reports.”

-- 9. Interview Follow-up Questions with Answers

-- Q1. What is a self join?

-- Answer: A self join is a join in which a table is joined with itself. It is useful when related records, such as employees and managers, are stored in the same table.

-- Q2. Why do we use HAVING instead of WHERE?

-- Answer: WHERE filters individual rows before grouping, whereas HAVING filters groups after aggregation. Since we need to filter managers based on employee counts, we use HAVING.

-- Q3. Why do we use COUNT(e.id)?

-- Answer: Each joined row represents one employee reporting to a manager. COUNT(e.id) counts the direct reports for each manager.

-- Q4. Why do we select m.name instead of e.name?

-- Answer: m represents the manager, and e represents the reporting employee. Since the question asks for manager names, we select m.name.

-- Q5. Why do we use GROUP BY m.id, m.name?

-- Answer: It groups employees by their manager so that we can count the direct reports for each manager separately.

-- Q6. What is the difference between INNER JOIN and LEFT JOIN?

-- Answer: INNER JOIN returns only matching rows from both tables. LEFT JOIN returns all rows from the left table, including those without a match in the right table.

-- Q7. What are direct and indirect reports?

-- Answer: Direct reports work immediately under a manager. Indirect reports work under another employee who reports to that manager. This query counts only direct reports.

-- Q8. Can we solve this problem using a subquery?

-- Answer: Yes. We can group employees by managerId in a subquery and use HAVING COUNT(*) >= 5 to find qualifying manager IDs.

-- SELECT name
-- FROM Employee
-- WHERE id IN (
--     SELECT managerId
--     FROM Employee
--     GROUP BY managerId
--     HAVING COUNT(*) >= 5
-- );

-- Q9. Why can't we write WHERE COUNT(e.id) >= 5?

-- Answer: Aggregate functions such as COUNT() cannot be used directly in the WHERE clause to filter groups. We use HAVING for that purpose.

-- Q10. What happens if we change >= 5 to > 5?

-- Answer: Only managers with more than five direct reports will be selected. Managers with exactly five reports will be excluded.

-- 10. Pattern Yaad Rakho

-- Self Join → GROUP BY → HAVING COUNT() → SELECT Manager Name

-- Ye pattern tab use karna jab question mein manager-wise employee count ya kisi group mein minimum number of records find karne hon.

-- Aage se tumhare SQL LeetCode questions ko isi complete format mein explain karunga.
