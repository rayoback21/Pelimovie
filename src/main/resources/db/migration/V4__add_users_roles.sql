
CREATE TABLE IF NOT EXISTS users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    password VARCHAR(225),
    email VARCHAR(75) UNIQUE,
    locked BOOLEAN,
    disabled BOOLEAN
    );

CREATE TABLE IF NOT EXISTS roles (
    id SERIAL PRIMARY KEY,
    roles VARCHAR(50),
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES users(id)
    );