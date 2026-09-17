# Write your MySQL query statement below
SELECT
    contest_id,
    ROUND(
        COUNT(user_id) * 100.0 / (SELECT COUNT(*) FROM Users),
        2
    ) AS percentage
FROM Register
GROUP BY contest_id
ORDER BY percentage DESC, contest_id ASC;


-- 8. Interview Explanation
-- English

-- “I start with the Register table because it contains contest registrations. I join it with the Users table using user_id. Then I group the records by contest_id and count the registered users. I divide that count by the total number of users from the Users table, multiply by 100, and round it to two decimal places. Finally, I sort by percentage in descending order and contest_id in ascending order for ties.”

-- Hinglish

-- “Main Register table se start karunga because usme contest registration hai. Phir Users table ke saath user_id par INNER JOIN karunga. Uske baad contest_id ke according GROUP BY karke registered users count karunga. Is count ko total users se divide karke 100 se multiply karunga aur 2 decimal tak ROUND karunga. Finally percentage descending aur tie hone par contest_id ascending me sort karunga.”

-- 9. Interview Follow-up Questions
-- Q1. INNER JOIN kyu use kiya?

-- Answer:
-- Because we need matching users between Register and Users.

-- Q2. GROUP BY contest_id kyu?

-- Answer:
-- Har contest ka separate percentage calculate karna hai.

-- Q3. COUNT(user_id) kya count karega?

-- Answer:
-- Har contest me registered users ki number.

-- Q4. ROUND(..., 2) kya karta hai?

-- Answer:

-- ROUND(66.6666, 2)

-- gives:

-- 66.67
-- Q5. 100.0 kyu likha, 100 kyu nahi?

-- Answer:
-- 100.0 decimal calculation ko ensure karta hai, especially division me.

-- Q6. ORDER BY percentage DESC kyu?

-- Because highest percentage first chahiye.

-- Q7. Tie hone par kya hoga?
-- ORDER BY percentage DESC, contest_id ASC

-- Percentage same → smaller contest_id first.

-- Q8. Kya INNER JOIN actually necessary hai?

-- Interesting interview question.

-- Is particular LeetCode problem me Register.user_id logically Users.user_id ko refer karta hai, so the join doesn't add a new calculation by itself. A simpler accepted approach can calculate directly from Register and Users count:

-- SELECT
--     contest_id,
--     ROUND(COUNT(user_id) * 100.0 / (SELECT COUNT(*) FROM Users), 2) AS percentage
-- FROM Register
-- GROUP BY contest_id
-- ORDER BY percentage DESC, contest_id ASC;

-- Learning point: Question ka main focus percentage calculation hai; INNER JOIN samajhne ke liye JOIN version useful hai, but JOIN is not inherently required here.
