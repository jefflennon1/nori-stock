-- =============================================================
--  V6 — Seed data
--  NOTE: replace password hashes with real BCrypt values
--  Example (Java): new BCryptPasswordEncoder().encode("demo1234")
-- =============================================================

-- Admin user (password: admin1234)
INSERT INTO users (id, name, username, email, password, role) VALUES
    (gen_random_uuid(),
     'Nori Admin',
     'nori_admin',
     'admin@nori-stock.com',
     '$2a$12$GSCy9hV56oJNOv83YuTw..MnGTEeK1uWJSu3Scx1BfLSvGMSo0Etu',
     'ADMIN');

-- Demo operator user (password: demo1234)
INSERT INTO users (id, name, username, email, password, role) VALUES
    (gen_random_uuid(),
     'Demo Operator',
     'operator_demo',
     'operator@demo.com',
     '$2a$12$0RrIP7/gGK7HzO18Vw.N8OdWwTaHRYpurzCccP8/CdNASb6p0bO.y',
     'OPERATOR');

-- Categories
INSERT INTO categories (id, name, description) VALUES
    ('b1c2d3e4-0000-0000-0000-000000000001', 'Electronics',  'Smartphones, laptops and accessories'),
    ('b1c2d3e4-0000-0000-0000-000000000002', 'Books',        'Physical and digital books'),
    ('b1c2d3e4-0000-0000-0000-000000000003', 'Peripherals',  'Keyboards, mice and headsets');

-- Sectors
INSERT INTO sectors (id, name, description, location) VALUES
    ('c1d2e3f4-0000-0000-0000-000000000001', 'Sector A', 'High-value electronics storage',  'Building A - Floor 1'),
    ('c1d2e3f4-0000-0000-0000-000000000002', 'Sector B', 'Books and printed materials',     'Building A - Floor 2'),
    ('c1d2e3f4-0000-0000-0000-000000000003', 'Sector C', 'Peripherals and accessories',     'Building B - Floor 1');

-- Products
INSERT INTO products (id, category_id, sector_id, name, description, sku, quantity, min_quantity) VALUES
    (gen_random_uuid(),
     'b1c2d3e4-0000-0000-0000-000000000001',
     'c1d2e3f4-0000-0000-0000-000000000001',
     'Smartphone X Pro', 'Flagship smartphone 256GB', 'SKU-ELEC-001', 50, 10),

    (gen_random_uuid(),
     'b1c2d3e4-0000-0000-0000-000000000001',
     'c1d2e3f4-0000-0000-0000-000000000001',
     'UltraBook Laptop', 'Lightweight laptop 16GB RAM 512GB SSD', 'SKU-ELEC-002', 30, 5),

    (gen_random_uuid(),
     'b1c2d3e4-0000-0000-0000-000000000002',
     'c1d2e3f4-0000-0000-0000-000000000002',
     'Clean Code', 'Robert C. Martin', 'SKU-BOOK-001', 100, 20),

    (gen_random_uuid(),
     'b1c2d3e4-0000-0000-0000-000000000002',
     'c1d2e3f4-0000-0000-0000-000000000002',
     'Domain-Driven Design', 'Eric Evans', 'SKU-BOOK-002', 80, 15),

    (gen_random_uuid(),
     'b1c2d3e4-0000-0000-0000-000000000003',
     'c1d2e3f4-0000-0000-0000-000000000003',
     'RGB Mechanical Keyboard', 'Red switch, US layout', 'SKU-PERI-001', 60, 10),

    (gen_random_uuid(),
     'b1c2d3e4-0000-0000-0000-000000000003',
     'c1d2e3f4-0000-0000-0000-000000000003',
     'Wireless Gaming Mouse', '12000 DPI optical mouse', 'SKU-PERI-002', 75, 15);
