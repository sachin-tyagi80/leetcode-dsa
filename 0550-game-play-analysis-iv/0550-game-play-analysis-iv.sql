# Write your MySQL query statement below
WITH first_login AS (
    SELECT
        player_id,
        MIN(event_date) AS first_date
    FROM activity
    GROUP BY player_id
)
SELECT 
    ROUND(
        COUNT(
            CASE
                WHEN DATEDIFF(a.event_date, f.first_date) = 1 THEN 1
            END
        ) / COUNT(DISTINCT f.player_id),
        2
    ) AS fraction
FROM first_login f
LEFT JOIN activity a
    ON f.player_id = a.player_id;


////////////////////////////////////////////////////////////////////////

SELECT
    ROUND(
        COUNT(DISTINCT a.player_id) /
        (SELECT COUNT(DISTINCT player_id) FROM Activity),
        2
    ) AS fraction
FROM Activity a
JOIN (
    SELECT
        player_id,
        MIN(event_date) AS first_login
    FROM Activity
    GROUP BY player_id
) AS first_login_table
    ON a.player_id = first_login_table.player_id
   AND a.event_date = DATE_ADD(first_login_table.first_login, INTERVAL 1 DAY);
/////////////////////////////////////////////////////////////////////////
-- . Interview explanation (English)
-- “First, I find the earliest login date for each player using MIN(event_date) and GROUP BY player_id. Then, I join this result with the original Activity table and use DATE_ADD() to check whether each player logged in exactly one day after their first login. I count the distinct qualifying players and divide that count by the total number of distinct players. Finally, I use ROUND() to return the fraction to two decimal places.”

-- 6. Interview follow-up questions
-- 1. Why do we use MIN(event_date)?To find each player's first login date.
-- 2. Why use DATE_ADD()?It adds one calendar day to the first login date so we can check for a login on the immediately following day.
-- 3. Why not use DATEDIFF()?We can also use DATEDIFF(a.event_date, first_login_table.first_login) = 1. DATE_ADD() is another clear way to express the condition.
-- 4. Why COUNT(DISTINCT player_id)?It ensures each qualifying player is counted once, even if duplicate qualifying rows were possible. The given primary key already makes each player-date combination unique.
-- 5. Why divide by total distinct players?The question asks for the fraction of all players who returned the next day, not the percentage of all activity records.
-- 6. What is the difference between COUNT(*) and COUNT(DISTINCT player_id)?COUNT(*) counts rows; COUNT(DISTINCT player_id) counts unique player IDs.
-- 7. Can this be solved without JOIN?Yes. A correlated subquery can find each player's first login date, or a window function can rank login dates and compare the next day's record.
-- 8. What does ROUND(value, 2) do?It rounds the calculated fraction to two digits after the decimal point.
-- Pattern to remember: MIN(date) → find the first event → check the next calendar day → count qualifying entities → divide by total entities → ROUND().
