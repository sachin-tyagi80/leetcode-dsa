# Write your MySQL query statement below
select t.id 
from Weather t
join Weather y
   ON DATEDIFF(t.recordDate,y.recordDate) = 1
WHERE t.temperature > y.temperature;