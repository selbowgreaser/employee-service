CREATE TABLE IF NOT EXISTS task
(
    uuid        UUID    NOT NULL PRIMARY KEY,
    description VARCHAR NOT NULL,
    employee_id INTEGER
);
