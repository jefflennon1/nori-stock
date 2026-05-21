-- =============================================================
--  V4 — Table: products
-- =============================================================

CREATE TABLE products (
    id                  UUID            NOT NULL DEFAULT gen_random_uuid(),
    category_id         UUID            NOT NULL,
    sector_id           UUID            NOT NULL,
    name                VARCHAR(200)    NOT NULL,
    description         TEXT,
    sku                 VARCHAR(100),
    quantity            INTEGER         NOT NULL DEFAULT 0,
    min_quantity        INTEGER         NOT NULL DEFAULT 0,
    active              BOOLEAN         NOT NULL DEFAULT TRUE,
    created_at          TIMESTAMP       NOT NULL DEFAULT NOW(),
    updated_at          TIMESTAMP       NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_products                  PRIMARY KEY (id),
    CONSTRAINT fk_products_category        FOREIGN KEY (category_id) REFERENCES categories (id),
    CONSTRAINT fk_products_sector          FOREIGN KEY (sector_id)   REFERENCES sectors    (id),
    CONSTRAINT uq_products_sku             UNIQUE (sku),
    CONSTRAINT ck_products_qty_non_negative CHECK (quantity >= 0),
    CONSTRAINT ck_products_min_qty          CHECK (min_quantity >= 0)
);

COMMENT ON TABLE  products             IS 'Products managed by the Stock API';
COMMENT ON COLUMN products.sku         IS 'Stock Keeping Unit — unique product identifier code';
COMMENT ON COLUMN products.quantity    IS 'Current available quantity in stock';
COMMENT ON COLUMN products.min_quantity IS 'Minimum quantity threshold — alert when stock falls below this';

CREATE INDEX idx_products_category ON products (category_id);
CREATE INDEX idx_products_sector   ON products (sector_id);
CREATE INDEX idx_products_active   ON products (active);
CREATE INDEX idx_products_sku      ON products (sku);
