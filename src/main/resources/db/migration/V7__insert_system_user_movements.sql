ALTER TABLE users DROP CONSTRAINT ck_users_role;

ALTER TABLE users ADD CONSTRAINT ck_users_role
    CHECK (role IN ('OPERATOR', 'ADMIN', 'SYSTEM'));


INSERT INTO users (id, name, username, email, password, role, active) VALUES
    ('00000000-0000-0000-0000-000000000001',
     'System',
     'system',
     'system@nori-stock.internal',
     '$2y$10$CjfR5Ufw5kxKUyyO0v0DNuZB6vejAG9JarudXgpRul.qkzj3rhuOa',
     'SYSTEM',
     true);