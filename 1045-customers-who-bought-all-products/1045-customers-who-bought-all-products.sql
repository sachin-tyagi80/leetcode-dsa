# Write your MySQL query statement below
SELECT customer_id FROM Customer
GROUP BY customer_id
HAVING COUNT(DISTINCT product_key) = (SELECT COUNT(*) FROM Product);

-- 🎯 Interview mein explanation

-- Agar interviewer kahe "Explain your approach", bolo:

-- "I group the Customer table by customer_id and count the distinct products purchased by each customer. Then I compare that count with the total number of products in the Product table using a subquery. If both counts are equal, the customer has bought all products."

-- Hinglish:

-- "Main customer-wise grouping karta hoon aur har customer ke distinct products count karta hoon. Phir us count ko Product table ke total products count se compare karta hoon. Jiska count equal hai, us customer ne saare products buy kiye hain."

-- 🔥 Important: WHERE vs HAVING

-- Interviewer ye zaroor pooch sakta hai.

-- WHERE

-- Rows ko filter karta hai GROUP BY se pehle.

-- WHERE product_key = 5
-- HAVING

-- Groups ko filter karta hai GROUP BY ke baad.

-- HAVING COUNT(DISTINCT product_key) = ...

-- Yahan COUNT() aggregate function hai, isliye HAVING use kiya.

-- ⭐ Follow-up Questions
-- Q1. Why COUNT(DISTINCT product_key)?

-- Answer:

-- Because Customer table can contain duplicate rows. We need to count unique products purchased by each customer.

-- Q2. Why not use COUNT(product_key)?

-- Because duplicates ho sakte hain.

-- Example:

-- customer_id | product_key
-- 1           | 5
-- 1           | 5
-- 1           | 6

-- COUNT(product_key) → 3 ❌

-- COUNT(DISTINCT product_key) → 2 ✅

-- Q3. Why use HAVING instead of WHERE?

-- COUNT() is an aggregate function, so we use HAVING to filter the grouped result.

-- Q4. What does the subquery return?
-- SELECT COUNT(*)
-- FROM Product

-- Product table mein total number of products return karta hai.

-- Q5. Why GROUP BY customer_id?

-- Because humein each customer separately check karna hai.

-- Q6. What happens if Product table has 5 products?

-- Then a customer must have:

-- COUNT(DISTINCT product_key) = 5

-- Only then that customer qualifies.

-- Q7. What if customer buys the same product 10 times?

-- Still it counts as one unique product because of:

-- COUNT(DISTINCT product_key)
-- Q8. Can we solve it without a subquery?

-- Yes, one common approach is:

-- SELECT customer_id
-- FROM Customer
-- GROUP BY customer_id
-- HAVING COUNT(DISTINCT product_key) = (SELECT COUNT(*) FROM Product);

-- Actually this is already the cleanest solution for this problem. Another approach can use joins, but unnecessary complexity hai.
