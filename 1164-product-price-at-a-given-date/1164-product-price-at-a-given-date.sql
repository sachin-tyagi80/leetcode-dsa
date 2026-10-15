##Write your MySQL query statement below

SELECT
    p.product_id,
    COALESCE(latest.new_price, 10) AS price
FROM (
    SELECT DISTINCT product_id
    FROM Products
) p
LEFT JOIN (
    SELECT product_id, new_price
    FROM Products
    WHERE (product_id, change_date) IN (
        SELECT product_id, MAX(change_date)
        FROM Products
        WHERE change_date <= '2019-08-16'
        GROUP BY product_id
    )
) latest
    ON p.product_id = latest.product_id;


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- 7. Interview Explanation
-- You can explain your solution to an interviewer like this:
-- First, I retrieve all distinct product IDs because every product must appear in the output. Next, I find the maximum change date for each product that is less than or equal to August 16, 2019. I retrieve the price associated with that date and use a LEFT JOIN so that products without any eligible change are not excluded. Finally, I use COALESCE to assign the initial price of 10 when the price is NULL.

-- 8. Interview Follow-up Questions with Answers
-- Q1. Why do we use MAX(change_date)?
-- To find the latest price change on or before the target date for each product.
-- Q2. Why is the condition change_date <= '2019-08-16' necessary?
-- It excludes future price changes. A change on August 17 must not affect the price on August 16.
-- Q3. Why use LEFT JOIN instead of INNER JOIN?
-- LEFT JOIN preserves products with no eligible price change. An INNER JOIN would remove them, even though their price should be 10.
-- Q4. What is the difference between COALESCE() and IFNULL() in MySQL?
-- - COALESCE(a, b) returns the first non-NULL value and can accept multiple arguments.
-- - IFNULL(a, b) checks just one expression and returns b if a is NULL.
-- For this problem, either works:
-- COALESCE(latest.new_price, 10)


-- or
-- IFNULL(latest.new_price, 10)


-- Q5. Why do we use DISTINCT product_id?
-- A product can have many rows because its price may change multiple times. DISTINCT ensures that each product appears once in the initial product list.
-- Q6. Can we use MAX(new_price) instead of MAX(change_date)?
-- No. The highest price is not necessarily the latest price. We need to find the price associated with the latest eligible date, not the maximum price value.
-- Q7. What happens if a product has a change on August 16 itself?
-- That change is included because the condition uses <=, so the price from August 16 is selected.
-- Q8. Can we solve this using a window function?
-- Yes. ROW_NUMBER() can rank each product's eligible changes from newest to oldest. We then select the row ranked first and use the same default-price logic for products with no eligible changes.
-- 9. Pattern to Remember
-- This problem follows a common SQL pattern: latest record before a target date, with a default value.
-- Remember these steps:
-- 1. DISTINCT — get all entities that must appear.
-- 2. WHERE date <= target_date — exclude future records.
-- 3. GROUP BY entity + MAX(date) — find the latest eligible record.
-- 4. Join back to the original table to retrieve its associated value.
-- 5. LEFT JOIN + COALESCE() — preserve entities without an eligible record and assign a default.
-- Important interview tip: MAX(date) finds the latest date, but it does not automatically return the price from that date. You must match the selected date back to its corresponding row.

