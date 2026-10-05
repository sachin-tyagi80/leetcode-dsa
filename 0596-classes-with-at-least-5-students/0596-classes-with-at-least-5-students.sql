# Write your MySQL query statement below
select class from Courses
Group by Class
having count(student) >=5;