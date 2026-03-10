## 🆕 Changelog

### v0.01 (Task 1)
- Adicionado o repositório;
- Criada a estrutura inicial do projeto na IDE;
- Adicionado o menu interativo de usuário;
- Adicionadas as funções de CRUD para usuário;
- Adicionada validação para indices;
- Correção de erros de validação;
- Refatorada a logica do CRUD de IF-ELSE para SWITCH-CASE.

### v0.02 (Task 2)
- Criada a classe `Usuario` com os atributos (nome, email, senha);
- Criada a classe `GerenciadorUsuario` com a logica do CRUD de usuario;
- Refatoração da classe Main(SistemaUsuario) para o padrão orientado a objetos;
- Adiciona tratamento de exceção para o valor de entrada do menu.

## v0.03 (Task 3)
- Migração do projeto para arquitetura Spring Boot;
- Adicionado Maven como gerenciador de dependências;
- Configurado `pom.xml` com Spring Boot Starter Web, Dev Tools, Lombok, Spring Data e H2 Database;
- Configurado `application.properties` com a conexão do H2 Database;
- Renomeada a classe `SistemaUsuario` para `SistemaUsuarioApplication`;
- Renomeada a classe `GerenciadorUsuario` para `UsuarioService` e adiciona compatibilidade com banco de dados;
- Adicionada arquitetura de camadas com Service, Controller, Repository, Entity, Mapper, Dto e Exception;
- Habilitado os endpoints de CREATE, READ, UPDATE e DELETE;
- Adicionado tratamento global de exceções.