# Write your MySQL query statement below
WITH QueueWeight AS (
    SELECT
        person_name,
        turn,
        SUM(weight) OVER (ORDER BY turn) AS total_weight
    FROM Queue
)
SELECT person_name
FROM QueueWeight
WHERE total_weight <= 1000
ORDER BY turn DESC
LIMIT 1;







///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- 6. Interview mein kaise explain karein?
-- “I use a window function to calculate the cumulative weight of people in their boarding order. Then I filter out the people whose cumulative weight exceeds 1000 kilograms. Finally, I sort the remaining people by turn in descending order and use LIMIT 1 to return the last eligible person.”
-- 7. Important follow-up questions
-- Q1. SUM(weight) OVER (ORDER BY turn) kya karta hai?

-- Ye running total calculate karta hai. Har row ka total us row tak ke sabhi weights ka sum hota hai.

-- Q2. GROUP BY ki jagah window function kyun use kiya?

-- GROUP BY rows ko aggregate karke combine kar deta hai. Window function har person ki individual row ko preserve karte hue cumulative sum calculate karta hai.

-- Q3. ORDER BY turn DESC LIMIT 1 kyun use kiya?

-- Capacity ke andar fit hone wale people mein sabse bada turn last person ko identify karta hai.

-- Q4. Kya person_id se order kar sakte hain?

-- Nahi. Boarding order turn decide karta hai, person_id nahi.

-- Pattern to remember: Jab bhi question mein running total, cumulative sum, ya running balance ho, SUM() OVER (ORDER BY ...) ke baare mein socho.

-- ///////////////////////////////////////////////////////////////////////////////////////////////////////////

-- -- SELECT q1.person_name
-- -- FROM Queue q1
-- -- INNER JOIN Queue q2
-- --     ON q1.turn >= q2.turn
-- -- GROUP BY q1.turn
-- -- HAVING SUM(q2.weight) <= 1000
-- -- ORDER BY q1.turn DESC
-- -- LIMIT 1;


-- -- Interview mein: “I use a self join to match each person with everyone who boards before or at their turn. I group by the current person, calculate the cumulative weight using SUM(), filter with HAVING to enforce the bus capacity, and return the eligible person with the highest turn.”
