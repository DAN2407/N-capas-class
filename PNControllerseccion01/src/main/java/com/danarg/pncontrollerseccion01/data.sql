INSERT INTO "sec01_categories" VALUES ('CT_AVR', 'Aventura') ON CONFLICT ("code") DO update SET "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_TRR', 'Terror') ON CONFLICT ("code") DO update SET "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_ACC', 'Accion') ON CONFLICT ("code") DO update SET "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_CYF', 'Ciencia Ficcion') ON CONFLICT ("code") DO update SET "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_FNT', 'Fantasia') ON CONFLICT ("code") DO update SET "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_MST', 'Misterio') ON CONFLICT ("code") DO update SET "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_SUS', 'Suspenso') ON CONFLICT ("code") DO update SET "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_DRM', 'Drama') ON CONFLICT ("code") DO update SET "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_DOC', 'Documental') ON CONFLICT ("code") DO update SET "name" = excluded."name";
INSERT INTO "sec01_categories" VALUES ('CT_SCS', 'Ciencia') ON CONFLICT ("code") DO update SET "name" = excluded."name";