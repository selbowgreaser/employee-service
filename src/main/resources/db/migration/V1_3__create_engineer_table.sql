CREATE TABLE IF NOT EXISTS engineer
(
    employee_id          INTEGER NOT NULL PRIMARY KEY REFERENCES employee (id) ON DELETE CASCADE,
    programming_language VARCHAR,
    tool                 VARCHAR,
    certification        VARCHAR
)