CREATE TABLE IF NOT EXISTS developer
(
    employee_id     INTEGER NOT NULL PRIMARY KEY REFERENCES employee (id) ON DELETE CASCADE,
    framework       VARCHAR,
    database_skill  VARCHAR,
    version_control VARCHAR
)