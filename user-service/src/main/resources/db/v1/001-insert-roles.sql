INSERT INTO roles (id, name, status, created_on, created_at, created_by, updated_on, updated_at, updated_by)
VALUES (1, 'ADMIN', 'INACTIVE', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP,
        'rslakra', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP, 'rslakra'),
       (2, 'USER', 'INACTIVE', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP,
        'rslakra', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP, 'rslakra'),
       (3, 'CREATOR', 'INACTIVE', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP,
        'rslakra', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP, 'rslakra'),
       (4, 'COLLABORATOR', 'INACTIVE', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000,
        CURRENT_TIMESTAMP, 'rslakra', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000,
        CURRENT_TIMESTAMP, 'rslakra'),
       (5, 'EDITOR', 'INACTIVE', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP,
        'rslakra', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000, CURRENT_TIMESTAMP, 'rslakra'),
       (6, 'REFRESH_TOKEN', 'INACTIVE', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000,
        CURRENT_TIMESTAMP, 'rslakra', DATEDIFF('SECOND', DATE '1970-01-01', CURRENT_TIMESTAMP()) * 1000,
        CURRENT_TIMESTAMP, 'rslakra');