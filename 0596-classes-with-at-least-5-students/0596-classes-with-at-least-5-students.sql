# Write your MySQL query statement below
SELECT class  
FROM Courses  
GROUP BY class  
HAVING COUNT(student) >= 5;


/////////////////////////////////////////////////////////////////////////////////////////////////
-- 🎤 Interview Explanation

-- You can say:

-- “First, I group the Courses table by class because I need the number of students for each class. Then I use COUNT() to count the students in each group. Finally, I use HAVING because I need to filter groups based on the aggregate count, and I keep only classes where the student count is at least 5.”

-- Short version:

-- “GROUP BY class, count the students, and use HAVING COUNT(student) >= 5.”

-- 🔥 Interview Follow-up Questions
-- 1. Why use GROUP BY?

-- Because we need the student count separately for each class.

-- Without GROUP BY, COUNT(student) would count students across the entire table.

-- 2. Why use HAVING instead of WHERE?

-- Because COUNT(student) is an aggregate result.

-- WHERE  → before GROUP BY
-- HAVING → after GROUP BY
-- 3. Can we write COUNT(*) instead of COUNT(student)?

-- Yes.

-- SELECT class
-- FROM Courses
-- GROUP BY class
-- HAVING COUNT(*) >= 5;

-- For this problem, both work because student is not NULL in the given table.

-- 4. Why not use COUNT(DISTINCT student)?

-- The table has:

-- (student, class)

-- as the primary key.

-- Therefore, the same student cannot appear twice in the same class.

-- So:

-- COUNT(student)

-- is enough.

-- If duplicates were possible, then:

-- COUNT(DISTINCT student)

-- would be safer.

-- 5. What is the difference between WHERE and HAVING?
-- WHERE	HAVING
-- Filters rows	Filters groups
-- Before GROUP BY	After GROUP BY
-- Normally cannot use aggregate result directly	Can use aggregate functions
-- Example: WHERE class = 'Math'	Example: HAVING COUNT(*) >= 5

-- Interview line:

-- “WHERE filters individual records, while HAVING filters grouped records after aggregation.”

-- 6. What happens if a class has exactly 5 students?

-- It will be included because:

-- COUNT(student) >= 5

-- means 5 or more.

-- 7. What if the condition was more than 5 students?

-- We would use:

-- HAVING COUNT(student) > 5

-- Difference:

-- >= 5 → 5, 6, 7, ...
-- > 5  → 6, 7, 8, ...
-- 8. Can we use a subquery for this problem?

-- Yes, but it is unnecessary.

-- For example:

-- SELECT class
-- FROM (
--     SELECT class, COUNT(*) AS student_count
--     FROM Courses
--     GROUP BY class
-- ) t
-- WHERE student_count >= 5;

-- But the direct GROUP BY + HAVING solution is cleaner.

-- 🧠 Pattern to Remember

-- This is one of the most important SQL interview patterns:

-- Need groups
--     ↓
-- GROUP BY
--     ↓
-- Need count/sum/avg
--     ↓
-- COUNT / SUM / AVG
--     ↓
-- Need condition on aggregate
--     ↓
-- HAVING
-- Memory Trick

-- “GROUP first, COUNT next, HAVING last.”

-- For this problem:

-- SELECT class
-- FROM Courses
-- GROUP BY class
-- HAVING COUNT(student) >= 5;
-- Pattern:

-- GROUP BY → COUNT → HAVING
