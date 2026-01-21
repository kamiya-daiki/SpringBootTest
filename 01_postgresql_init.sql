CREATE TABLE users (
    id          BIGSERIAL PRIMARY KEY,
    username    VARCHAR(50) NOT NULL UNIQUE,
    -- BCrypt でハッシュ化されたパスワード
    password    VARCHAR(100) NOT NULL,
    enabled     BOOLEAN DEFAULT TRUE
);

INSERT INTO users (username, password)
VALUES ('user', '}$2a$10$Dow1xgFQzvQKZy1E9K8b9e0ZK3cZPjvF1qJ1nPzZ6l7YpJ1pQeW8C'); -- "password"

