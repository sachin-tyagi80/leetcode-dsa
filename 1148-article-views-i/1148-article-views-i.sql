# Write your MySQL query statement below
select distinct author_id AS id
from Views
where author_id = viewer_id
order by id ASC;



-- 🎯 Interview mein complete answer

-- Interviewer: "Explain your approach."

-- Aap bol sakte ho:

-- "First, I filter the rows where author_id is equal to viewer_id, because that means the author viewed their own article. Then I select the author_id and use DISTINCT to remove duplicate authors. Finally, I sort the result by ID in ascending order."

-- Ye answer simple aur interview-friendly hai.

-- 🔥 Follow-up Questions
-- Q1. Why do we use DISTINCT?

-- Answer:

-- Because the same author can view their article multiple times. DISTINCT ensures that each author appears only once.

-- Example:

-- 4
-- 4
-- 4
-- 7

-- After DISTINCT:

-- 4
-- 7
-- Q2. Can we solve this without DISTINCT?

-- Yes, GROUP BY se:

-- SELECT author_id AS id
-- FROM Views
-- WHERE author_id = viewer_id
-- GROUP BY author_id
-- ORDER BY id ASC;

-- Interview explanation:

-- GROUP BY also removes duplicate author IDs here, but DISTINCT is simpler because we only need unique values and no aggregation.

-- Q3. Why don't we use GROUP BY here?

-- "There is no aggregation required in this problem. We only need unique author IDs, so DISTINCT is more appropriate."

-- Q4. Why is author_id = viewer_id the main condition?

-- "Because equal author_id and viewer_id mean that the author and viewer are the same person. Therefore, the author viewed their own article."

-- Q5. What if the same author views the same article twice?

-- Example:

-- author_id = 4, viewer_id = 4
-- author_id = 4, viewer_id = 4

-- Both rows satisfy the condition, but DISTINCT makes sure that 4 appears only once.

-- Q6. Why do we use AS id?
-- author_id AS id

-- AS creates an alias.

-- Hindi: author_id ka output name change karke id kar diya.

-- Q7. Is ORDER BY necessary?

-- Yes.

-- Because the problem specifically says:

-- Return the result sorted by id in ascending order.

-- 🧠 Is question se 4 concepts yaad rakho
-- WHERE
--  ↓
-- Rows filter

-- author_id = viewer_id
--  ↓
-- Author viewed own article

-- DISTINCT
--  ↓
-- Duplicate IDs remove

-- ORDER BY
--  ↓
-- Ascending order
