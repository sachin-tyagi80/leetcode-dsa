# Write your MySQL query statement below
select teacher_id , COUNT(DISTINCT subject_id) AS cnt
from Teacher GROUP BY teacher_id;



-- 🎯 Interview Explanation

-- Interviewer: "Explain your approach."

-- Aap bolo:

-- "We need to count the number of unique subjects taught by each teacher. Since a teacher can teach the same subject in multiple departments, I use COUNT(DISTINCT subject_id) to avoid counting the same subject multiple times. Then I use GROUP BY teacher_id to calculate the count separately for each teacher."

-- Short version:

-- "I group the records by teacher and count the distinct subject IDs for each teacher."

-- 🔥 Follow-up Questions
-- Q1. Why do we use DISTINCT?

-- Because same teacher same subject ko multiple departments mein teach kar sakta hai.

-- Example:

-- teacher_id = 1
-- subject_id = 2
-- dept_id = 3

-- teacher_id = 1
-- subject_id = 2
-- dept_id = 4

-- Subject 2 actually one unique subject hai.

-- So:

-- COUNT(DISTINCT subject_id)
-- Q2. What happens if we use only COUNT(subject_id)?
-- SELECT teacher_id, COUNT(subject_id)
-- FROM Teacher
-- GROUP BY teacher_id;

-- Teacher 1 ke liye:

-- 2
-- 2
-- 3

-- count = 3

-- ❌ But correct answer = 2

-- Isliye DISTINCT necessary hai.

-- Q3. Why do we use GROUP BY teacher_id?

-- "Because we need a separate count for every teacher."

-- Without GROUP BY, hume poori table ka single count mil sakta hai.

-- Q4. Why don't we use COUNT(DISTINCT dept_id)?

-- Because question unique subjects pooch raha hai, departments nahi.

-- Correct:

-- COUNT(DISTINCT subject_id)

-- Not:

-- COUNT(DISTINCT dept_id)
-- Q5. Can we use GROUP BY teacher_id, subject_id?

-- Technically grouping duplicate subject-teacher combinations identify karne ke liye kiya ja sakta hai, but then result ko further count karna padega.

-- Is problem ke liye direct:

-- COUNT(DISTINCT subject_id)

-- much simpler hai.

-- Q6. Why is dept_id not included in GROUP BY?

-- Because hume department-wise result nahi chahiye.

-- Hume:

-- Teacher → Unique Subjects

-- chahiye.

-- 🧠 Important Pattern

-- Ye pattern bahut important hai:

-- SELECT column, COUNT(DISTINCT another_column)
-- FROM table
-- GROUP BY column;

-- Meaning:

-- Har group ke andar unique values count karo.

-- Is question mein:
-- teacher_id
--     ↓
-- GROUP BY
--     ↓
-- subject_id
--     ↓
-- DISTINCT
--     ↓
-- COUNT
-- ⭐ Final Code
-- SELECT teacher_id, COUNT(DISTINCT subject_id) AS cnt
-- FROM Teacher
-- GROUP BY teacher_id;

-- Main concepts: GROUP BY ⭐ + COUNT() + DISTINCT

-- Interview trick:
-- 👉 "Har teacher ke unique subjects count karne hain" = GROUP BY teacher_id + COUNT(DISTINCT subject_id)
