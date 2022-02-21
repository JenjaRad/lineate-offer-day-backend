ALTER TABLE copyright
    add column price DECIMAL;

ALTER TABLE copyright
    add column creation_date DATE;

ALTER TABLE company
    add column total_balance DECIMAL;