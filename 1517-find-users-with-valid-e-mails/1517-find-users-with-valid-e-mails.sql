# Write your MySQL query statement below
SELECT user_id, name, mail
FROM Users
WHERE mail REGEXP '^[A-Za-z][A-Za-z0-9_.-]*@leetcode\\.com$'
AND mail LIKE BINARY '%@leetcode.com';

///////////////////////////////////////////////////////////////////////////////////////
-- "Explain your approach."

-- You can say:

-- “The problem requires us to find users whose email follows a specific format. I use REGEXP to validate the complete email structure. The first character must be a letter, followed by letters, digits, underscore, dot or hyphen, and the email must end with @leetcode.com. I also use LIKE BINARY to make the domain comparison case-sensitive, because the domain must be exactly lowercase. Finally, I return the user_id, name and mail.”

-- That's a good interview answer.

-- 7. Interview Follow-up Questions
-- Q1. Why did you use REGEXP?

-- Answer:

-- REGEXP is useful when we need to validate a specific pattern in a string. Here, the email has multiple formatting rules, so REGEXP is suitable.

-- Q2. What does ^ mean?
-- ^

-- means start of the string.

-- So:

-- ^[A-Za-z]

-- means the email must start with a letter.

-- Q3. What does $ mean?
-- $

-- means end of the string.

-- It ensures that nothing comes after:

-- @leetcode.com
-- Q4. What does * mean?
-- *

-- means zero or more occurrences.

-- For example:

-- [A-Za-z0-9_.-]*

-- means these characters can appear zero or more times.

-- Q5. Why did you separate the first character?

-- Because the first character has a special rule.

-- It must be a letter.

-- After that, digits, _, ., and - are also allowed.

-- That's why we use:

-- [A-Za-z]

-- followed by:

-- [A-Za-z0-9_.-]*
-- Q6. Why not use this?
-- [A-Za-z0-9_.-]+

-- Because that would allow the first character to be a digit, _, ., or -.

-- For example:

-- 1abc@leetcode.com

-- could become valid, which violates the requirement.

-- Q7. Why use LIKE BINARY?

-- Because we need a case-sensitive comparison for:

-- @leetcode.com

-- Without BINARY, MySQL collation may perform case-insensitive comparison.

-- Q8. What is the difference between LIKE and REGEXP?

-- LIKE is mainly used for simple pattern matching:

-- LIKE 'A%'
-- LIKE '%abc%'

-- REGEXP is used for more complex patterns:

-- REGEXP '^[A-Za-z][A-Za-z0-9_.-]*...'

-- For this problem, REGEXP is much more suitable.

-- Q9. What does % mean in LIKE?

-- In:

-- LIKE '%@leetcode.com'

-- % means zero or more characters before:

-- @leetcode.com

-- So it can match:

-- abc@leetcode.com
-- john123@leetcode.com
-- a_b-c@leetcode.com
-- Q10. Why do we use \\. instead of just .?

-- Because . has a special meaning in regular expressions.

-- A literal dot is represented as:

-- \.

-- And inside the MySQL string, we write:

-- \\.

-- to represent that escaped dot.

-- 8. Important Interview Point

-- The interviewer may ask:

-- "Can you solve it without REGEXP?"

-- Yes, but it becomes more complicated because we would need string functions such as:

-- LEFT()
-- INSTR()
-- SUBSTRING()
-- LIKE

-- For this particular problem, REGEXP is the cleanest approach.

-- 9. Your Pattern to Remember 🧠

-- For email-regex problems, remember:

-- START
--   ↓
-- FIRST CHARACTER MUST BE LETTER
--   ↓
-- ALLOWED CHARACTERS
--   ↓
-- EXACT DOMAIN
--   ↓
-- END

-- In regex:

-- ^
-- [A-Za-z]
-- [A-Za-z0-9_.-]*
-- @leetcode\.com
-- $

-- And because the domain must be lowercase in MySQL:

-- LIKE BINARY
-- Final code you can remember
-- SELECT user_id, name, mail
-- FROM Users
-- WHERE mail REGEXP '^[A-Za-z][A-Za-z0-9_.-]*@leetcode\\.com$'
--   AND mail LIKE BINARY '%@leetcode.com';

-- One-line interview summary:

-- “I use REGEXP to validate the email structure and LIKE BINARY to ensure that the required @leetcode.com domain is matched in lowercase.”
