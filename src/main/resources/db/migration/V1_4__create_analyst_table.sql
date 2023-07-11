CREATE TABLE IF NOT EXISTS analyst
(
    employee_id                      INTEGER NOT NULL PRIMARY KEY REFERENCES employee (id) ON DELETE CASCADE,
    notation                         VARCHAR,
    business_domain                  VARCHAR,
    requirements_gathering_technique VARCHAR
)