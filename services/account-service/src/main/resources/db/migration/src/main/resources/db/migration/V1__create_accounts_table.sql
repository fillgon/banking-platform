CREATE TABLE accounts (
                          id BIGSERIAL PRIMARY KEY,
                          account_number VARCHAR(255) NOT NULL UNIQUE,
                          owner_name VARCHAR(255) NOT NULL,
                          balance NUMERIC(19, 2) NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          updated_at TIMESTAMP,
                          version BIGINT NOT NULL
);