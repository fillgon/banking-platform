CREATE TABLE transactions (
                              id BIGSERIAL PRIMARY KEY,
                              type VARCHAR(255) NOT NULL,
                              source_account_id BIGINT NOT NULL,
                              destination_account_id BIGINT,
                              amount NUMERIC(19, 2) NOT NULL,
                              created_at TIMESTAMP NOT NULL,
                              source_balance_before NUMERIC(19, 2),
                              source_balance_after NUMERIC(19, 2),
                              destination_balance_before NUMERIC(19, 2),
                              destination_balance_after NUMERIC(19, 2)
);