CREATE TABLE tasks(
                      ID SERIAL PRIMARY KEY NOT NULL,
                      NAME       VARCHAR(255),
                      DESCRIPTION VARCHAR(255),
                      user_id BIGINT REFERENCES users(id)
);