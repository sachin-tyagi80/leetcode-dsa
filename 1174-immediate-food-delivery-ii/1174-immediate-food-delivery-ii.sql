select
Round(Sum(if(first_order_date = first_customer_pref_delivery_date,1,0)*100) / count(first_order_date),2) as immediate_percentage
from 
(select delivery_id,
 customer_id,
 min(order_date) as first_order_date,
 min(customer_pref_delivery_date) as first_customer_pref_delivery_date
 from delivery
 group by customer_id) as new_table


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- . Interviewer ko English mein kaise explain karein?
-- You can say:
-- “The goal of this problem is to calculate the percentage of customers whose first order was delivered immediately, meaning their order date is the same as their preferred delivery date.

-- First, I use a subquery with GROUP BY customer_id and MIN(order_date) to find the earliest order date for each customer.

-- Then, I join this result with the original Delivery table to retrieve the complete record of each customer's first order.

-- Next, I use the IF() function to assign 1 when the order date matches the preferred delivery date, and 0 otherwise.

-- I calculate the percentage by dividing the total immediate first orders by the total number of first orders, multiplying by 100, and using ROUND() to return the result to two decimal places.”

-- 3. Approach step by step
-- 1

-- Find each customer's first order

-- MIN(order_date) with GROUP BY customer_id finds the earliest order date for every customer.



-- 2

-- Retrieve the complete first-order row

-- JOIN matches the customer ID and first order date with the original table.



-- 3

-- Identify immediate orders

-- IF(order_date = customer_pref_delivery_date, 1, 0) marks immediate orders as 1 and scheduled orders as 0.



-- 4

-- Calculate the percentage

-- SUM() counts immediate first orders, COUNT(*) counts all first orders, and ROUND(..., 2) rounds the percentage to two decimal places.



-- 4. Interview follow-up questions with answers
-- 1. Why do we use MIN(order_date)?MIN(order_date) returns the earliest order date. Combined with GROUP BY customer_id, it identifies the first order date for each customer.
-- 2. Why is GROUP BY customer_id required?It groups rows by customer so that MIN(order_date) is calculated separately for every customer, rather than for the whole table.
-- 3. Why do we use JOIN after the subquery?The subquery gives us each customer's first order date. JOIN retrieves the corresponding original row, including that order's customer_pref_delivery_date.
-- 4. Why is calculating MIN(customer_pref_delivery_date) separately incorrect?The minimum preferred date may belong to a different order. We must compare the preferred date from the actual first-order row.
-- 5. What does IF(condition, 1, 0) do?It returns 1 when the condition is true and 0 when it is false. Here, it identifies immediate first orders.
-- 6. Why do we use SUM() instead of COUNT() to count immediate orders?SUM() adds the 1s and 0s produced by IF(). COUNT() would count both 1 and 0 because both are non-NULL values.
-- 7. Why do we multiply by 100?The ratio gives a fraction. Multiplying by 100 converts it into a percentage.
-- 8. Why use ROUND(..., 2)?It rounds the calculated percentage to two decimal places, as required by the problem.
-- 9. Can we solve this using a window function?Yes. ROW_NUMBER() OVER (PARTITION BY customer_id ORDER BY order_date) assigns rank 1 to each customer's earliest order. We then filter for rn = 1 and calculate the percentage.
-- 10. What is the difference between WHERE and HAVING?WHERE filters individual rows before grouping. HAVING filters grouped results after GROUP BY.
-- 11. What if a customer has multiple orders on the same earliest date?A join on customer_id and order_date can return multiple rows for that customer. If ties are possible, use a deterministic tie-breaker, such as ordering by order_date and delivery_id with ROW_NUMBER(), according to the problem's rules.
-- 12. What is the time complexity?The exact execution cost depends on indexes and the query plan. Without useful indexes, the aggregation and join can require scans and may approach O(n) work for the aggregation plus additional join cost; avoid claiming a universal exact complexity for SQL queries.
-- 5. Most important concept to remember
-- The query follows this pattern:

-- Group to find the first date → Join to retrieve the first-order row → Use conditional aggregation → Calculate the percentage.

-- Interview mein sabse important question ho sakta hai: “Why can't you simply use MIN(customer_pref_delivery_date)?” Answer: because the minimum preferred date does not necessarily belong to the customer's first order.
