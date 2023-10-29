INSERT INTO users (id, email, first_name, last_name, password, status, created_on, created_at, created_by,
                   updated_on, updated_at, updated_by)
VALUES (1, 'rslakra@lakra.com', 'Rohtash', 'Lakra', 'password', 'INACTIVE',
        DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP, 'rslakra',
        DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP, 'rslakra');