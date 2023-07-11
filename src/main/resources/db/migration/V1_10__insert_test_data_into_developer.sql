INSERT INTO developer (employee_id, framework, database_skill, version_control)
SELECT id, 'Spring', 'Good', 'Git'
FROM employee
WHERE position = 'DEVELOPER'