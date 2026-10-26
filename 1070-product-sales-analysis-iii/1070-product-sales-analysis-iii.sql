# Write your MySQL query statement below

SELECT
    s.product_id,
    s.year AS first_year,
    s.quantity,
    s.price
FROM Sales s
JOIN (
    SELECT
        product_id,
        MIN(year) AS first_year
    FROM Sales
    GROUP BY product_id
) AS first_sales
    ON s.product_id = first_sales.product_id
   AND s.year = first_sales.first_year;


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

-- . Interviewer ko English mein kaise explain karein?
-- “The objective is to find all sales entries that occurred in the first year each product was sold.

-- First, I use a subquery with GROUP BY product_id and MIN(year) to identify the earliest sales year for every product.

-- Then, I join this result with the original Sales table using both product_id and year. This retrieves the original sales records from the first year, including their quantity and price.

-- I select the required columns and alias year as first_year in the final output.”

-- 5. Important interview follow-up questions
-- 1. Why do we use MIN(year)?MIN(year) returns the earliest year in which a product appears in the Sales table.
-- 2. Why do we use GROUP BY product_id?
-- 3. Why do we need a JOIN?The subquery returns only product_id and first_year. The join retrieves quantity and price from the original sales records.
-- 4. Why do we join on both product_id and year?
-- 5. What if a product has multiple sales in its first year?All such sales rows must be returned. Joining on product_id and first_year preserves every matching row.
-- 6. Can we solve this using a window function?Yes. MIN(year) OVER (PARTITION BY product_id) calculates each product's first year. Then filter rows where year equals that minimum.
-- 7. Why not use ROW_NUMBER()?ROW_NUMBER() assigns only one row rank 1 per product, so it can exclude other sales in the same first year. MIN() with a join or window function preserves all first-year sales.
-- 8. What is the difference between WHERE and HAVING?WHERE filters rows before grouping, while HAVING filters groups after aggregation.
-- 6. Alternative solution using a window function
-- MySQL 8.0+ mein ye approach bhi use kar sakte ho:

-- SELECT
--     product_id,
--     year AS first_year,
--     quantity,
--     price
-- FROM (
--     SELECT
--         product_id,
--         year,
--         quantity,
--         price,
--         MIN(year) OVER (
--             PARTITION BY product_id
--         ) AS earliest_year
--     FROM Sales
-- ) AS s
-- WHERE year = earliest_year;



-- Pattern to remember: Jab question kahe “first year for each product, but return all records from that year”, toh GROUP BY + MIN() + JOIN ek important SQL pattern hai. ROW_NUMBER() = 1 avoid karo agar first year ki multiple rows bhi return karni hain.
