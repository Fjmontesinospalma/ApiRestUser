CREATE TABLE "user" (
                      id UUID PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      password VARCHAR(255) NOT NULL,
                      created TIMESTAMP NOT NULL,
                      modified TIMESTAMP NOT NULL,
                      last_login TIMESTAMP NOT NULL,
                      token VARCHAR(255) NOT NULL,
                      is_active BOOLEAN NOT NULL
);

CREATE TABLE phone (
                       id UUID PRIMARY KEY,
                       number VARCHAR(20) NOT NULL,
                       citycode VARCHAR(5) NOT NULL,
                       contrycode VARCHAR(5) NOT NULL,
                       user_id UUID,
                       FOREIGN KEY (user_id) REFERENCES "user"(id) ON DELETE CASCADE
);

CREATE INDEX idx_user_email ON "user"(email);
CREATE INDEX idx_phone_user_id ON phone(user_id);
