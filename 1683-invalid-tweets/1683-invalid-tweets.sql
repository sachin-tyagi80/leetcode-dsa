# Write your MySQL query statement below
select tweet_id from Tweets
where LENGTH(content)>15;



-- 🎯 Interview Explanation

-- Agar interviewer bole "Explain your approach", bolo:

-- "I need to find tweets whose content length is greater than 15. So, I select the tweet_id from the Tweets table and use the LENGTH() function in the WHERE clause to filter tweets with content length greater than 15."

-- Short version:

-- "I use LENGTH() to calculate the tweet content length and filter the records where the length is greater than 15."

-- 🔥 Follow-up Questions
-- Q1. Why did you use LENGTH()?

-- LENGTH() is used to calculate the length of the tweet content.

-- Q2. Why do we use > instead of >=?

-- Question says:

-- strictly greater than 15

-- Therefore:

-- LENGTH(content) > 15

-- Correct.

-- If condition was 15 or more, then:

-- LENGTH(content) >= 15
-- Q3. What if tweet length is exactly 15?

-- It is valid, because invalid means strictly greater than 15.

-- 15 > 15 → false

-- So it won't appear in the result.

-- Q4. What is LENGTH() in SQL?

-- LENGTH() is a string function used to determine the length of a string.

-- Example:

-- SELECT LENGTH('Hello');

-- Result:

-- 5
-- Q5. Does this query need DISTINCT?

-- No.

-- tweet_id is the primary key, so every tweet has a unique ID.

-- SELECT tweet_id
-- FROM Tweets
-- WHERE LENGTH(content) > 15;

-- is enough.

-- Q6. Do we need ORDER BY?

-- No.

-- The question says:

-- Return the result table in any order.

-- So sorting isn't required.

-- 🧠 Important SQL Pattern

-- Is question se ye pattern yaad rakho:

-- SELECT
--    ↓
-- What do you want?

-- FROM
--    ↓
-- Which table?

-- WHERE
--    ↓
-- Which rows?
