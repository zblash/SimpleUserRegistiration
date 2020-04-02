-- Table: public.roles

-- DROP TABLE public.roles;

CREATE TABLE public.roles
(
    id bigint NOT NULL,
    name character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT roles_pkey PRIMARY KEY (id)
)
    WITH (
        OIDS = FALSE
    )
    TABLESPACE pg_default;

ALTER TABLE public.roles
    OWNER to postgres;

-- Table: public.users

-- DROP TABLE public.users;

CREATE TABLE public.users
(
    id bigint NOT NULL,
    activation_code character varying(255) COLLATE pg_catalog."default",
    activation_token_sent_time timestamp without time zone,
    active boolean NOT NULL,
    banned boolean NOT NULL,
    created_at timestamp without time zone,
    email character varying(255) COLLATE pg_catalog."default",
    first_login_date timestamp without time zone,
    name character varying(25) COLLATE pg_catalog."default",
    password character varying(90) COLLATE pg_catalog."default",
    password_reset_token character varying(255) COLLATE pg_catalog."default",
    reset_token_expire_time timestamp without time zone,
    surname character varying(25) COLLATE pg_catalog."default",
    role_id bigint,
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT fkp56c1712k691lhsyewcssf40f FOREIGN KEY (role_id)
        REFERENCES public.roles (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;