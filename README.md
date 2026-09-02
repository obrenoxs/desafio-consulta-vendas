# Desafio: Consulta de Vendas

Projeto desenvolvido como desafio do módulo Back End (JPA, consultas SQL e JPQL) da Formação Desenvolvedor Moderno - DevSuperior.

## Descrição

Sistema de vendas (`Sale`) e vendedores (`Seller`), onde cada venda pertence a um vendedor, e um vendedor pode ter várias vendas.

O projeto implementa duas consultas usando JPQL:

### 1. Relatório de vendas — `GET /sales/report`

Retorna uma listagem paginada de vendas, filtradas por período e/ou trecho do nome do vendedor.

**Parâmetros (todos opcionais):**
- `minDate` (formato `yyyy-MM-dd`): se não informado, considera 1 ano antes de `maxDate`
- `maxDate` (formato `yyyy-MM-dd`): se não informado, considera a data atual do sistema
- `name`: trecho do nome do vendedor (busca case-insensitive); se não informado, considera todos os vendedores
- Parâmetros de paginação padrão do Spring (`page`, `size`, `sort`)

**Exemplo:**

GET /sales/report?minDate=2022-05-01&maxDate=2022-05-31&name=odinson

### 2. Sumário de vendas por vendedor — `GET /sales/summary`

Retorna a soma das vendas de cada vendedor, agrupadas por vendedor, no período informado.

**Parâmetros (todos opcionais):**
- `minDate` e `maxDate`: mesmas regras do relatório acima

**Exemplo:**

GET /sales/summary?minDate=2022-01-01&maxDate=2022-06-30

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- JPQL
- H2 Database (ambiente de testes)
- Maven

## Como executar

1. Clone o repositório
2. Importe o projeto na sua IDE como projeto Maven
3. Execute a classe `DsmetaApplication`
4. A aplicação sobe por padrão em `http://localhost:8080`