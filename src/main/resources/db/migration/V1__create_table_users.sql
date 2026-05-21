-- =============================================================
--  V1 — Table: users
-- =============================================================

CREATE TABLE users (
    id            UUID            NOT NULL DEFAULT gen_random_uuid(),
    name          VARCHAR(150)    NOT NULL,
    username      VARCHAR(30)     NOT NULL,
    email         VARCHAR(255)    NOT NULL,
    password      VARCHAR(255)    NOT NULL,
    role          VARCHAR(30)     NOT NULL DEFAULT 'OPERATOR',
    active        BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at    TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMP       NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_users        PRIMARY KEY (id),
    CONSTRAINT uq_users_email    UNIQUE (email),
    CONSTRAINT uq_users_username UNIQUE (username),
    CONSTRAINT ck_users_role     CHECK (role IN ('OPERATOR', 'ADMIN'))
);

COMMENT ON TABLE  users          IS 'Stock API users — operators and admins';
COMMENT ON COLUMN users.role     IS 'OPERATOR = views stock movements | ADMIN = manages products and sectors';
COMMENT ON COLUMN users.password IS 'BCrypt password hash';
