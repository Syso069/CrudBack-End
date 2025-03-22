CREATE SEQUENCE produto_id_seq START 1;

CREATE TABLE produto(
    id NUMERIC PRIMARY KEY NOT NULL DEFAULT nextval('produto_id_seq'),
    nome TEXT NOT NULL,
    preco NUMERIC(10,2) NOT NULL
);