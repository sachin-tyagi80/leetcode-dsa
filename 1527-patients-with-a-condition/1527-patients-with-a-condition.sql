# Write your MySQL query statement below
SELECT patient_id,patient_name,conditions FROM Patients
WHERE conditions LIKE 'DIAB1%' or conditions LIKE '% DIAB1%';



-- 8. Interview Explanation 🎤
-- English

-- "I use the LIKE operator to find patients whose condition code starts with DIAB1. Since the conditions are space-separated, I check two cases: DIAB1 at the beginning of the string or DIAB1 immediately after a space."

-- Hindi/Hinglish

-- "Main LIKE operator use karke DIAB1 condition ko search kar raha hoon. Kyunki conditions space-separated hain, main do cases check karta hoon: DIAB1 starting mein ho ya kisi condition ke baad space ke through start ho."

-- 9. Interview Follow-up Questions
-- Q1. % ka kya meaning hai?

-- % means zero or more characters.

-- LIKE 'DIAB1%'

-- Matches:

-- DIAB1
-- DIAB10
-- DIAB100
-- DIAB12345
-- Q2. _ ka kya meaning hai?

-- _ means exactly one character.

-- Example:

-- LIKE 'DIAB1_'

-- DIAB10 ko match karega.

-- Q3. LIKE '%DIAB1%' kyun nahi use kiya?

-- Because that can match DIAB1 anywhere inside a code.

-- Question mein DIAB1 ko condition code ke beginning mein hona chahiye.

-- Isliye hum boundary check karte hain:

-- 'DIAB1%'

-- or

-- '% DIAB1%'
-- 🧠 10. Pattern Yaad Rakho
-- String starts with something:
-- column LIKE 'ABC%'
-- Space ke baad word starts ho:
-- column LIKE '% ABC%'
-- Dono cases:
-- WHERE column LIKE 'ABC%'
--    OR column LIKE '% ABC%'
-- Is problem ka main concept:
-- LIKE
--  ↓
-- %
--  ↓
-- Space boundary
--  ↓
-- OR

-- One-line memory trick:

"Starting mein ho → ABC%, beech mein separate word ho → % ABC%."
