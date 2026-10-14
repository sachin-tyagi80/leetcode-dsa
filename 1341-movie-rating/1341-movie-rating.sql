# Write your MySQL query statement below
(
    SELECT u.name AS results
    FROM Users u
    JOIN MovieRating mr
        ON u.user_id = mr.user_id
    GROUP BY u.user_id, u.name
    ORDER BY COUNT(mr.movie_id) DESC, u.name ASC
    LIMIT 1
)

UNION ALL

(
    SELECT m.title AS results
    FROM Movies m
    JOIN MovieRating mr
        ON m.movie_id = mr.movie_id
    WHERE mr.created_at >= '2020-02-01'
      AND mr.created_at < '2020-03-01'
    GROUP BY m.movie_id, m.title
    ORDER BY AVG(mr.rating) DESC, m.title ASC
    LIMIT 1
);



////////////////////////////////////////////////////////////////////////////////////////////////
-- 7. Interview Explanation
-- “I divided the problem into two queries. In the first query, I joined Users with MovieRating, grouped by user, and counted their ratings. I sorted by the count in descending order and the name in ascending order to handle ties.
-- In the second query, I joined Movies with MovieRating, filtered ratings to February 2020, and calculated the average rating for each movie. I sorted by average rating in descending order and title in ascending order.
-- I used LIMIT 1 in both queries to select one winner and UNION ALL to combine the two results into the required output.”
-- 8. Interview Follow-up Questions with Answers
-- Q1. What is the difference between UNION and UNION ALL?
-- Answer: UNION removes duplicate rows, whereas UNION ALL keeps all rows. Here, UNION ALL combines the two answers without unnecessarily removing duplicates.
-- Q2. Why do we use ORDER BY COUNT(...) DESC in the first query?
-- Answer: We want the user with the greatest number of movie ratings, so we sort the counts from highest to lowest.
-- Q3. Why do we use u.name ASC after the count?
-- Answer: If multiple users have the same count, sorting by name in ascending order selects the alphabetically smallest name.
-- Q4. Why do we use AVG() instead of SUM() for the movie?
-- Answer: The question asks for the highest average rating, not the highest total rating. A movie with more ratings should not automatically win because its total is larger.
-- Q5. Why do we use LIMIT 1?
-- Answer: Each part of the problem requires exactly one winner, including when there is a tie.
-- Q6. Why do we filter the date before calculating the average?
-- Answer: Only ratings submitted during February 2020 should contribute to the average. Filtering before grouping ensures ratings from other months are excluded.
-- Q7. Why do we group by both the ID and name/title?
-- Answer: Grouping by the ID identifies each entity, while including its name or title makes the query compatible with MySQL's ONLY_FULL_GROUP_BY mode.
-- Q8. Can we solve this problem using a CTE?
-- Answer: Yes. We can calculate the winning user and movie in separate common table expressions and combine them with UNION ALL.
-- Q9. What is the difference between WHERE and HAVING?
-- Answer: WHERE filters rows before grouping, while HAVING filters groups after aggregation. We use WHERE here to filter February's rating records before calculating averages.
-- Q10. What happens if two movies have the same average rating?
-- Answer: ORDER BY AVG(mr.rating) DESC, m.title ASC sorts tied movies alphabetically and selects the first one using LIMIT 1.
-- 9. Pattern to Remember
-- JOIN → GROUP BY → COUNT/AVG → ORDER BY with tie-breaker → LIMIT 1 → UNION ALL
-- Use this pattern when a SQL problem asks for multiple winners based on different aggregate calculations and requires tie-breaking rules.
