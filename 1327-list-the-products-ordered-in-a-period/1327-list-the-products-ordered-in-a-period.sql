# Write your MySQL query statement below
SELECT
    p.product_name,
    SUM(o.unit) AS unit
FROM Products p
JOIN Orders o
    ON p.product_id = o.product_id
WHERE o.order_date >= '2020-02-01'
  AND o.order_date < '2020-03-01'
GROUP BY
    p.product_id,
    p.product_name
HAVING SUM(o.unit) >= 100;

/////////////////////////////////////////////////////////////////////////////////////////
-- Interview Explanation

-- If the interviewer says:

-- "Explain your approach."

-- You can say:

-- “First, I join the Products and Orders tables using product_id because the product name is stored in Products while the order details are stored in Orders. Then I filter the orders to include only February 2020. After that, I group the records by product and calculate the total units using SUM(). Finally, I use HAVING to keep only those products whose total ordered units are at least 100.”

-- That's a strong and simple interview explanation.

-- 12. Interview Follow-Up Questions
-- Q1. Why did you use JOIN?

-- Because:

-- Products → product_name
-- Orders → unit, order_date

-- We need data from both tables.

-- Q2. Why did you use GROUP BY?

-- Because we need the total units for each product.

-- GROUP BY p.product_id, p.product_name

-- allows us to calculate:

-- SUM(o.unit)

-- for each product separately.

-- Q3. Why did you use SUM()?

-- Because a product can have multiple orders.

-- Example:

-- 60 + 70 = 130

-- We need the total number of units.

-- Q4. Why use HAVING instead of WHERE?

-- Because:

-- SUM(o.unit)

-- is an aggregate result.

-- HAVING filters groups after aggregation.

-- Q5. What is the difference between WHERE and HAVING?
-- WHERE	HAVING
-- Filters rows	Filters groups
-- Before GROUP BY	After GROUP BY
-- Usually used for normal columns	Commonly used with aggregate functions

-- Example:

-- WHERE order_date >= '2020-02-01'

-- and:

-- HAVING SUM(unit) >= 100
-- Q6. Why are you grouping by both product_id and product_name?

-- Because product_id uniquely identifies a product, and we are selecting product_name as well.

-- It also makes the query explicit and safe for SQL modes that require selected non-aggregated columns to appear in GROUP BY.

-- Q7. Can we use only product_name in GROUP BY?

-- Yes, in this problem:

-- GROUP BY p.product_name

-- would generally work because product names identify the products in the given schema, but grouping by the primary key plus the selected name is clearer:

-- GROUP BY p.product_id, p.product_name
-- Q8. Why not use COUNT()?

-- Because COUNT() counts rows/orders.

-- The problem asks for units ordered, not the number of orders.

-- Example:

-- Order 1 → 60 units
-- Order 2 → 70 units

-- COUNT() → 2

-- But we need:

-- SUM() → 130
-- Q9. Why not use BETWEEN?

-- We can:

-- WHERE o.order_date BETWEEN '2020-02-01' AND '2020-02-29'

-- But this is also good:

-- WHERE o.order_date >= '2020-02-01'
--   AND o.order_date < '2020-03-01'

-- The second pattern is especially useful when working with DATETIME values because it avoids problems with time components.

-- Q10. Why not use MONTH(order_date) = 2?

-- You could write:

-- WHERE MONTH(order_date) = 2
--   AND YEAR(order_date) = 2020

-- But the date-range approach is generally preferable:

-- WHERE order_date >= '2020-02-01'
--   AND order_date < '2020-03-01'

-- It clearly represents the date range and is generally more index-friendly.

-- 13. 🧠 Pattern to Remember

-- This problem belongs to a very common SQL pattern:

-- JOIN
--  ↓
-- WHERE (filter rows)
--  ↓
-- GROUP BY
--  ↓
-- SUM / COUNT / AVG
--  ↓
-- HAVING (filter groups)

-- For this problem:

-- Products + Orders
--        ↓
--      JOIN
--        ↓
-- February 2020
--        ↓
--    GROUP BY Product
--        ↓
--     SUM(unit)
--        ↓
--   >= 100
--        ↓
--     HAVING
-- Final query to remember:
-- SELECT
--     p.product_name,
--     SUM(o.unit) AS unit
-- FROM Products p
-- JOIN Orders o
--     ON p.product_id = o.product_id
-- WHERE o.order_date >= '2020-02-01'
--   AND o.order_date < '2020-03-01'
-- GROUP BY
--     p.product_id,
--     p.product_name
-- HAVING SUM(o.unit) >= 100;

-- One-line interview summary:

-- “JOIN the product and order data, filter February orders, GROUP BY product, SUM the units, and use HAVING to keep totals of at least 100.”


