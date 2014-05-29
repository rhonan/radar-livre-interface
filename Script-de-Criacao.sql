
CREATE TABLE public.aeroporto (
                prefixo VARCHAR(45) NOT NULL,
                nome VARCHAR(45),
                pais VARCHAR(45),
                latitude DOUBLE PRECISION,
                longitude DOUBLE PRECISION,
                altitude INTEGER,
                cidade VARCHAR(45),
                CONSTRAINT aeroporto_pk PRIMARY KEY (prefixo)
);


CREATE TABLE public.aeronave (
                hex VARCHAR(45) NOT NULL,
                modelo VARCHAR(45),
                companhia VARCHAR(45),
                CONSTRAINT aeronave_pk PRIMARY KEY (hex)
);


CREATE TABLE public.rota (
                id VARCHAR(45) NOT NULL,
                hex VARCHAR(45) NOT NULL,
                aeroporto_chegada VARCHAR(45) NOT NULL,
                aeroporto_saida VARCHAR(45) NOT NULL,
                CONSTRAINT rota_pk PRIMARY KEY (id, hex)
);


CREATE TABLE public.rota_aeroporto (
                id VARCHAR(45) NOT NULL,
                hex VARCHAR(45) NOT NULL,
                prefixo VARCHAR(45) NOT NULL,
                sequencial INTEGER,
                CONSTRAINT rota_aeroporto_pk PRIMARY KEY (id, hex, prefixo)
);

CREATE SEQUENCE public.observacao_id_seq
    START WITH 0
    INCREMENT BY 1
    MINVALUE 0
    NO MAXVALUE
    CACHE 1;

CREATE TABLE public.observacao (
                id INTEGER NOT NULL DEFAULT nextval('public.observacao_id_seq'),
                rota_id VARCHAR(45),
                hex VARCHAR(45) NOT NULL,
                radar VARCHAR(45),
                latitude DOUBLE PRECISION,
                longitude DOUBLE PRECISION,
                altitude INTEGER,
                velocidade DOUBLE PRECISION,
                angulo DOUBLE PRECISION,
                hora TIMESTAMP,
                CONSTRAINT observacao_pk PRIMARY KEY (id, hex)
);

ALTER SEQUENCE public.observacao_id_seq OWNED BY public.observacao.id;

ALTER TABLE public.rota ADD CONSTRAINT aeroporto_rota_fk
FOREIGN KEY (aeroporto_chegada)
REFERENCES public.aeroporto (prefixo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.rota ADD CONSTRAINT aeroporto_rota_fk1
FOREIGN KEY (aeroporto_saida)
REFERENCES public.aeroporto (prefixo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.rota_aeroporto ADD CONSTRAINT aeroporto_rota_aeroporto_fk
FOREIGN KEY (prefixo)
REFERENCES public.aeroporto (prefixo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.rota ADD CONSTRAINT aeronave_rota_fk
FOREIGN KEY (hex)
REFERENCES public.aeronave (hex)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.observacao ADD CONSTRAINT rota_observacao_fk
FOREIGN KEY (rota_id, hex)
REFERENCES public.rota (id, hex)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.rota_aeroporto ADD CONSTRAINT rota_rota_aeroporto_fk
FOREIGN KEY (id, hex)
REFERENCES public.rota (id, hex)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;
