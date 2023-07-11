INSERT INTO manager (employee_id, department, responsibility)
SELECT id, 'IT', NULL
FROM employee
WHERE position = 'MANAGER'