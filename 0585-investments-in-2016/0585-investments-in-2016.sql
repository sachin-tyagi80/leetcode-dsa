
SELECT
    ROUND(SUM(tiv_2016), 2) AS tiv_2016
FROM Insurance
WHERE tiv_2015 IN (
    SELECT tiv_2015
    FROM Insurance
    GROUP BY tiv_2015
    HAVING COUNT(*) > 1
)
AND (lat, lon) IN (
    SELECT lat, lon
    FROM Insurance
    GROUP BY lat, lon
    HAVING COUNT(*) = 1
);


///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
-- Interviewer ko English mein kaise explain karein?
-- “The problem requires us to calculate the sum of tiv_2016 for policyholders who satisfy two conditions.

-- First, their tiv_2015 value must be shared by at least one other policyholder. I use GROUP BY tiv_2015 with HAVING COUNT(*) > 1 to identify these repeated values.

-- Second, their (lat, lon) location pair must be unique. I use another grouped subquery with HAVING COUNT(*) = 1.

-- I filter the Insurance table using both conditions and calculate the sum of tiv_2016. Finally, I use ROUND() to return the result to two decimal places.”

-- 6. Interview follow-up questions
-- 1. Why do we use HAVING instead of WHERE?WHERE filters individual rows before grouping. HAVING filters groups after aggregate functions such as COUNT() have been calculated.
-- 2. What is the difference between COUNT(*) > 1 and COUNT(*) = 1?COUNT(*) > 1 identifies values or locations that appear multiple times. COUNT(*) = 1 identifies values or location pairs that appear exactly once.
-- 3. Why do we group by both lat and lon?The problem defines a location using the pair (lat, lon). Two policies share a location only when both coordinates match.
-- 4. Can we solve this using a JOIN?Yes. We can join the Insurance table to grouped subqueries that identify repeated tiv_2015 values and unique location pairs.
-- 5. Why is ROUND() used?The problem explicitly requires the final sum to be rounded to two decimal places.
-- 6. Can we use COUNT(DISTINCT ...)?Not directly as a replacement for these checks. We need to identify each repeated tiv_2015 value and each unique (lat, lon) pair, which grouped subqueries handle clearly.
-- Pattern to remember: GROUP BY + HAVING is useful when a problem asks you to find repeated values or unique combinations. Here, dono conditions ko AND se combine karna hai, kyunki policyholder ko both conditions satisfy karni hain.
