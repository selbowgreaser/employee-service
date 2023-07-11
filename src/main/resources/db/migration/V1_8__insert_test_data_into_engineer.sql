INSERT INTO engineer (employee_id, programming_language, tool, certification)
SELECT id, 'Java', 'Idea', 'Oracle'
FROM employee
WHERE position = 'ENGINEER'