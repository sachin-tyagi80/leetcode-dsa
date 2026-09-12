# Write your MySQL query statement below
select activity_date AS day, COUNT(DISTINCT user_id) AS active_users
from Activity
where activity_date BETWEEN '2019-06-28' AND '2019-07-27'
GROUP BY activity_date;


-- 7. Interview mein kaise explain karna hai?

-- Aap interviewer ko directly bol sakte ho:

-- "We need the number of unique active users for each day within the last 30 days. Since a user can perform multiple activities on the same day, I use COUNT(DISTINCT user_id) to avoid counting the same user multiple times. I filter the required date range using WHERE and then group the records by activity_date."

-- Short version:

-- "GROUP BY activity_date gives me one group per day, and COUNT(DISTINCT user_id) gives the number of unique active users for that day."

-- 8. Important Interview Follow-up Questions
-- Q1. Why DISTINCT?

-- Because one user can have multiple activities on the same day.

-- COUNT(DISTINCT user_id)

-- ensures one user is counted only once per day.

-- Q2. Why GROUP BY activity_date?

-- Because we need daily active user count.

-- GROUP BY activity_date

-- creates one group for each date.

-- Q3. Do we need to check activity_type?

-- No.

-- Problem clearly says all four activities are valid:

-- open_session
-- end_session
-- scroll_down
-- send_message

-- So we don't need:

-- WHERE activity_type = ...
-- Q4. What if a user performs 10 activities on one day?

-- Still count:

-- 1

-- because of:

-- COUNT(DISTINCT user_id)
-- Q5. What happens for days having zero active users?

-- They won't appear.

-- Why?

-- Because we're grouping only the dates that actually exist in Activity.

-- Q6. Why not use COUNT(*)?

-- COUNT(*) counts rows, not unique users.

-- Multiple activities = multiple rows.

-- So:

-- COUNT(*)

-- would give wrong result for this problem.

-- ⭐ Interview Pattern to Remember

-- Is type ke questions mein ye pattern yaad rakho:

-- SELECT
--     date_column,
--     COUNT(DISTINCT user_id)
-- FROM table
-- WHERE date_column BETWEEN start_date AND end_date
-- GROUP BY date_column;

-- Core concept:
-- GROUP BY → per day
-- DISTINCT user_id → unique users
-- COUNT → number of users
