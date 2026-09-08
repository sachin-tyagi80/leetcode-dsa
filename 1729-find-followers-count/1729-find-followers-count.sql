# Write your MySQL query statement below
select user_id , COUNT(follower_id) AS followers_count
from Followers 
GROUP BY user_id
order by user_id;


-- 🎤 Interview Explanation

-- Interviewer ko aise explain karo:

-- "I need to calculate the number of followers for each user. Since multiple rows can belong to the same user, I group the table by user_id. Then I use COUNT(follower_id) to count how many followers each user has. Finally, I sort the result by user_id in ascending order."

-- Hinglish:

-- "Sir, har user_id ke liye followers count karne hain. Isliye main user_id par GROUP BY karunga. Har group mein follower_id ko COUNT() karunga. Finally ORDER BY user_id se ascending order mein result return karunga."

-- ❓ Interview Follow-up Questions
-- Q1. GROUP BY kyun use kiya?

-- Because hume each user ka separate count chahiye.

-- Without GROUP BY:

-- SELECT COUNT(follower_id)
-- FROM Followers;

-- Ye total followers dega:

-- 4

-- But hume chahiye:

-- 0 → 1
-- 1 → 1
-- 2 → 2
-- Q2. COUNT(*) use kar sakte hain?

-- Yes.

-- Because every row represents one follower relationship:

-- SELECT
--     user_id,
--     COUNT(*) AS followers_count
-- FROM Followers
-- GROUP BY user_id
-- ORDER BY user_id;

-- Is problem mein COUNT(*) bhi correct hai.

-- Q3. COUNT(user_id) use kar sakte hain?

-- Yes, is particular table mein kar sakte hain because user_id NULL nahi hai due to primary key.

-- COUNT(user_id)

-- But semantically:

-- COUNT(follower_id)

-- zyada clear hai because hum followers count kar rahe hain.

-- Q4. WHERE aur GROUP BY mein difference?

-- WHERE rows ko filter karta hai before grouping.

-- GROUP BY rows ko groups mein divide karta hai.

-- Example:

-- WHERE user_id > 1
-- GROUP BY user_id

-- Pehle users filter honge, phir groups banenge.

-- Q5. HAVING kya hota hai?

-- HAVING grouped result ko filter karta hai.

-- Example: sirf un users ko find karo jinke 2 se zyada followers hain:

-- SELECT
--     user_id,
--     COUNT(*) AS followers_count
-- FROM Followers
-- GROUP BY user_id
-- HAVING COUNT(*) > 2;
