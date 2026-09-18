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