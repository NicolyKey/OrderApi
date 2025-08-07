#  Order API

API REST para gerenciamento de comandas (*Orders*) desenvolvida em **Java Spring Boot**.  
Permite criar, listar, buscar, atualizar e remover pedidos, com autenticação e respostas padronizadas.

---

##  Tecnologias Utilizadas
- **Java 17+**
- **Spring Boot** (Web, Security, Data JPA)
- **Hibernate / JPA**
- **PostgreSQL** (ou outro banco configurado)
- **Lombok**
- **JWT** para autenticação

---

##  Estrutura do Projeto
```
src/
├── controller # Controladores REST
├── service # Regras de negócio
├── repository # Acesso ao banco de dados
├── dto # Objetos de transferência de dados
└── mapper # Conversões Entity <-> DTO

````
