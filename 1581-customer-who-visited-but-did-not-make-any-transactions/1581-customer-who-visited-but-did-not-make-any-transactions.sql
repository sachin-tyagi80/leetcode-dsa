# Write your MySQL query statement below
select v.customer_id , COUNT(*) AS count_no_trans
from visits v 
left join transactions t
on t.visit_id = v.visit_id 

where t.transaction_id IS NULL
GROUP BY v.customer_id;



///////////////////////////////////////////////////////////////////////////
-- 7. Interview Explanation
-- English

-- "I use a LEFT JOIN because I want all visits, including visits that don't have a matching transaction. Then I filter the unmatched rows using WHERE t.visit_id IS NULL. Finally, I group the result by customer_id and count the number of visits without transactions."

-- Hinglish

-- "Main LEFT JOIN use karunga kyunki mujhe saari visits chahiye, including those visits jinke transactions nahi hain. LEFT JOIN ke baad jahan transaction nahi milegi, wahan right table ke columns NULL honge. Main WHERE t.visit_id IS NULL se un visits ko filter karunga aur phir customer-wise GROUP BY karke COUNT karunga."

-- 8. Interview Follow-up Questions
-- Q1. LEFT JOIN hi kyu use kiya?

-- Because hume Visits ki unmatched rows bhi chahiye.

-- LEFT JOIN → unmatched rows preserved
-- INNER JOIN → unmatched rows removed
-- Q2. INNER JOIN use karenge to kya hoga?
-- FROM Visits v
-- INNER JOIN Transactions t
-- ON v.visit_id = t.visit_id

-- Sirf transactions wali visits milengi.

-- No-transaction visits already remove ho jayengi, so answer nahi milega.

-- Q3. WHERE t.visit_id = NULL kyu nahi?

-- Wrong:

-- WHERE t.visit_id = NULL

-- Correct:

-- WHERE t.visit_id IS NULL

-- SQL me NULL unknown value ko represent karta hai.

-- Q4. GROUP BY customer_id kyu?

-- Because question customer-wise count maang raha hai.

-- Customer 54 → 2 visits
-- Customer 30 → 1 visit
-- Customer 96 → 1 visit
-- Q5. COUNT(*) kya count karega?

-- Filtering ke baad jo rows bachengi, unko count karega.

-- 54 → visit 7, visit 8 → 2 rows → COUNT(*) = 2
-- 🧠 Pattern Yaad Rakho

-- Ye pattern bahut important hai:

-- "A table ke records jinka B table me match nahi hai"

-- Use:

-- SELECT ...
-- FROM A
-- LEFT JOIN B
--     ON A.id = B.id
-- WHERE B.id IS NULL;
-- Shortcut:

-- LEFT JOIN + RIGHT TABLE IS NULL = Find records with NO MATCH

-- Is question me:

-- Visits = A
-- Transactions = B

-- So:

-- Visits
-- LEFT JOIN
-- Transactions
-- WHERE Transactions.visit_id IS NULL

-- Ye JOIN questions me ek classic interview pattern hai.
