# Write your MySQL query statement below
# Write your MySQL query statement below
select s.user_id,ROUND(IFNULL(SUM(c.action = 'confirmed')/ COUNT(c.action),0),2) AS confirmation_rate
FROM Signups s
LEFT JOIN Confirmations c
     ON s.user_id = c.user_id
GROUP BY s.user_id;


//////////////////////////////////////////////////////////////////
-- Interview Explanation
-- “I started with the Signups table and used a LEFT JOIN with the Confirmations table because every registered user must appear in the result, including users who never requested confirmation. I counted confirmed requests using SUM with a Boolean condition and divided that by the total number of requests using COUNT. I used IFNULL to handle users with no requests and ROUND to format the confirmation rate to two decimal places. Finally, I grouped the results by user ID.”
-- 8. Interview Follow-up Questions with Answers
-- Q1. Why do we use LEFT JOIN instead of INNER JOIN?
-- Answer: LEFT JOIN preserves every user from the Signups table. An INNER JOIN would exclude users who have no confirmation requests, such as user 6.
-- Q2. Why do we use IFNULL()?
-- Answer: If a user has no confirmation requests, the denominator is zero and the calculated rate can be NULL. IFNULL() replaces that result with 0.
-- Q3. What is the difference between COUNT(*) and COUNT(c.action) here?
-- Answer: After a LEFT JOIN, COUNT(*) counts the row for a user even if there is no matching confirmation. COUNT(c.action) ignores NULL values and correctly returns zero requests for that user.
-- Q4. How does SUM(c.action = 'confirmed') work?
-- Answer: MySQL evaluates the condition as 1 for a confirmed action and 0 for a timeout. Adding these values counts the confirmed requests.
-- Q5. Why do we use ROUND(..., 2)?
-- Answer: The problem requires the confirmation rate to be rounded to two decimal places. The second argument specifies two digits after the decimal point.
-- Q6. What happens if every request times out?
-- Answer: The number of confirmed requests is zero, so the confirmation rate is 0.00.
-- Q7. What happens if all requests are confirmed?
-- Answer: The number of confirmed requests equals the total number of requests, so the rate is 1.00.
-- Q8. Why do we use GROUP BY s.user_id?
-- Answer: Each user can have multiple confirmation records. Grouping by user ID allows us to calculate one confirmation rate per user.
-- Q9. Can we use CASE WHEN instead of the Boolean condition?
-- Answer: Yes. For example:
-- SUM(
--     CASE
--         WHEN c.action = 'confirmed' THEN 1
--         ELSE 0
--     END
-- )


-- This explicitly counts confirmed requests and works as an alternative to SUM(c.action = 'confirmed').
-- Q10. What is the time complexity of this query?
-- Answer: Conceptually, the query processes the signup and confirmation records and aggregates them by user. Exact performance depends on the database execution plan and indexes, particularly the index on Confirmations.user_id.
-- 9. Pattern to Remember
-- LEFT JOIN → GROUP BY → SUM(CASE/condition) → COUNT() → IFNULL() → ROUND()
-- Use this pattern for SQL problems involving rates, percentages, success ratios, and users who may have no matching records.
-- Most important interview concept: Use LEFT JOIN when the output must include all records from the main table, even when no matching record exists in the second table.
