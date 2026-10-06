Write your MySQL query statement below
Delete p1
from Person p1
join Person p2
    on p1.email = p2.email
    and p1.id>p2.id


///////////////////////////////////////////////////////////////////////////////////////
-- 🎤 Interview Explanation

-- You can say:

-- “I use a self join because I need to compare rows within the same Person table. I match rows having the same email, and then compare their IDs. If p1.id is greater than p2.id, it means p1 is a duplicate with a larger ID, so I delete p1. This automatically keeps the row with the smallest ID for each email.”

-- Short interview version:

-- “Same email and larger ID means duplicate, so I delete the larger ID.”

-- 🔥 Interview Follow-up Questions
-- 1. Why do you use SELF JOIN?

-- Because both the original row and duplicate row are stored in the same table.

-- We need to compare rows of Person with other rows of Person.

-- 2. Why use aliases p1 and p2?

-- Because we are using the same table twice.

-- Person p1
-- Person p2

-- The aliases allow us to distinguish the two copies.

-- 3. Why use DELETE p1?
-- DELETE p1

-- means:

-- Delete the rows represented by alias p1.

-- Since p1 represents the row with the larger ID, those duplicate rows are removed.

-- 4. Why not use DELETE p2?

-- Because p2 represents the smaller ID.

-- We want to keep the smallest ID.

-- So we delete p1, not p2.

-- 5. Why is p1.email = p2.email required?

-- Without it, we would compare unrelated emails.

-- We only want to compare rows that have the same email.

-- 6. What happens if there are three duplicate emails?

-- For example:

-- 1 → abc@gmail.com
-- 5 → abc@gmail.com
-- 8 → abc@gmail.com

-- The query identifies the larger IDs as duplicates:

-- 5 → DELETE
-- 8 → DELETE
-- 1 → KEEP

-- So only:

-- 1 → abc@gmail.com

-- remains.

-- 7. Can we solve this using MIN()?

-- Yes, another approach is to delete rows whose ID is not the minimum ID for that email:

-- DELETE FROM Person
-- WHERE id NOT IN (
--     SELECT min_id
--     FROM (
--         SELECT MIN(id) AS min_id
--         FROM Person
--         GROUP BY email
--     ) t
-- );

-- But the SELF JOIN solution is cleaner for this problem.

-- 8. Why can't we simply use DISTINCT?

-- Because:

-- SELECT DISTINCT email

-- can remove duplicate values from a result set, but this problem asks us to actually delete rows from the table.

-- We need a DELETE statement.

-- Also, we need to preserve the row with the smallest id.

-- 🧠 Pattern to Remember

-- This problem has a very useful pattern:

-- Same table
--     ↓
-- SELF JOIN
--     ↓
-- Same email
--     ↓
-- Compare IDs
--     ↓
-- Larger ID = duplicate
--     ↓
-- DELETE
-- Memorize this condition:
-- p1.email = p2.email
-- AND p1.id > p2.id
-- One-line memory trick:

-- “Same email → bigger ID → delete.”
