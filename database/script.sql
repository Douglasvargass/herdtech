-- Execute este script no PostgreSQL (pgAdmin ou psql) para preparar o banco.
-- 1) Crie o banco (rode separado, conectado ao banco "postgres"):
--    CREATE DATABASE heedtech;
-- 2) Conecte no banco "heedtech" e rode o restante:

CREATE TABLE IF NOT EXISTS cliente (
    id            SERIAL PRIMARY KEY,
    nome          VARCHAR(100) NOT NULL,
    cpf           BIGINT       NOT NULL,
    data_nasc     VARCHAR(10)  NOT NULL,
    nome_fazenda  VARCHAR(100) NOT NULL,
    endereco      VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS gado (
    id         SERIAL PRIMARY KEY,
    id_cliente INTEGER      NOT NULL REFERENCES cliente (id),
    nome       VARCHAR(100) NOT NULL,
    raca       VARCHAR(50)  NOT NULL,
    genero     VARCHAR(20)  NOT NULL,
    data_nasc  VARCHAR(10)  NOT NULL
);

CREATE TABLE IF NOT EXISTS aplicacao (
    id              SERIAL PRIMARY KEY,
    id_gado         INTEGER      NOT NULL REFERENCES gado (id),
    vacina          VARCHAR(100) NOT NULL,
    data_aplicacao  VARCHAR(10)  NOT NULL,
    proxima_vacina  VARCHAR(10)  NOT NULL,
    quem_aplicou    VARCHAR(100) NOT NULL
);
