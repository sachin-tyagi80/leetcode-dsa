# Write your MySQL query statement below
select eu.unique_id,e.name
from Employees e 
LEFT JOIN EmployeeUNI eu
ON e.id = eu.id


/////////////////////////////////////////////////
-- 10. Interview Explanation
-- English

-- “I use a LEFT JOIN because every employee must appear in the result, even if they don't have a unique ID. I join Employees with EmployeeUNI using the id column. If a matching record exists, unique_id is returned; otherwise SQL returns NULL.”

-- Hinglish

-- “Main LEFT JOIN use karunga because hume har employee ko output me dikhana hai. Employees ko EmployeeUNI ke saath id par join karunga. Agar matching unique ID milti hai to wo show hogi, otherwise right table se value nahi milegi aur NULL show hoga.”

-- 11. Interview Follow-up Questions
-- Q1. LEFT JOIN kyu use kiya?

-- Because all Employees must be retained.

-- Q2. INNER JOIN use karoge to kya hoga?

-- Jinke paas unique ID nahi hai, wo output se remove ho jayenge.

-- Q3. NULL automatically kahan se aaya?

-- LEFT JOIN me jab right table me matching row nahi milti, right-side columns NULL ho jaate hain.

-- Q4. ON e.id = u.id kya karta hai?

-- Dono tables ke related records ko match karta hai.

-- Q5. Kya WHERE u.unique_id IS NULL lagana chahiye?

-- No.

-- Question me hume both matching and non-matching employees chahiye. Isliye WHERE ki zarurat nahi hai.

-- 12. 🧠 Pattern Yaad Rakho

-- Ye pattern bahut important hai:

-- ALL records from A
-- +
-- Matching records from B
-- +
-- No match → NULL

-- SQL:

-- SELECT ...
-- FROM A
-- LEFT JOIN B
--     ON A.id = B.id;
-- 🔥 Shortcut

-- "Left table ke saare records chahiye?" → LEFT JOIN

-- "Sirf matching records chahiye?" → INNER JOIN

-- Is question me Employees ke saare records chahiye, isliye LEFT JOIN.
