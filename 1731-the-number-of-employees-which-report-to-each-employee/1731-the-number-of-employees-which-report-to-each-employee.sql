# Write your MySQL query statement below
SELECT 
   manager.employee_id,
   manager.name,
   COUNT(report.employee_id) AS reports_count,
   ROUND(AVG(report.age)) AS average_age
FROM Employees manager
JOIN Employees report
        ON manager.employee_id = report.reports_to

    GROUP BY 
        manager.employee_id,
        manager.name
ORDER BY 
     manager.employee_id;