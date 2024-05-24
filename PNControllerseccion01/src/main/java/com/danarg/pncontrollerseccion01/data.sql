ALTER TABLE public."user" ADD active boolean NOT NULL DEFAULT true;

CREATE TABLE public."token" (
                                code uuid NOT NULL DEFAULT gen_random_uuid(),
                                "content" varchar NOT NULL,
                                active boolean NOT NULL DEFAULT true,
                                "timestamp" timestamp without time zone NULL DEFAULT CURRENT_TIMESTAMP,
                                user_code uuid NULL,
                                CONSTRAINT token_pk PRIMARY KEY (code),
                                CONSTRAINT token_fk FOREIGN KEY (user_code) REFERENCES public."user"(code) ON DELETE CASCADE ON UPDATE CASCADE
);

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

