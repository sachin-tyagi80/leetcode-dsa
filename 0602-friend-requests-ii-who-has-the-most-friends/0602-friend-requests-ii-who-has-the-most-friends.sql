# Write your MySQL query statement below
Select id, Count(*) as num
FROM(
    select requester_id as id
    from RequestAccepted

    UNION ALL 

    select accepter_id AS id
    FROM RequestAccepted
) as friends

GROUP BY id
ORDER BY num DESC
LIMIT 1;


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- Interview mein English mein kaise explain karein?
-- “I combine the requester IDs and accepter IDs using UNION ALL because each accepted friendship contributes one friend to each person. Then I group the combined IDs and count their occurrences to calculate each person's total friends. Finally, I sort the counts in descending order and use LIMIT 1 to return the person with the most friends.”

-- Important: UNION ki jagah UNION ALL use karna, kyunki same ID multiple accepted friendships mein aayegi aur har occurrence friend count mein contribute karti hai. UNION duplicates remove karke count galat kar sakta hai.


-- LeetCode 602 — Interview Follow-up Questions with Answers
-- Is problem se interviewer SQL ke UNION ALL, GROUP BY, COUNT, ORDER BY, LIMIT, CTE aur subqueries par questions pooch sakta hai.
-- 1. Basic SQL follow-up questions
-- Q1. Why do we use UNION ALL instead of UNION?

-- Answer: UNION ALL keeps all rows, including duplicate IDs. A person can appear multiple times because they have multiple friends. UNION removes duplicate rows, which would cause an incorrect friend count.


-- Q2. Why do we select both requester_id and accepter_id?

-- Answer: Because an accepted friendship connects two people. The requester is a friend of the accepter, and the accepter is a friend of the requester. We must count both sides.


-- Q3. Why do we use GROUP BY id?

-- Answer: GROUP BY id groups all occurrences of the same person together so we can calculate their total number of friends.


-- Q4. What does COUNT(*) do?

-- Answer: It counts the rows in each group. After combining both ID columns, the number of occurrences of an ID represents that person's number of friends.


-- Q5. Why do we use ORDER BY num DESC LIMIT 1?

-- Answer: ORDER BY num DESC sorts people from the highest friend count to the lowest. LIMIT 1 returns only the person with the highest count.


-- Q6. What is the purpose of AS id?

-- Answer: It gives both selected columns the same alias, id, so the combined result has one consistent column name.


-- 2. Intermediate-level follow-ups
-- Q7. What if multiple people have the same maximum number of friends?

-- Answer: LIMIT 1 returns only one row, so it does not guarantee that all tied people will be returned. We can calculate each person's friend count and filter using the maximum count.
-- WITH friends AS (
--     SELECT requester_id AS id
--     FROM RequestAccepted
--     UNION ALL
--     SELECT accepter_id AS id
--     FROM RequestAccepted
-- ),
-- friend_counts AS (
--     SELECT id, COUNT(*) AS num
--     FROM friends
--     GROUP BY id
-- )
-- SELECT id, num
-- FROM friend_counts
-- WHERE num = (SELECT MAX(num) FROM friend_counts);




-- Q8. What is a CTE?

-- Answer: A Common Table Expression (CTE) is a named temporary result set defined using WITH. It makes complex queries easier to read and organize.


-- Q9. Can we solve this problem without UNION ALL?

-- Answer: Yes. We can use separate aggregations for requester and accepter IDs and combine their counts. However, UNION ALL is usually simpler because it converts both columns into one list before counting.
-- SELECT id, SUM(cnt) AS num
-- FROM (
--     SELECT requester_id AS id, COUNT(*) AS cnt
--     FROM RequestAccepted
--     GROUP BY requester_id

--     UNION ALL

--     SELECT accepter_id AS id, COUNT(*) AS cnt
--     FROM RequestAccepted
--     GROUP BY accepter_id
-- ) AS t
-- GROUP BY id
-- ORDER BY num DESC
-- LIMIT 1;




-- Q10. What is the difference between WHERE and HAVING?

-- Answer: WHERE filters individual rows before grouping. HAVING filters groups after aggregation. For example, HAVING COUNT(*) > 2 returns people whose friend count exceeds two.


-- Q11. What is the difference between COUNT(*) and COUNT(column)?

-- Answer: COUNT(*) counts rows, while COUNT(column) counts only rows where that column is not NULL. In this problem, both ID columns are non-null integers, so either can work on the combined id column.


-- 3. Advanced SQL follow-ups
-- Q12. What happens if RequestAccepted is empty?

-- Answer: The query returns no rows because there are no people to count. The problem does not require returning a person when no accepted friendships exist.


-- Q13. What is the time complexity of the recommended solution?

-- Answer: Combining the rows and counting IDs takes \(O(n)\) expected processing time, where \(n\) is the number of accepted requests. Sorting the grouped results can take \(O(k\log k)\), where \(k\) is the number of distinct people. Exact performance depends on the database execution plan.


-- Q14. Can RANK() or DENSE_RANK() solve the tie case?

-- Answer: Yes. We can calculate counts, rank people by num DESC, and return rows where the rank equals 1. Both functions preserve ties at the highest count.


-- Q15. Why is COUNT(DISTINCT id) not appropriate here?

-- Answer: Because after combining the two ID columns, we need to count how many times each person appears. COUNT(DISTINCT id) would count unique IDs rather than friendship occurrences and would not calculate each person's friend count correctly.


-- Q16. Does the primary key guarantee that every person has a unique ID in the entire combined list?

-- Answer: No. The composite primary key guarantees that a requester–accepter pair is unique. The same person can still appear in many rows as either a requester or an accepter.
