CREATE TABLE public.person (
    id bigint NOT NULL,
    first_name character varying(80) NOT NULL,
    last_name character varying(80) NOT NULL,
    address character varying(100) NOT NULL,
    gender character varying(1) NOT NULL,
    birth_day timestamp(6) without time zone,
    PRIMARY KEY(id)
);
