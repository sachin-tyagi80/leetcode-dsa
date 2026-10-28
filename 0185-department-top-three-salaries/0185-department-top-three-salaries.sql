SELECT d.name AS Department,
       e.name AS Employee,
       e.salary AS Salary
FROM (
    SELECT name,
           salary,
           departmentId,
           DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS rnk
    FROM Employee
) e
JOIN Department d ON d.id = e.departmentId
WHERE e.rnk <= 3;

////////////////////////////////////////////////////////////////////////////////////////////////////
-- Alternative without window functions (for older MySQL): a correlated subquery counts the distinct higher salaries in the same department, and keeps the employee if fewer than 3 exist.

-- sql
-- SELECT d.name AS Department, e.name AS Employee, e.salary AS Salary
-- FROM Employee e
-- JOIN Department d ON d.id = e.departmentId
-- WHERE 3 > (
--     SELECT COUNT(DISTINCT e2.salary)
--     FROM Employee e2
--     WHERE e2.departmentId = e.departmentId
--       AND e2.salary > e.salary
-- );

-- The window-function version is cleaner and usually faster, so prefer it.


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- How to explain it to the interviewer

-- 1. Restate the problem (10 seconds)
-- “I need, for each department, the employees whose salary is among the top three distinct salary values in that department. If two people tie, both count, and the tie doesn’t use up an extra slot.”

-- 2. Explain the approach
-- “I rank salaries within each department using DENSE_RANK(). I partition by departmentId so every department is ranked on its own, and order by salary descending, so the highest salary gets rank 1. I use DENSE_RANK instead of RANK because ties should share a rank and the next distinct salary should get the next consecutive rank, with no gaps.”

-- 3. Walk through the query
-- “The inner query adds a rnk column to each employee row. The outer query joins with Department to get the department name, then filters rnk <= 3. I rank in a subquery because window functions can’t be used directly in a WHERE clause.”

-- 4. Run it on the example
-- “In IT the salaries are 90000, 85000, 85000, 70000, 69000. Ranks are 1, 2, 2, 3, 4. So Max, Joe, Randy and Will qualify, and Janet at rank 4 doesn’t. With RANK(), Will would get rank 4 and be wrongly excluded.”

-- 5. Complexity
-- “It’s roughly O(n log n) because of the sort inside each partition. An index on (departmentId, salary) can help avoid the sort.”

-- Likely follow-up questions

-- Q1. What’s the difference between ROW_NUMBER, RANK and DENSE_RANK?
-- For salaries 90, 85, 85, 70: ROW_NUMBER gives 1, 2, 3, 4 (always unique, ties broken arbitrarily). RANK gives 1, 2, 2, 4 (ties share a rank, then a gap). DENSE_RANK gives 1, 2, 2, 3 (no gap). This problem needs DENSE_RANK.

-- Q2. Why a subquery? Can’t you put DENSE_RANK() <= 3 in WHERE?
-- No. WHERE is evaluated before window functions, so the rank doesn’t exist yet. You need a subquery or CTE, then filter on the alias.

-- Q3. How would you solve it without window functions?
-- Use the correlated subquery: keep an employee if fewer than 3 distinct salaries in their department are higher than theirs. It works on older MySQL, but it is O(n²) in the worst case, so it’s slower on large tables.

-- Q4. What if the requirement were top N, with N variable?
-- Replace 3 with a parameter, for example rnk <= @N, or wrap the query in a function or procedure that takes N.

-- Q5. What if an employee has a NULL salary?
-- In ORDER BY salary DESC, MySQL treats NULLs as the smallest values, so they rank last and are unlikely to appear in the top 3. In PostgreSQL, NULLs sort first in descending order unless you add NULLS LAST. To be safe, add WHERE salary IS NOT NULL in the inner query.

-- Q6. What if an employee has no department, or a department has no employees?
-- The inner JOIN drops employees whose department doesn’t exist. Departments with no employees don’t appear at all, which is correct here since there is nobody to list. A LEFT JOIN would only matter if we wanted to keep unmatched rows.

-- Q7. How would you optimize it for millions of rows?
-- Add a composite index on Employee(departmentId, salary DESC) so the database can read rows already ordered per department. Filter early where possible, and check the execution plan with EXPLAIN.

-- Q8. Subquery vs CTE?
-- Same result and usually the same plan. A CTE is more readable, especially if the logic grows:

-- sql
-- WITH ranked AS (
--     SELECT name, salary, departmentId,
--            DENSE_RANK() OVER (PARTITION BY departmentId ORDER BY salary DESC) AS rnk
--     FROM Employee
-- )
-- SELECT d.name AS Department, r.name AS Employee, r.salary AS Salary
-- FROM ranked r
-- JOIN Department d ON d.id = r.departmentId
-- WHERE r.rnk <= 3;

-- Q9. How would you do this in Pandas?

-- python
-- df = employee.merge(department, left_on='departmentId', right_on='id', suffixes=('', '_dept'))
-- df['rnk'] = df.groupby('departmentId')['salary'].rank(method='dense', ascending=False)
-- result = df[df['rnk'] <= 3][['name_dept', 'name', 'salary']]
-- result.columns = ['Department', 'Employee', 'Salary']

-- method='dense' is the Pandas equivalent of DENSE_RANK.

-- Q10. How would you test it?
-- Departments with fewer than 3 employees, all employees with the same salary, many ties at the boundary (for example the 3rd and 4th salaries equal), a single-employee department, and NULL salaries.

-- Tip: In the interview, state the tie-handling trap (RANK vs DENSE_RANK) up front. It’s the main thing this problem tests, and saying it unprompted shows you understand the requirement, not just the syntax.



