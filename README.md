# 🚀 ManageApp API

API REST para gerenciamento de produtos desenvolvida com Java, Spring Boot e MongoDB.

## 📌 Sobre o projeto

O ManageApp API é uma aplicação backend desenvolvida para gerenciar produtos destinados à revenda.

O projeto segue uma arquitetura em camadas e aplica boas práticas de engenharia de software, como DTOs, Bean Validation, tratamento de exceções e separação de responsabilidades.

Este repositório representa o backend do ecossistema ManageApp, que futuramente contará com um frontend em Flutter e recursos adicionais como autenticação, dashboard e estatísticas de vendas.

---

## 🛠 Tecnologias utilizadas

* Java 21
* Spring Boot
* Spring Data MongoDB
* MongoDB
* Maven
* Lombok
* Bean Validation

---

## 📂 Arquitetura do projeto

```text
controller
service
repository
model
dto
exception
```

---

## ✅ Funcionalidades implementadas

### CRUD de Produtos

* Cadastrar produto
* Atualizar produto
* Excluir produto
* Buscar produto por ID
* Listar todos os produtos

### Filtros de pesquisa

* Buscar por nome
* Buscar por descrição
* Buscar por faixa de preço
* Produtos em estoque
* Produtos sem estoque

### Validações

* Campos obrigatórios
* Preços positivos
* Quantidade em estoque maior ou igual a zero

### Tratamento de exceções

* Produto não encontrado
* Erros de validação
* Tratamento global de exceções

### Camada DTO

* ProdutoRequestDTO
* ProdutoResponseDTO

---

## 📈 Próximas funcionalidades

* Dashboard
* Cálculo de lucro
* Metas mensais
* Estatísticas de vendas
* Autenticação com JWT
* Frontend em Flutter
* MongoDB Atlas
* Docker
* Testes automatizados
* Deploy
* manageAppIA (gerenciamento, precificação e demais serviços com a ajuda de IA)
---

## 👨‍💻 Autor

Desenvolvido por Rodrigues (jaxmine).

---

Projeto em desenvolvimento.

O objetivo é evoluir o ManageApp para uma solução completa de gestão de produtos para revenda, com backend em Spring Boot e frontend multiplataforma em Flutter.
Num futuro próximo, haverá acessoria dentro do aplicativo em quase todas as funcionalidades com IA.