CREATE DATABASE IF NOT EXISTS studentdb;

USE studentdb;

CREATE TABLE IF NOT EXISTS students (
    id VARCHAR(36) NOT NULL,
    email VARCHAR(255),
    name VARCHAR(255),
    PRIMARY KEY (id)
);

INSERT INTO students (id, email, name)
VALUES ('sample-id', 'sample@example.com', 'Sample Student')
ON DUPLICATE KEY UPDATE
    email = VALUES(email),
    name = VALUES(name);
