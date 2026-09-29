# Write your MySQL query statement below
select sell_date,COUNT(DISTINCT product) AS num_sold,
GROUP_CONCAT(
    DISTINCT product
    ORDER BY product
    SEPARATOR ','
    ) AS products
FROM Activities
GROUP BY sell_date
ORDER BY sell_date;


//////////////////////////////////////////////////////////////////////
-- 🎤 Interview Explanation

-- If the interviewer asks "Explain your approach", say:

-- "First, I group the Activities table by sell_date because I need one result for each date. Then I use COUNT(DISTINCT product) to count the number of unique products sold on that date. For the product names, I use GROUP_CONCAT with DISTINCT to remove duplicates, ORDER BY product to sort them lexicographically, and SEPARATOR comma to join the names. Finally, I order the result by sell_date."

-- Short version to memorize:

-- GROUP BY date → COUNT(DISTINCT product) → GROUP_CONCAT(DISTINCT product ORDER BY product) → ORDER BY date

-- 🔥 Interview Follow-up Questions
-- Q1. Why COUNT(DISTINCT product) instead of COUNT(product)?

-- Because duplicate products can exist.

-- Mask
-- Mask

-- COUNT(product) → 2

-- COUNT(DISTINCT product) → 1

-- The question asks for different products, so we use DISTINCT.

-- Q2. Why do we use GROUP BY sell_date?

-- Because we need a separate result for every date.

-- GROUP BY sell_date

-- creates one group per date.

-- Q3. What is GROUP_CONCAT()?

-- GROUP_CONCAT() combines multiple values from rows into one string.

-- Example:

-- Basketball
-- Headphone
-- T-Shirt

-- becomes:

-- Basketball,Headphone,T-Shirt
-- Q4. Why DISTINCT inside GROUP_CONCAT()?

-- To remove duplicate product names.

-- GROUP_CONCAT(DISTINCT product)
-- Q5. Why ORDER BY product inside GROUP_CONCAT()?

-- The question requires the product names to be sorted lexicographically.

-- GROUP_CONCAT(
--     DISTINCT product
--     ORDER BY product
-- )
-- Q6. What does lexicographical order mean?

-- It basically means dictionary/alphabetical order for strings.

-- Example:

-- Pencil
-- Bible

-- becomes:

-- Bible
-- Pencil
-- Q7. What does SEPARATOR ',' mean?

-- It tells GROUP_CONCAT() to separate values using a comma.

-- SEPARATOR ','

-- Result:

-- Bible,Pencil
-- Q8. Can we use ORDER BY sell_date?

-- Yes.

-- ORDER BY sell_date;

-- This sorts the final result by date.

-- Q9. What happens if we don't use GROUP BY?

-- We cannot get a separate result for each date.

-- The aggregate functions would work on the whole table instead of each date group.

-- Q10. What is the difference between these two?
-- COUNT(product)

-- vs.

-- COUNT(DISTINCT product)
-- Query	Meaning
-- COUNT(product)	Counts all non-NULL product rows
-- COUNT(DISTINCT product)	Counts unique non-NULL products
-- 🧠 Pattern to Remember

-- Whenever the question says:

-- "For each date/category, count unique items and combine their names into one string."

-- Think:

-- GROUP BY
--    ↓
-- COUNT(DISTINCT ...)
--    ↓
-- GROUP_CONCAT(DISTINCT ... ORDER BY ...)
-- Main pattern:
-- SELECT
--     category,
--     COUNT(DISTINCT item),
--     GROUP_CONCAT(
--         DISTINCT item
--         ORDER BY item
--         SEPARATOR ','
--     )
-- FROM table
-- GROUP BY category;

-- For this problem:

-- category = sell_date
-- item     = product

-- So the key concepts to remember are:

-- GROUP BY → COUNT(DISTINCT) → GROUP_CONCAT() → ORDER BY.
