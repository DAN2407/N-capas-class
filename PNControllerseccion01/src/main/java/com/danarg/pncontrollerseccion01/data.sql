
INSERT INTO Sec01_categories (code, name) VALUES ('CAT001', 'Category 1') ON CONFLICT ("code") DO UPDATE SET name = EXCLUDED.name;
INSERT INTO sec01_categories (code, name) VALUES ('CAT002', 'Category 2') ON CONFLICT ("code") DO UPDATE SET name = EXCLUDED.name;
INSERT INTO sec01_categories (code, name) VALUES ('CAT003', 'Category 3') ON CONFLICT ("code") DO UPDATE SET name = EXCLUDED.name;
INSERT INTO sec01_categories (code, name) VALUES ('CAT004', 'Category 4') ON CONFLICT ("code") DO UPDATE SET name = EXCLUDED.name;
INSERT INTO sec01_categories (code, name) VALUES ('CAT005', 'Category 5') ON CONFLICT ("code") DO UPDATE SET name = EXCLUDED.name;
INSERT INTO sec01_categories (code, name) VALUES ('CAT006', 'Category 6') ON CONFLICT ("code") DO UPDATE SET name = EXCLUDED.name;
INSERT INTO sec01_categories (code, name) VALUES ('CAT007', 'Category 7') ON CONFLICT ("code) DO UPDATE SET name = EXCLUDED.name;
INSERT INTO sec01_categories (code, name) VALUES ('CAT008', 'Category 8') ON CONFLICT (code) DO UPDATE SET name = EXCLUDED.name;

/* Roles
INSERT INTO "sec01_roles" VALUES ('USER', 'user') ON CONFLICT ("id") DO UPDATE SET "name" = EXCLUDED."name";
INSERT INTO "sec01_roles" VALUES ('LBRN', 'librarian') ON CONFLICT ("id") DO UPDATE SET "name" = EXCLUDED."name";
INSERT INTO "sec01_roles" VALUES ('SUDO', 'sysadmin') ON CONFLICT ("id") DO UPDATE SET "name" = EXCLUDED."name";
