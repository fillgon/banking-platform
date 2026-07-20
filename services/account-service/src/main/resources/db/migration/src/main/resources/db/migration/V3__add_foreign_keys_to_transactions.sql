ALTER TABLE transactions
    ADD CONSTRAINT fk_transaction_source_account
        FOREIGN KEY (source_account_id)
            REFERENCES accounts(id);

ALTER TABLE transactions
    ADD CONSTRAINT fk_transaction_destination_account
        FOREIGN KEY (destination_account_id)
            REFERENCES accounts(id);