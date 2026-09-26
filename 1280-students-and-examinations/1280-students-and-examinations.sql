# Write your MySQL query statement below
SELECT
    s.student_id,
    s.student_name,
    sub.subject_name,
    COUNT(e.subject_name) AS attended_exams
FROM Students s
CROSS JOIN Subjects sub
LEFT JOIN Examinations e
    ON s.student_id = e.student_id
    AND sub.subject_name = e.subject_name
GROUP BY
    s.student_id,
    s.student_name,
    sub.subject_name
ORDER BY
    s.student_id,
    sub.subject_name;


///////////////////////////////////////////////////////////////////////////
-- 14. Interview Explanation

-- If the interviewer asks:

-- Explain your approach.

-- Say:

-- "First, I use a CROSS JOIN between Students and Subjects because I need every possible student-subject combination, including combinations where the student attended zero exams. Then I use a LEFT JOIN with Examinations using both student_id and subject_name. This keeps combinations with no exam records. Finally, I group by student and subject and use COUNT on the examination subject column to calculate the number of exams attended. I use COUNT(column) instead of COUNT(*) because COUNT(column) ignores NULL values and therefore returns 0 for students who did not attend an exam."

-- That's a strong interview explanation.

-- 15. Interview Follow-up Questions
-- Q1. Why CROSS JOIN?

-- Because we need every student with every subject.

-- Q2. Why LEFT JOIN?

-- Because we need to keep student-subject combinations even when there is no examination record.

-- Q3. Why not INNER JOIN?

-- INNER JOIN would remove students/subjects where no matching examination exists, so we wouldn't get 0 counts.

-- Q4. Why not use COUNT(*)?

-- COUNT(*) counts the LEFT JOIN row even when the examination columns are NULL. COUNT(e.subject_name) ignores NULL and correctly returns 0.

-- Q5. Why do we need GROUP BY?

-- Because we need one result for each student-subject combination and need to calculate the exam count for each group.

-- Q6. Why do we join using two conditions?
-- ON s.student_id = e.student_id
-- AND sub.subject_name = e.subject_name

-- Because an examination record belongs to a specific:

-- student + subject

-- We need to match both.

-- Q7. What happens if the same exam record appears multiple times?

-- The question says duplicates can exist.

-- For example:

-- 1 | Math
-- 1 | Math
-- 1 | Math

-- COUNT(e.subject_name) counts all three rows:

-- 3

-- That's exactly what we need because the question asks for the number of times attended.

-- Q8. Why not use COUNT(DISTINCT e.subject_name)?

-- Because we want to count every attendance, including duplicates.

-- For Alice:

-- Math
-- Math
-- Math

-- We need:

-- 3

-- But:

-- COUNT(DISTINCT e.subject_name)

-- would give:

-- 1

-- ❌ Wrong.

-- Q9. What is the difference between CROSS JOIN and INNER JOIN?

-- CROSS JOIN:

-- Every row × every row

-- No matching condition is required.

-- INNER JOIN:

-- Only matching rows

-- based on a condition.

-- Q10. What happens if there are 5 students and 4 subjects?

-- CROSS JOIN produces:

-- 5 × 4 = 20

-- student-subject combinations.

-- 🧠 Most Important Pattern

-- This problem has a very important SQL pattern:

-- Need ALL combinations
--         ↓
--     CROSS JOIN
--         ↓
-- Need matching data + keep missing
--         ↓
--     LEFT JOIN
--         ↓
-- Need count
--         ↓
-- COUNT(column)
--         ↓
-- GROUP BY
-- Remember this:

-- CROSS JOIN = Create all combinations
-- LEFT JOIN = Keep missing combinations
-- COUNT(column) = Count matches, NULL becomes 0

-- This pattern is very useful for SQL interviews.
