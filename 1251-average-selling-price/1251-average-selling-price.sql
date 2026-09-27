# Write your MySQL query statement below
select p.product_id,
ROUND(IFNULL(SUM(p.price*u.units)/SUM(u.units),0),2) AS average_price
FROM Prices p
LEFT JOIN UnitsSold u
  ON p.product_id = u.product_id
  AND u.purchase_date BETWEEN p.start_date AND p.end_date
GROUP BY p.product_id;



-- 🎤 Interview Explanation

-- If the interviewer asks “Explain your approach for this problem”, you can say:

-- “First, I join the Prices and UnitsSold tables using product_id. I also check that the purchase date falls between the start_date and end_date of the price period.

-- I use a LEFT JOIN because every product should appear in the result, even if it has no sales.

-- Then I calculate the total revenue using price * units and divide it by the total units sold to get the weighted average selling price.

-- I use IFNULL() to return 0 when a product has no sold units, and ROUND() to round the result to two decimal places. Finally, I use GROUP BY product_id to get one result for each product.”

-- Short version to memorize

-- “LEFT JOIN → match product and date range → calculate total revenue → divide by total units → IFNULL for no sales → ROUND to 2 decimals → GROUP BY product.”

-- 🔥 Interview Follow-up Questions
-- 1. Why did you use LEFT JOIN?

-- Because all products must appear, including products that have no sales.

-- If we use INNER JOIN, products without sales will be removed.

-- 2. Why not use AVG(price)?

-- Because this is a weighted average.

-- For example:

-- 100 units × ₹5
-- 15 units × ₹20

-- ₹5 should have more weight because 100 units were sold at that price.

-- So we use:

-- SUM(price * units) / SUM(units)
-- 3. Why did you use IFNULL()?

-- To handle products with no sales.

-- IFNULL(calculation, 0)

-- means:

-- If the calculation is NULL, return 0.

-- 4. What happens if we use INNER JOIN?

-- Products that have no matching record in UnitsSold will disappear.

-- That's incorrect because the question requires them with average price 0.

-- 5. Why do we need the date condition?
-- u.purchase_date BETWEEN p.start_date AND p.end_date

-- Because a product can have different prices during different periods.

-- We need to find which price was active when the product was purchased.

-- 6. Why is the date condition inside ON?
-- LEFT JOIN UnitsSold u
-- ON p.product_id = u.product_id
-- AND u.purchase_date BETWEEN p.start_date AND p.end_date

-- Because we want to keep products even when there is no matching sale.

-- If we put the condition in WHERE, the LEFT JOIN behavior can effectively become an inner join for that condition.

-- 7. Why use GROUP BY product_id?

-- Because we need one answer for each product.

-- GROUP BY p.product_id

-- allows SUM() to calculate revenue and units separately for each product.

-- 8. Why SUM(price * units)?

-- Because:

-- price × units = revenue

-- For example:

-- ₹5 × 100 = ₹500
-- ₹20 × 15 = ₹300

-- Total revenue:

-- ₹800
-- 9. What is the formula for average selling price?
-- Average Selling Price
-- = Total Revenue / Total Units Sold

-- SQL:

-- SUM(price * units) / SUM(units)
-- 10. Why ROUND(..., 2)?

-- The question requires the result to have 2 decimal places.

-- ROUND(value, 2)

-- Example:

-- 6.9565 → 6.96
-- 11. What is the difference between IFNULL() and COALESCE()?

-- IFNULL() in MySQL takes two arguments:

-- IFNULL(value, 0)

-- COALESCE() can check multiple values:

-- COALESCE(value1, value2, value3, 0)

-- For this problem, either works.

-- 12. Can UnitsSold contain duplicate rows?

-- Yes.

-- The problem explicitly says:

-- This table may contain duplicate rows.

-- We should not use DISTINCT because duplicate rows represent units sold and must be included in the calculation.

-- 🧠 Most Important Pattern

-- Remember this:

-- Date-based price
--        ↓
-- LEFT JOIN
--        ↓
-- Product + Date condition
--        ↓
-- SUM(price × units)
--        ↓
-- ÷ SUM(units)
--        ↓
-- IFNULL(..., 0)
--        ↓
-- ROUND(..., 2)
--        ↓
-- GROUP BY product

-- Interview keywords: LEFT JOIN, BETWEEN, SUM(), GROUP BY, IFNULL(), ROUND(), weighted average.
