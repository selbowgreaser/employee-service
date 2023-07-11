CREATE TABLE IF NOT EXISTS manager
(
    employee_id    INTEGER NOT NULL PRIMARY KEY REFERENCES employee (id) ON DELETE CASCADE,
    department     VARCHAR,
    responsibility VARCHAR
)