CREATE TABLE IF NOT EXISTS designer
(
    employee_id INTEGER NOT NULL PRIMARY KEY REFERENCES employee (id) ON DELETE CASCADE,
    design_tool VARCHAR,
    ux_skill    VARCHAR
)