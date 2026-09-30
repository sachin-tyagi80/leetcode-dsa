# Write your MySQL query statement below
SELECT
    a.machine_id,
    ROUND(AVG(b.timestamp - a.timestamp), 3) AS processing_time
FROM Activity a
JOIN Activity b
    ON a.machine_id = b.machine_id
    AND a.process_id = b.process_id
    AND a.activity_type = 'start'
    AND b.activity_type = 'end'
GROUP BY a.machine_id;



/////////////////////////////////////////////////////////////////////
-- 🎤 Interview Explanation

-- “I use a self join on the Activity table because the start and end times of a process are stored in separate rows.

-- First, I join records having the same machine_id and process_id.

-- Then I use the timestamp condition to identify the earlier and later records. The difference between the later timestamp and the earlier timestamp gives the processing time of that process.

-- After that, I use AVG() to calculate the average processing time for each machine.

-- Finally, I use GROUP BY machine_id and ROUND() the result to 3 decimal places.”

-- 🔥 Follow-up Questions

-- 1. Why did you use SELF JOIN?

-- Because start and end activities are stored as separate rows in the same Activity table. Self join allows us to compare those rows.

-- 2. Why do we join on both machine_id and process_id?

-- Because a process is uniquely identified by the combination of:

-- machine_id + process_id

-- We must compare the start and end of the same process on the same machine.

-- 3. Why do you calculate a2.timestamp - a1.timestamp?

-- a1 represents the earlier timestamp and a2 represents the later timestamp.

-- So:

-- End Time - Start Time = Processing Time

-- 4. Why use AVG()?

-- Each machine can execute multiple processes. We need the average processing time for each machine.

-- AVG(processing_time)

-- 5. Why use GROUP BY machine_id?

-- Because the final answer requires one average processing time for each machine.

-- 6. Why use ROUND(..., 3)?

-- The problem requires the processing time to be returned with 3 decimal places.

-- 7. Why not use WHERE instead of the timestamp condition in JOIN?

-- We can technically filter after joining, but putting the matching condition in the ON clause makes the relationship between the two rows clear and is the natural way to define which rows should be joined.

-- 8. What if start and end timestamps are equal?

-- This query uses:

-- a1.timestamp < a2.timestamp

-- So equal timestamps would not match. A more robust solution is to use activity_type:

-- SELECT
--     a.machine_id,
--     ROUND(AVG(b.timestamp - a.timestamp), 3) AS processing_time
-- FROM Activity a
-- JOIN Activity b
--     ON a.machine_id = b.machine_id
--     AND a.process_id = b.process_id
--     AND a.activity_type = 'start'
--     AND b.activity_type = 'end'
-- GROUP BY a.machine_id;

-- This explicitly identifies start and end, so it is easier to explain in an interview.

-- 🧠 Pattern to Remember
-- Same table
--    ↓
-- SELF JOIN
--    ↓
-- Same machine + same process
--    ↓
-- Start → End
--    ↓
-- End - Start
--    ↓
-- AVG()
--    ↓
-- GROUP BY machine

-- One-line interview answer:

-- “Self join the activity table to pair each process's start and end records, calculate end minus start, then average the processing times for each machine.”
