INSERT INTO phones (id, user_id, number, type, created_on, created_at, created_by, updated_on, updated_at,
                    updated_by)
VALUES (1, 1, 4085108759, 'Mobile', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000,
        CURRENT_TIMESTAMP, 'rslakra', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000,
        CURRENT_TIMESTAMP, 'rslakra'),
       (2, 1, 4085108750, 'Mobile', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000,
        CURRENT_TIMESTAMP, 'rslakra', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000,
        CURRENT_TIMESTAMP, 'rslakra');