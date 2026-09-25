# Write your MySQL query statement below
select 
  p.product_name,
  s.year,
  s.price
FROM Sales s 
INNER JOIN Product p
   ON s.product_id = p.product_id;


-- Interview Explanation
-- English

-- "The Sales table contains the product ID, year, and price, while the Product table contains the product name. I join the two tables using product_id and select the product name from Product and year and price from Sales."

-- Short Interview Answer

-- I use an INNER JOIN on product_id because the product name is stored in the Product table while the year and price are stored in the Sales table.

-- 10. Interview Follow-up Questions
-- Q1. Why do we need a JOIN?

-- Because product_name is in Product, while year and price are in Sales.

-- Q2. What is the joining column?
-- product_id
-- Q3. What is the relationship between the tables?
-- Product.product_id
--         ↑
--         |
-- Sales.product_id

-- Sales.product_id is a foreign key referencing Product.product_id.

-- Q4. What type of JOIN did we use?

-- INNER JOIN.

-- Q5. Why not CROSS JOIN?

-- Because we don't want every product combined with every sale. We only want matching product IDs.

-- Q6. Do we need GROUP BY?

-- No.

-- We are not calculating:

-- SUM
-- AVG
-- COUNT
-- MAX
-- MIN

-- We simply need matching records.

-- Q7. Do we need ORDER BY?

-- No. The question explicitly says:

-- Return the resulting table in any order.
