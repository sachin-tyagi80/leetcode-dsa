# Write your MySQL query statement below
select * from cinema
where id % 2 = 1 AND description != 'boring'
order by rating DESC;


-- 🎯 Interview Explanation

-- Interviewer: "Explain your approach."

-- Aap bolo:

-- "First, I filter the movies whose IDs are odd using the modulo operator. Then I exclude movies whose description is 'boring'. Finally, I sort the remaining movies by rating in descending order."

-- Short answer:

-- "I use id % 2 = 1 to find odd IDs, description != 'boring' to exclude boring movies, and ORDER BY rating DESC to sort by highest rating first."

-- 🔥 Follow-up Questions
-- Q1. How do you check whether a number is odd in SQL?
-- id % 2 = 1

-- % returns the remainder. If remainder is 1 when divided by 2, the number is odd.

-- Q2. How do you check even number?
-- id % 2 = 0

-- Example:

-- 4 % 2 = 0

-- So 4 is even.

-- Q3. Why did you use AND instead of OR?

-- Because both conditions are required.

-- We need:

-- Odd ID
-- AND
-- Not boring

-- If we use OR, then a movie satisfying only one condition could be included incorrectly.

-- Q4. What is the difference between != and <>?

-- Both can mean not equal in MySQL.

-- description != 'boring'

-- and

-- description <> 'boring'

-- are equivalent for this purpose.

-- Q5. Why did you use DESC?

-- Because the question asks:

-- Order by rating in descending order.

-- So:

-- ORDER BY rating DESC

-- means:

-- Highest rating → Lowest rating
-- Q6. Can we use MOD() instead of %?

-- Yes.

-- WHERE MOD(id, 2) = 1
--   AND description != 'boring'

-- In MySQL, % is simpler and commonly used.

-- Q7. Do we need DISTINCT?

-- No.

-- id is the primary key, so each movie is already unique.

-- 🧠 Final Revision
-- SELECT *
-- FROM Cinema
-- WHERE id % 2 = 1
--   AND description != 'boring'
-- ORDER BY rating DESC;

-- Remember the pattern:

-- Odd ID → id % 2 = 1
-- Not boring → description != 'boring'
-- Both conditions → AND
-- Highest rating first → ORDER BY rating DESC
