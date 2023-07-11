INSERT INTO designer (employee_id, design_tool, ux_skill)
SELECT id, 'Figma', 'Great'
FROM employee
WHERE position = 'DESIGNER'