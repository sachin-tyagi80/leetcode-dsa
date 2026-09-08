# Write your MySQL query statement below
select MAX(num) as num from MyNumbers
where num IN (select num from MyNumbers Group by num Having count(num) = 1);