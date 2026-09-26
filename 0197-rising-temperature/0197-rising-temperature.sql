# Write your MySQL query statement below
select t.id 
from Weather t
join Weather y
   ON DATEDIFF(t.recordDate,y.recordDate) = 1
WHERE t.temperature > y.temperature;



///////////////////////////////////////////////////////////////
-- How to explain it in an interview

-- "I use a self join because I need to compare each day's temperature with the previous day's temperature. I use two aliases for the same Weather table: today represents the current day and yesterday represents the previous day. The DATEDIFF condition ensures that the two dates are exactly one day apart. Finally, I compare the temperatures and return the ID of the day where today's temperature is higher."

-- Even shorter version

-- "This is a self-join problem. I join the Weather table with itself, match rows whose dates differ by exactly one day, and then check whether today's temperature is greater than yesterday's."

-- Line-by-line interview explanation
-- 1. FROM Weather today
-- FROM Weather today

-- "I use today as an alias to represent the current day's record."

-- 2. JOIN Weather yesterday
-- JOIN Weather yesterday

-- "I join the same table again using another alias, yesterday. This is called a self join."

-- 3. DATEDIFF
-- ON DATEDIFF(today.recordDate, yesterday.recordDate) = 1

-- "I make sure that the yesterday record is exactly one day before the today record."

-- For example:

-- today      = 2015-01-02
-- yesterday  = 2015-01-01

-- DATEDIFF(today, yesterday)
-- = 1
-- 4. Temperature comparison
-- WHERE today.temperature > yesterday.temperature

-- "After finding the previous day's record, I compare the two temperatures and keep only the days where today's temperature is higher."

-- 5. Return ID
-- SELECT today.id

-- "Finally, I return the ID of the current day."

-- Important Interview Follow-up Questions
-- Q1. Why did you use a SELF JOIN?

-- Answer:

-- "Because today's and yesterday's records are stored in the same Weather table. I need two references to the same table to compare two different rows."

-- Q2. Why did you use DATEDIFF()?

-- Answer:

-- "DATEDIFF() calculates the difference between two dates. I check whether the difference is exactly 1, which means the records are consecutive days."

-- Q3. Why not compare id values?

-- For example:

-- today.id = yesterday.id + 1

-- Answer:

-- "Because id represents a unique identifier, not necessarily consecutive dates. The problem specifically asks us to compare with yesterday, so I should compare recordDate, not id."

-- Q4. What does DATEDIFF() return?
-- DATEDIFF(date1, date2)

-- returns the number of days between the two dates.

-- Example:

-- DATEDIFF('2015-01-05', '2015-01-03')
-- = 2

-- And:

-- DATEDIFF('2015-01-02', '2015-01-01')
-- = 1
-- Q5. Why is today the first argument?

-- Because we want:

-- today - yesterday = 1

-- So:

-- DATEDIFF(today.recordDate, yesterday.recordDate) = 1

-- If we reverse them:

-- DATEDIFF(yesterday.recordDate, today.recordDate) = 1

-- we would get -1, so it wouldn't work.

-- Q6. What happens if yesterday's record is missing?

-- With this INNER JOIN, the current day won't have a matching row, so it won't be returned.

-- That's appropriate because we cannot determine whether the temperature increased compared with yesterday if yesterday's record doesn't exist.

-- Q7. Why didn't you use LEFT JOIN?

-- Answer:

-- "We only need days that have a previous-day record to compare against. Therefore, an INNER JOIN is sufficient."

-- A LEFT JOIN could also be written, but we'd need additional handling for the missing previous-day row.

-- Q8. Can you solve this using DATE_SUB()?

-- Yes:

-- SELECT today.id
-- FROM Weather today
-- JOIN Weather yesterday
--     ON yesterday.recordDate =
--        DATE_SUB(today.recordDate, INTERVAL 1 DAY)
-- WHERE today.temperature > yesterday.temperature;

-- Interview answer:

-- "DATE_SUB() directly finds the date one day before the current date, while DATEDIFF() checks whether the two dates are exactly one day apart."

-- Q9. What is a SELF JOIN?

-- "A self join is when a table is joined with itself. We use different aliases to treat the same table as two separate references."

-- Example:

-- FROM Weather today
-- JOIN Weather yesterday

-- Same table, two aliases.

-- Q10. Can we solve this using a window function?

-- Yes. For example, LAG() can access the previous row:

-- SELECT id
-- FROM (
--     SELECT
--         id,
--         temperature,
--         recordDate,
--         LAG(temperature) OVER (ORDER BY recordDate) AS previous_temp
--     FROM Weather
-- ) t
-- WHERE temperature > previous_temp;

-- Here:

-- LAG(temperature)
--         ↓
-- gets previous row's temperature
