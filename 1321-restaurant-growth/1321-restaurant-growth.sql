# Write your MySQL query statement below
select visited_on,(
    select sum(amount)
    from customer
    where visited_on between date_sub(c.visited_on, INTERVAL 6 DAY) and c.visited_on
) as amount,
ROUND(
    (
        select sum(amount) / 7
        from customer
        where visited_on between date_sub(c.visited_on,INTERVAL 6 DAY) and c.visited_on
    ),
    2
) as average_amount
from customer c
where visited_on >= (
    select date_add(min(visited_on),INTERVAL 6 DAY)
    FROM customer
)

GROUP BY visited_on;