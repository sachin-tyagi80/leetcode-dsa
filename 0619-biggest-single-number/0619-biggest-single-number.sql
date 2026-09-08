# Write your MySQL query statement below
select MAX(num) as num from MyNumbers
where num IN (select num from MyNumbers Group by num Having count(num) = 1);


-- Interview Explanation 🎯

-- Interview me aap aise bol sakte ho:

-- "First, I group the numbers using GROUP BY and use HAVING COUNT(num) = 1 to find numbers that appear exactly once. Then I use MAX() on those single numbers to get the largest one. If no number appears exactly once, MAX() returns NULL."

-- Important Interview Question

-- Q: WHERE COUNT(num) = 1 kyun nahi likh sakte?

-- Because COUNT() is an aggregate function. Aggregate functions ko filter karne ke liye HAVING use hota hai.

-- ❌ Wrong:

-- WHERE COUNT(num) = 1

-- ✅ Correct:

-- HAVING COUNT(num) = 1
-- Follow-up Questions

-- 1. WHERE aur HAVING me difference?

-- WHERE → rows ko GROUP BY se pehle filter karta hai.
-- HAVING → groups ko GROUP BY ke baad filter karta hai.

-- 2. Agar koi single number nahi hai to kya hoga?

-- MAX(...)

-- NULL return karega, jo exactly required answer hai.

-- 3. COUNT(*) ki jagah COUNT(num) use kar sakte hain?

-- Haan, is problem me dono effectively same hain because num values are integers and LeetCode data me relevant rows count karna hai.

-- 4. Kya DISTINCT use karke problem solve ho jayegi?

-- Nahi. DISTINCT duplicate values hata deta hai, lekin hume ye pata karna hai ki number exactly ek baar aaya hai. Isliye GROUP BY + HAVING COUNT() = 1 better approach hai.

-- Pattern yaad rakho:

-- "appeared exactly once"
--         ↓
-- GROUP BY
--         ↓
-- HAVING COUNT(*) = 1
--         ↓
-- largest/smallest chahiye
--         ↓
-- MAX() / MIN()
