# Write your MySQL query statement below
# Write your MySQL query statement below
select visited_on,(
    select sum(amount)
    from customer
    where visited_on between date_sub(c.visited_on, INTERVAL 6 DAY) and c.visited_on
) as amount,
ROUND(
    (
        select sum(amount) / 7
        from customer
        where visited_on between date_sub(c.visited_on,INTERVAL 6 DAY) and c.visited_on
    ),
    2
) as average_amount
from customer c
where visited_on >= (
    select date_add(min(visited_on),INTERVAL 6 DAY)
    FROM customer
)

GROUP BY visited_on;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- Interviewer ko English mein kaise explain karein?
-- Interview mein ye script bol sakte ho:
-- “I solve this problem using correlated subqueries.

-- First, I select each distinct visit date from the Customer table. For each date, I calculate the total revenue over a seven-day window, including the current date and the previous six days.

-- I use DATE_SUB(c.visited_on, INTERVAL 6 DAY) to determine the starting date of the window, and BETWEEN to include both the starting date and the current date.

-- I calculate the seven-day total using SUM(amount). For the moving average, I divide this total by seven and round the result to two decimal places.

-- Finally, I filter out dates that occur before the first complete seven-day window. I use DATE_ADD(MIN(visited_on), INTERVAL 6 DAY) to identify the earliest date on which a complete seven-day calculation is possible, and return the results in ascending date order.”

-- 2. Tumhare code ke important parts
-- A. Seven-day total
-- SELECT SUM(amount)
-- FROM customer
-- WHERE visited_on BETWEEN
--     DATE_SUB(c.visited_on, INTERVAL 6 DAY)
--     AND c.visited_on


-- Maan lo current date 2019-01-07 hai.
-- - DATE_SUB(..., INTERVAL 6 DAY) → 2019-01-01
-- - BETWEEN → January 1 se January 7 tak
-- - SUM(amount) → in seven days ka total, yani 860
-- B. Seven-day average
-- ROUND(
--     (
--         SELECT SUM(amount) / 7
--         FROM customer
--         WHERE visited_on BETWEEN
--             DATE_SUB(c.visited_on, INTERVAL 6 DAY)
--             AND c.visited_on
--     ),
--     2
-- ) AS average_amount


-- Seven-day total 860 hai, isliye average \(860/7 = 122.86\) hoga.
-- C. First valid date
-- WHERE visited_on >= (
--     SELECT DATE_ADD(MIN(visited_on), INTERVAL 6 DAY)
--     FROM customer
-- )


-- Agar earliest date January 1 hai, toh first valid date January 7 hogi. January 1–6 ke liye complete seven-day history available nahi hai.
-- D. GROUP BY visited_on
-- Ek date par multiple customers ho sakte hain, lekin output mein har date sirf ek baar chahiye. GROUP BY visited_on date ko unique output row mein laata hai.

-- Important improvement: Is query mein correlated subqueries original customer table par run hoti hain, jahan ek date par multiple rows ho sakti hain. Isko aur clear banane ke liye pehle daily revenue aggregate karke phir seven-day totals nikal sakte ho.
-- 3. Most important interview follow-up questions
-- 1. What is a correlated subquery?A correlated subquery references a column from the outer query. Here, the inner query uses c.visited_on from the outer query to calculate the seven-day window for each date.
-- 2. Why do we use DATE_SUB()?DATE_SUB() subtracts six days from the current date to find the start of the seven-day window.
-- 3. Why do we use BETWEEN?BETWEEN includes both boundaries. It includes the date six days before the current date and the current date itself.
-- 4. Why divide the sum by 7?
-- 5. Why do we use DATE_ADD(MIN(visited_on), INTERVAL 6 DAY)?It identifies the earliest date for which seven calendar days of data can be included, starting from the minimum visit date.
-- 6. Why use ROUND(..., 2)?
-- 7. What is the difference between WHERE and HAVING?
-- 8. Can this problem be solved using window functions?
-- 9. What is a potential performance issue with this solution?
-- 10. Why is daily aggregation important?
-- 11. Does ROWS BETWEEN 6 PRECEDING AND CURRENT ROW mean six days?
-- 12. Why is ORDER BY visited_on needed?
-- 4. One important technical correction
-- Tumhari query mein GROUP BY visited_on hai, lekin visited_on ko outer query mein select karne ke alawa correlated subqueries original transaction rows par calculate ho rahi hain. MySQL mein ye approach is particular problem par kaam kar sakti hai, lekin daily aggregation ke baad window functions wala solution zyada maintainable hai.

-- Interview mein sabse important concepts yaad rakho:
-- - Correlated subquery: inner query outer query ke current date par depend karti hai.
-- - Moving window: current date + previous six calendar days.
-- - Daily aggregation: har date ka revenue pehle combine karna.
-- - Complete window: pehle six days ka output nahi dena.
