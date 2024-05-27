ALTER TABLE public."Sec01_users" ADD active boolean NOT NULL DEFAULT true;

CREATE TABLE public."token" (
                                code uuid NOT NULL DEFAULT gen_random_uuid(),
                                "content" varchar NOT NULL,
                                active boolean NOT NULL DEFAULT true,
                                "timestamp" timestamp without time zone NULL DEFAULT CURRENT_TIMESTAMP,
                                user_code uuid NULL,
                                CONSTRAINT token_pk PRIMARY KEY (code),
                                CONSTRAINT token_fk FOREIGN KEY (user_code) REFERENCES public."user"(code) ON DELETE CASCADE ON UPDATE CASCADE
);