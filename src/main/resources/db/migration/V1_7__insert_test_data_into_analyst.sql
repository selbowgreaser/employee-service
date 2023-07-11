INSERT INTO analyst (employee_id, notation, business_domain, requirements_gathering_technique)
SELECT id, 'UML', 'Banking', 'Stakeholder Interviews'
FROM employee
WHERE position = 'ANALYST'