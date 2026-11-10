# HerdTech — Gestão de rebanho (protótipo em Java)

Protótipo em Java, integrado a um banco de dados PostgreSQL, que ajuda o pequeno produtor a tirar o rebanho do caderno:
cadastro de clientes (produtores e fazendas), cadastro de gado e controle de vacinação.

Projeto da Unidade Curricular **Programação de Aplicativos** — Curso Técnico em Desenvolvimento de Sistemas, SENAI Tubarão.

**Equipe:** Douglas, Guilherme, Julia e Vitor.

## Funcionalidades

Três CRUDs completos (listar, cadastrar, alterar e excluir), todos gravados no banco:

| Módulo | O que faz |
|---|---|
| Cliente | Produtor e propriedade (nome, CPF, data de nascimento, fazenda e endereço) |
| Gado | Animais de cada cliente (apelido, raça, gênero e data de nascimento) |
| Aplicação | Vacinas aplicadas em cada animal, com data, próxima dose e quem aplicou |

## Arquitetura em camadas

Fluxo: **Controller → View → Service → DAO → Banco de dados**

```
src/
├── controllers/   # orquestram o fluxo do menu
├── views/         # telas (JOptionPane)
├── services/      # validações e regras de negócio
├── dao/           # acesso ao banco (JDBC) e classe de conexão
├── models/        # Cliente, Gado e Aplicacao
├── exceptions/    # exceções próprias
└── utils/         # conversões
database/script.sql  # criação das tabelas
lib/                 # drivers JDBC (PostgreSQL e MySQL)
```

## Como executar

**Requisitos:** JDK 25 e PostgreSQL.

1. No PostgreSQL, crie o banco: `CREATE DATABASE heedtech;`
2. Conectado nesse banco, execute o arquivo [`database/script.sql`](database/script.sql) para criar as tabelas.
3. Abra o projeto no IntelliJ e adicione `lib/postgresql-42.7.4.jar` em **File → Project Structure → Libraries**, se ele ainda não aparecer.
4. Execute a classe `Principal`. Na primeira operação com o banco, o programa pede a senha do usuário `postgres` numa janela.

Se o seu PostgreSQL usar outra porta ou outro usuário, ajuste `URL` e `USUARIO` em [`src/dao/Conexao.java`](src/dao/Conexao.java).

## Testes automatizados

A pasta `src/tests` tem um teste para cada CRUD, na ordem Cliente, Gado e Aplicação. Cada um testa listar, cadastrar,
cadastrar com campos vazios (deve lançar exceção), alterar e excluir, direto no banco. Os testes criam os próprios dados
e apagam tudo no final, sem mexer nos registros existentes.

Para executar, rode a classe `tests.ExecutarTestes` (ela pede a senha do banco numa janela). A saída mostra
`Passou` ou `Falhou` em cada teste e um resumo no final.

## Banco de dados

Três tabelas: `cliente`, `gado` (pertence a um cliente) e `aplicacao` (pertence a um gado).
Excluir um cliente que tem gado, ou um gado que tem aplicações, é recusado pelo banco.
