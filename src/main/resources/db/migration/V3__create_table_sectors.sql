-- =============================================================
--  V3 — Table: sectors
-- =============================================================

CREATE TABLE sectors (
    id          UUID            NOT NULL DEFAULT gen_random_uuid(),
    name        VARCHAR(100)    NOT NULL,
    description VARCHAR(255),
    location    VARCHAR(150),
    active      BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP       NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_sectors      PRIMARY KEY (id),
    CONSTRAINT uq_sectors_name UNIQUE (name)
);

COMMENT ON TABLE  sectors          IS 'Physical or logical sectors where products are stored';
COMMENT ON COLUMN sectors.location IS 'Optional physical description e.g. Building A, Shelf 3';
