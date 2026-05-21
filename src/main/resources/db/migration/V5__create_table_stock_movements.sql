-- =============================================================
--  V5 — Table: stock_movements
-- =============================================================

CREATE TABLE stock_movements (
    id              UUID            NOT NULL DEFAULT gen_random_uuid(),
    product_id      UUID            NOT NULL,
    sector_id       UUID            NOT NULL,
    user_id         UUID            NOT NULL,
    type            VARCHAR(20)     NOT NULL,
    quantity        INTEGER         NOT NULL,
    reason          VARCHAR(255),
    order_id        UUID,
    created_at      TIMESTAMP       NOT NULL DEFAULT NOW(),

    CONSTRAINT pk_stock_movements           PRIMARY KEY (id),
    CONSTRAINT fk_movements_product         FOREIGN KEY (product_id) REFERENCES products (id),
    CONSTRAINT fk_movements_sector          FOREIGN KEY (sector_id)  REFERENCES sectors  (id),
    CONSTRAINT fk_movements_user            FOREIGN KEY (user_id)    REFERENCES users    (id),
    CONSTRAINT ck_movements_type            CHECK (type IN (
        'OUTBOUND',
        'INBOUND',
        'ADJUSTMENT'
    )),
    CONSTRAINT ck_movements_quantity        CHECK (quantity > 0)
);

COMMENT ON TABLE  stock_movements          IS 'All stock movements — inbound, outbound and adjustments';
COMMENT ON COLUMN stock_movements.type     IS 'OUTBOUND = sale confirmed via Kafka | INBOUND = restock | ADJUSTMENT = manual correction';
COMMENT ON COLUMN stock_movements.order_id IS 'Reference to the Sales API order ID — populated for OUTBOUND movements via Kafka';
COMMENT ON COLUMN stock_movements.reason   IS 'Optional description of why the movement occurred';

CREATE INDEX idx_movements_product    ON stock_movements (product_id);
CREATE INDEX idx_movements_sector     ON stock_movements (sector_id);
CREATE INDEX idx_movements_user       ON stock_movements (user_id);
CREATE INDEX idx_movements_type       ON stock_movements (type);
CREATE INDEX idx_movements_created_at ON stock_movements (created_at DESC);
CREATE INDEX idx_movements_order_id   ON stock_movements (order_id);
