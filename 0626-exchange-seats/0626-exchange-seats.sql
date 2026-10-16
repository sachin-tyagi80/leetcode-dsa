# Write your MySQL query statement below
SELECT
    CASE
        WHEN id % 2 = 1 AND id < (SELECT MAX(id) FROM Seat)
            THEN id + 1
        WHEN id % 2 = 0
            THEN id - 1
        ELSE id
    END AS id,
    student
FROM Seat
ORDER BY id ASC;


//////////////////////////////////////////////////////////////////
7. Interview Explanation
You can explain this solution to the interviewer as follows:
I use a CASE expression to calculate the new seat ID for each student. Odd IDs are increased by one, except when the ID is the maximum ID, and even IDs are decreased by one. The maximum ID check prevents the last student from being swapped when the total number of students is odd. Finally, I use ORDER BY on the calculated ID in ascending order.

8. Interview Follow-up Questions with Answers
Q1. What does % mean in SQL?
It is the modulo operator, which returns the remainder after division.
5 % 2 = 1
4 % 2 = 0


Q2. Why do we use MAX(id)?
To identify the last student. If the maximum ID is odd, that student must keep their seat.
Q3. What happens if there is only one student?
The maximum ID is 1. The odd-ID condition fails because 1 < 1 is false, so the student keeps ID 1.
Q4. What is CASE WHEN used for?
It performs conditional logic in SQL, similar to if-else statements in Java.
Q5. Why is ORDER BY id ASC necessary?
Because swapping the IDs changes their calculated positions. We must sort the output to satisfy the question.
Q6. Does this query permanently update the table?
No. It only returns the swapped IDs in the query result. It does not modify the original Seat table.
Q7. What is the time complexity?
The query processes the rows and orders the result. Sorting generally takes \(O(n\log n)\) time for \(n\) students. The exact execution plan can vary.
9. Pattern to Remember
For seat-swapping problems, remember:
- id % 2 = 1 → odd ID.
- id % 2 = 0 → even ID.
- Odd ID → id + 1, unless it is the final unpaired student.
- Even ID → id - 1.
- CASE WHEN → conditional calculation.
- ORDER BY id ASC → sort the final result.
Interview tip: The key trick is that you are not physically exchanging rows. You calculate a new ID for each student, retain the student's name, and then sort the output by the new ID.
