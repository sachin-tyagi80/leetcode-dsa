# Write your MySQL query statement below
select name from Customer
where referee_id != 2 or referee_id is null;




-- 👨‍💻 Interviewer:

-- How would you solve this problem?

-- 🗣️ Aap:

-- "We have a Customer table containing the customer's name and the ID of the customer who referred them. We need to find customers who were either not referred by customer ID 2 or were not referred by anyone."

-- "So, I select the name column from the Customer table and apply a WHERE condition. If referee_id is not equal to 2, we include the customer. We also need to include customers whose referee_id is NULL."

-- Then code:

-- SELECT name
-- FROM Customer
-- WHERE referee_id != 2
--    OR referee_id IS NULL;
-- 🔍 Interviewer: Why did you use OR?
-- Answer:

-- "Because there are two conditions. A customer should be included if either their referee is not customer 2, or they have no referee at all."

-- Condition 1 → referee_id != 2
-- OR
-- Condition 2 → referee_id IS NULL
-- 🔍 Interviewer: Why IS NULL instead of = NULL?
-- ⭐ Very Important

-- Aap bolo:

-- "In SQL, NULL represents a missing or unknown value. We cannot compare NULL using = or !=. Therefore, we use IS NULL or IS NOT NULL."

-- ❌ Wrong:

-- referee_id = NULL

-- ❌ Wrong:

-- referee_id != NULL

-- ✅ Correct:

-- referee_id IS NULL

-- ✅ Correct:

-- referee_id IS NOT NULL
-- 🔥 Follow-up Questions
-- Q1. What if we write only this?
-- SELECT name
-- FROM Customer
-- WHERE referee_id != 2;

-- Answer:

-- "Customers with NULL referee_id will not be returned because comparisons with NULL result in UNKNOWN, not TRUE."

-- Isliye IS NULL condition separately add karni padti hai.

-- Q2. Can we use <> instead of !=?

-- Yes.

-- SELECT name
-- FROM Customer
-- WHERE referee_id <> 2
--    OR referee_id IS NULL;

-- <> and != both mean not equal in MySQL.

-- Q3. Can we use NOT IN?

-- Haan, but careful with NULL.

-- WHERE referee_id NOT IN (2)

-- Ye NULL rows ko include nahi karega.

-- Isliye:

-- WHERE referee_id NOT IN (2)
--    OR referee_id IS NULL;

-- use karna padega.

-- Q4. Why don't we need GROUP BY?

-- "Because we are not grouping or performing any aggregate calculation. We only need to filter individual rows, so WHERE is sufficient."

-- Q5. Why don't we need ORDER BY?

-- "The problem says the result can be returned in any order, so sorting is not required."

-- Q6. What is the time complexity?

-- For this query, conceptually:

-- Without a useful index: approximately O(n) because the database may need to examine each row.

-- With an appropriate index, the database optimizer may reduce the amount of data it needs to examine.

-- For a basic LeetCode/interview answer, O(n) is the expected explanation.
