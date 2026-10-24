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